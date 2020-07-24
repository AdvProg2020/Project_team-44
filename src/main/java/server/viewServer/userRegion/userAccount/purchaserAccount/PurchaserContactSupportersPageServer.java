package server.viewServer.userRegion.userAccount.purchaserAccount;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.model.account.Account;
import server.model.account.Supporter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class PurchaserContactSupportersPageServer {
    public static final int port = 9014;

    public PurchaserContactSupportersPageServer() throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new PurchaserContactSupportersPageServer.ClientHandler(out, in, socket).start();
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
                    if (input.startsWith("get_all_supporter")) {
                        processGetAllSupporter();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void processGetAllSupporter() throws IOException {
            StringBuilder all = new StringBuilder();
            for (Supporter supporter : Supporter.getAllSupporters()) {
                if (supporter.isLoggedIn()) {
                    all.append(supporter.getUserName() + " - ");
                }
            }
            dataOutputStream.writeUTF(all.toString());
            dataOutputStream.flush();
        }


    }
}
