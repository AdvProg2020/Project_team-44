package server.viewServer.userRegion.userAccount.sellerAccount;

import server.controller.LoginPageController;
import server.model.account.Seller;
import server.model.product.Product;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AllProductsForAuctionPageServer {
    private final int port = 9005;

    public AllProductsForAuctionPageServer() throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new AllProductsForAuctionPageServer.ClientHandler(out, in, socket).start();
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
                    if (input.startsWith("logout ")) {
                        processLogout(input.substring(7));
                    } else if (input.startsWith("check ")) {
                        processCheck(Integer.parseInt(input.substring(6)));
                    } else if (input.equals("get_seller_product_to_sell_size")) {
                        processGetSize();
                    } else if (input.startsWith("get_product_info ")) {
                        processGetProductInfo(Integer.parseInt(input.substring(17)));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void processLogout(String token) {
            LoginPageController.logout();
        }

        public void processCheck(int i) throws IOException {
            Seller seller = (Seller) LoginPageController.loggedInAccount;
            Product product = null;
            for (Product products : seller.getProductsToSell().keySet()) {
                if (seller.getProductsToSell().get(products) == i) {
                    product = products;
                }
            }
            if (!seller.isProductInAuctionList(product)) {
                dataOutputStream.writeUTF("yes");
            } else {
                dataOutputStream.writeUTF("no");
            }
            dataOutputStream.flush();
        }

        public void processGetSize() throws IOException {
            Seller seller = (Seller) LoginPageController.loggedInAccount;
            dataOutputStream.writeUTF(String.valueOf(seller.getProductsToSell().size()));
            dataOutputStream.flush();
        }

        public void processGetProductInfo(int i) throws IOException {
            Seller seller = (Seller) LoginPageController.loggedInAccount;
            Product product = null;
            for (Product products : seller.getProductsToSell().keySet()) {
                if (seller.getProductsToSell().get(products) == i) {
                    product = products;
                }
            }
            dataOutputStream.writeUTF(product.getName() + " " + product.getPrice());
            dataOutputStream.flush();
        }
    }
}
