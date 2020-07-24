package server.viewServer.userRegion.userAccount.sellerAccount;

import server.controller.LoginPageController;
import server.model.account.Seller;
import server.model.product.Auction;
import server.model.product.Product;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SellerAuctionServer {
    private final int port = 9006;

    public SellerAuctionServer() throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new SellerAuctionServer.ClientHandler(out, in, socket).start();
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
                    if (input.equals("logout")) {
                        processLogout();
                    } else if (input.startsWith("create_auction ")) {
                        processCreateAuction(input.substring(15));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void processLogout() {
            LoginPageController.logout();
        }

        public void processCreateAuction(String path) {
            String name = path.split("\\s")[0];
            int year = Integer.parseInt(path.split("\\s")[1]);
            int month = Integer.parseInt(path.split("\\s")[2]);
            int day = Integer.parseInt(path.split("\\s")[3]);
            int hour = Integer.parseInt(path.split("\\s")[4]);
            int minute = Integer.parseInt(path.split("\\s")[5]);
            Date date = new Date(year, month, day, hour, minute);
            Product product = Product.getProductByName(name);
            Auction auction = new Auction(product.getCategory(), product.getName(),
                    product.getCompanyName(), product.getPrice(), product.getExplanationText(),
                    product.getImageName(), date);
            Seller seller = (Seller) LoginPageController.loggedInAccount;
            seller.getAuction().add(auction);
            seller.createAndUpdateJson();
        }
    }
}
