package server.viewServer.userRegion.LoginPanel;

import client.graphicView.userRegion.loginPanel.LoginPanelController;
import server.controller.LoginPageController;
import server.exception.UsernameExistsException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class PurchaserInfoSetPanelServer {
    public static final int PORT = 9052;
    private DataOutputStream out;
    private DataInputStream in;
    private ServerSocket purchaserInfoSetPanelServer;

    public PurchaserInfoSetPanelServer() throws IOException {
        purchaserInfoSetPanelServer = new ServerSocket(PORT);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = purchaserInfoSetPanelServer.accept();
                    in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new PurchaserInfoSetPanelServer.ClientHandler(out, in, socket).start();
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
                    if (input.startsWith("finishRegister:")) {
                        String[] purchaserLoginInfo = input.split("\n");
                        String username = purchaserLoginInfo[1];
                        String password = purchaserLoginInfo[2];
                        String firstName = purchaserLoginInfo[3];
                        String secondName = purchaserLoginInfo[4];
                        String email = purchaserLoginInfo[5];
                        String tel = purchaserLoginInfo[6];
                        String address = purchaserLoginInfo[7];

                        // never reach catch clause cause it was the server.exception was checked in previous scene
                        try {
                            LoginPanelController.setLoggedInAccount(LoginPageController.processCreateAccount("Purchaser",
                                    username,
                                    password,
                                    firstName,
                                    secondName,
                                    email,
                                    tel,
                                    null,
                                    address,
                                    null));
                            response = "registerSuccessful";
                        } catch (UsernameExistsException e) {
                            response = "registerUnSuccessful:" + Arrays.toString(e.getStackTrace());
                        }

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
