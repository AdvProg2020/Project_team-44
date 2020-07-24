package server.viewServer.purchasePage;

import client.graphicView.userRegion.userAccount.sellerAccount.SellerWalletController;
import server.controller.LoginPageController;
import server.controller.ManagerAccountController;
import server.model.CodedDiscount;
import server.model.account.Manager;
import server.model.account.Purchaser;
import server.model.account.Seller;
import server.model.product.Product;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PurchasePageServer {
    public static final int PORT = 9056;
    private DataOutputStream out;
    private DataInputStream in;
    private ServerSocket purchasePageServer;

    public PurchasePageServer() throws IOException {
        purchasePageServer = new ServerSocket(PORT);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = purchasePageServer.accept();
                    in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new PurchasePageServer.ClientHandler(out, in, socket).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class ClientHandler extends Thread {
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;
        private Socket socket;

        private ClientHandler(DataOutputStream dataOutputStream, DataInputStream dataInputStream, Socket socket) {
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                String input;
                String response = null;
                while (true) {
                    input = dataInputStream.readUTF();
                    if (input.startsWith("checkDiscountCodeExistence")) {
                        int colonIndex = input.indexOf(":");
                        String discountCode = input.substring(colonIndex + 1);
                        if (!ManagerAccountController.processViewDiscountCodes().contains(discountCode)) {
                            response = "checkDiscountCodeExistenceFalse";
                        } else {
                            response = "checkDiscountCodeExistenceTrue";
                        }

                    } else if (input.startsWith("codedDiscountPercentage:")) {
                        int colonIndex = input.indexOf(":");
                        String discountCode = input.substring(colonIndex + 1);
                        response = "" + CodedDiscount.getCodedDiscountByCode(discountCode).getDiscountPercentage();

                    } else if (input.startsWith("checkEnoughMoney:")) {
                        int colonIndex = input.indexOf(":");
                        int toPay = Integer.parseInt(input.substring(colonIndex + 1));
                        if (toPay > (int) LoginPageController.getLoggedInAccount().getBalance() - SellerWalletController.atLeastAmount) {
                            response = "checkEnoughMoneyFalse";
                        } else {
                            response = "checkEnoughMoneyTrue";
                        }

                    } else if (input.startsWith("finishPurchase:")) {
                        int colonIndex = input.indexOf(":");
                        int toPay = Integer.parseInt(input.substring(colonIndex + 1));
                        int wage = Manager.getAllManagers().get(0).getWage();
                        Purchaser purchaser = (Purchaser) LoginPageController.getLoggedInAccount();
                        purchaser.setBalance(purchaser.getBalance() - toPay);
                        purchaser.createAndUpdateJson();
                        for (Product product : ((Purchaser) LoginPageController.getLoggedInAccount()).getSellerSelectedForEachProduct().keySet()) {
                            Seller seller = purchaser.getSellerSelectedForEachProduct().get(product);
                            seller.setBalance(seller.getBalance() + ((100 - wage) * purchaser.getCart().get(product) * product.getPrice()) / 100);
                            seller.createAndUpdateJson();
                        }
                        response = "";
                    } else if (input.startsWith("getWage")) {
                        response = "" + Manager.getAllManagers().get(0).getWage();
                    }
                    dataOutputStream.writeUTF(response);
                    dataOutputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
