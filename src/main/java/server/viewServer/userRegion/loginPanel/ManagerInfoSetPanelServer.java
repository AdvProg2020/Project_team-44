package server.viewServer.userRegion.loginPanel;

import client.graphicView.userRegion.loginPanel.LoginPanelController;
import server.controller.LoginPageController;
import server.exception.UsernameExistsException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ManagerInfoSetPanelServer {
    public static final int PORT = 9051;
    private DataOutputStream out;
    private DataInputStream in;
    private ServerSocket managerInfoSetPanelServer;

    public ManagerInfoSetPanelServer() throws IOException {
        managerInfoSetPanelServer = new ServerSocket(PORT);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = managerInfoSetPanelServer.accept();
                    in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new ManagerInfoSetPanelServer.ClientHandler(out, in, socket).start();
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
                        String[] managerLoginInfo = input.split("\n");
                        String username = managerLoginInfo[1];
                        String password = managerLoginInfo[2];
                        String firstName = managerLoginInfo[3];
                        String secondName = managerLoginInfo[4];
                        String email = managerLoginInfo[5];
                        String tel = managerLoginInfo[6];
                        // never reach catch clause cause it was the server.exception was checked in previous scene
                        try {
                            LoginPanelController.setLoggedInAccount(LoginPageController.processCreateAccount("Head Manager",
                                    username,
                                    password,
                                    firstName,
                                    secondName,
                                    email,
                                    tel,
                                    null,
                                    null,
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
