package server.graphicViewServer.userRegion.loginPanel;

import server.controller.LoginPageController;
import server.controller.ValidationController;
import server.exception.UsernameExistsException;
import server.exception.UsernameNotExistsException;
import server.exception.WrongPasswordException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;

public class LoginPanelServer {
    public static final int port = 9050;
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private static HashMap<String, String> tokenToUser = new HashMap<>();
    private DataOutputStream out;
    private DataInputStream in;
    private ServerSocket loginPanelServer;

    public LoginPanelServer() throws IOException {
        loginPanelServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = loginPanelServer.accept();
                    in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new LoginPanelServer.ClientHandler(out, in, socket).start();
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
                    if (input.startsWith("IsMainManagerRegistered")) {
                        if (LoginPageController.isIsMainManagerRegistered()) {
                            response = "MainManagerIsRegistered";
                        } else {
                            response = "MainManagerIsNotRegistered";
                        }

                    } else if (input.startsWith("checkUsernameForRegistration:")) {
                        int colonIndex = input.indexOf(":");
                        try {
                            ValidationController.checkUsernameForRegistration(input.substring(colonIndex + 1));
                            response = "checkUsernameForRegistrationTrue";
                        } catch (UsernameExistsException e) {
                            response = "checkUsernameForRegistrationFalse:" + e.getMessage();
                        }

                    } else if (input.startsWith("processLogin")) {
                        int colonIndex = input.indexOf(":");
                        int comoIndex = input.indexOf(",");
                        String username = input.substring(colonIndex + 1, comoIndex);
                        String password = input.substring(comoIndex + 1);
                        try {
                            LoginPageController.processLogin(username, password);
                            String token = generateNewToken();
                            response = "loginSuccessful:" + token;
                            tokenToUser.put(token, username);

                        } catch (UsernameNotExistsException | WrongPasswordException e) {
                            response = "loginUnsuccessful:" + e.getMessage();
                        }

                    }
                    dataOutputStream.writeUTF(response);
                    dataOutputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String generateNewToken() {
            byte[] randomBytes = new byte[24];
            secureRandom.nextBytes(randomBytes);
            return base64Encoder.encodeToString(randomBytes);
        }

    }
}
