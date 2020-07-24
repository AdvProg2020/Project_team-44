package server.viewServer.userRegion.userAccount.sellerAccount;

import javafx.scene.paint.Color;
import server.main.BankAPI;
import server.model.ShopBankAccount;
import server.viewServer.userRegion.userAccount.purchaserAccount.PurchaserWalletServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SellerBankAccountServer {
    private final int port = 9008;

    public SellerBankAccountServer() throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new SellerBankAccountServer.ClientHandler(out, in, socket).start();
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
                    if (input.startsWith("create_account ")) {
                        processCreateAccount(input);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void processCreateAccount(String path) throws IOException {
            BankAPI bankAPI = new BankAPI();
            bankAPI.SendMessage(path);
            String response = bankAPI.getInputStream();
            dataOutputStream.writeUTF(response);
            dataOutputStream.flush();
            if (response.matches("\\d+")) {
                String[] part = path.substring(15).split("\\s");
                new ShopBankAccount(part[0], part[1], part[2], part[3], Integer.parseInt(response));
            }
        }

    }
}
