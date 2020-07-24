package server.viewServer.userRegion.userAccount.purchaserAccount;

import server.model.product.Auction;
import server.viewServer.userRegion.userAccount.managerAccount.ManagerAccountPageServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PurchaserAuctionServer {
    private final int port = 9004;

    public PurchaserAuctionServer() throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new PurchaserAuctionServer.ClientHandler(out, in, socket).start();
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
                    if (input.startsWith("get_auction_info_by_num ")) {
                        processGetAuctionInfo(Integer.parseInt(input.substring(24)));
                    } else if (input.startsWith("get_auction_size")) {
                        processGetAuctionSize();
                    } else if (input.startsWith("get_image_name ")) {
                        processGetImageName(Integer.parseInt(input.substring(15)));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void processGetAuctionInfo(int num) throws IOException {
            Auction auction = Auction.getAllAuctions().get(num);
            dataOutputStream.writeUTF(auction.getName() + " " + auction.getPrice() + " " + auction.getExplanationText());
            dataOutputStream.flush();
        }

        public void processGetAuctionSize() throws IOException {
            dataOutputStream.writeUTF(String.valueOf(Auction.getAllAuctions().size()));
            dataOutputStream.flush();
        }

        public void processGetImageName(int i) throws IOException {
            Auction auction = Auction.getAllAuctions().get(i);
            dataOutputStream.writeUTF(auction.getImageName());
            dataOutputStream.flush();
        }

    }
}
