package server.viewServer.purchasePage;

import client.graphicView.purchasePage.PurchasePageController;
import server.controller.LoginPageController;
import server.main.BankAPI;
import server.model.ShopBankAccount;
import server.model.account.Purchaser;
import server.model.account.Seller;
import server.model.product.Product;
import server.viewServer.userRegion.LoginPanel.LoginPanelServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PurchasePagePaymentServer {
    private final int port = 9009;

    public PurchasePagePaymentServer() throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new PurchasePagePaymentServer.ClientHandler(out, in, socket).start();
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
                while (true) {
                    String input = dataInputStream.readUTF();
                    if (input.startsWith("create_receipt")) {
                        processCreateReceipt(input);
                    } else if (input.equals("get_shop_bank_account_id")) {
                        processGetShopBankAAccountId();
                    } else if (input.startsWith("get_size ")) {
                        processGetSize(input.substring(9));
                    } else if (input.startsWith("set_each_product ")) {
                        processSetEachProduct(Integer.parseInt(input.substring(17)));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void processGetShopBankAAccountId() throws IOException {
            dataOutputStream.writeUTF(String.valueOf(ShopBankAccount.getShopBankAccount().getAccountId()));
            dataOutputStream.flush();
        }

        public void processCreateReceipt(String path) throws IOException {
            BankAPI bankAPI = new BankAPI();
            String[] parts = path.split("\\s");
            String token;
            String response = null;
            if (path.startsWith("create_receiptReceive")) {
                token = processGetToken("get_token " + ShopBankAccount.getShopBankAccount().getUserName() + " " + ShopBankAccount.getShopBankAccount().getPassword());
                if (!token.equals("invalid username or password")) {
                    StringBuilder message = new StringBuilder("create_receipt " + token);
                    for (int i = 1; i < parts.length; i++) {
                        message.append(" " + parts[i]);
                    }
                    bankAPI.SendMessage(message.toString());
                    response = bankAPI.getInputStream();
                } else {
                    dataOutputStream.writeUTF(token);
                    dataOutputStream.flush();
                    return;
                }
            } else if (path.startsWith("create_receiptCharge")) {
                token = processGetToken("get_token " + parts[parts.length - 2] + " " + parts[parts.length - 1]);
                if (!token.equals("invalid username or password")) {
                    StringBuilder message = new StringBuilder("create_receipt " + token);
                    for (int i = 1; i < parts.length - 2; i++) {
                        message.append(" " + parts[i]);
                    }
                    bankAPI.SendMessage(message.toString());
                    response = bankAPI.getInputStream();
                } else {
                    dataOutputStream.writeUTF(token);
                    dataOutputStream.flush();
                    return;
                }
            }
            if (!response.matches("\\d+")) {
                dataOutputStream.writeUTF(response);
            } else {
                bankAPI.SendMessage("pay " + Integer.parseInt(response));
                dataOutputStream.writeUTF(bankAPI.getInputStream());
            }
            dataOutputStream.flush();
//            bankAPI.SendMessage("exit");
        }

        public String processGetToken(String path) throws IOException {
            BankAPI bankAPI = new BankAPI();
            bankAPI.SendMessage(path);
            String response = bankAPI.getInputStream();
            if (response.equals("invalid username or password")) {
                dataOutputStream.writeUTF(response);
                dataOutputStream.flush();
            }
            return response;
        }

        public void processGetSize(String token) throws IOException {
            Purchaser purchaser = Purchaser.getPurchaserByUsername(LoginPanelServer.getTokenToUser().get(token));
            dataOutputStream.writeUTF(String.valueOf(purchaser.getSellerSelectedForEachProduct().keySet().size()));
            dataOutputStream.flush();
        }

        public void processSetEachProduct(int i) {
            Seller sellers = ((Purchaser) LoginPageController.getLoggedInAccount()).getSellerSelectedForEachProduct().get(i);
            Product product = null;
            for (Product products : ((Purchaser) LoginPageController.getLoggedInAccount()).getSellerSelectedForEachProduct().keySet()) {
                if (((Purchaser) LoginPageController.getLoggedInAccount()).getSellerSelectedForEachProduct().get(products).getUserName().equals(sellers.getUserName())) {
                    product = products;
                    break;
                }
            }
            Seller seller = ((Purchaser) LoginPageController.getLoggedInAccount()).getSellerSelectedForEachProduct().get(product);
            seller.setBalance(seller.getBalance() + ((100 - PurchasePageController.wage) * ((Purchaser) LoginPageController.getLoggedInAccount()).getCart().get(product) * product.getPrice()) / 100);
            seller.createAndUpdateJson();
        }
    }
}
