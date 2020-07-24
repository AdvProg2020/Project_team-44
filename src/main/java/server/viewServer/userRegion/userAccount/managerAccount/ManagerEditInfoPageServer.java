package server.viewServer.userRegion.userAccount.managerAccount;

import server.controller.LoginPageController;
import server.controller.ManagerAccountController;
import server.exception.ManagerFieldsNotExistException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ManagerEditInfoPageServer {
    private final int port = 9001;

    public ManagerEditInfoPageServer() throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new ManagerEditInfoPageServer.ClientHandler(out, in, socket).start();
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
            while (true) {
                String input = null;
                try {
                    input = dataInputStream.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (input.startsWith("logout ")) {
                    processLogout(input.substring(7));
                } else if (input.startsWith("edit_field_each ")) {
                    String[] part = input.substring(16).split("\\s");
                    try {
                        processEditFieldEach(part[0], part[1], part[2], part[3]);
                    } catch (ManagerFieldsNotExistException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void processLogout(String token) {
            LoginPageController.logout();
        }

        public void processEditFieldEach(String newFirstName, String newLastName, String newEmail, String newPassWord) throws ManagerFieldsNotExistException {
            ManagerAccountController.processEditFieldEach("FIRST_NAME", newFirstName);
            ManagerAccountController.processEditFieldEach("LAST_NAME", newLastName);
            ManagerAccountController.processEditFieldEach("EMAIL", newEmail);
            ManagerAccountController.processEditFieldEach("PASSWORD", newPassWord);
        }

    }
}
