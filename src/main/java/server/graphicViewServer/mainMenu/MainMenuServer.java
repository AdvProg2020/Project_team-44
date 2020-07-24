package server.graphicViewServer.mainMenu;

import server.controller.LoginPageController;
import server.model.account.Manager;
import server.model.account.Purchaser;
import server.model.account.Seller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainMenuServer {
    public static final int PORT = 9055;
    private DataOutputStream out;
    private DataInputStream in;
    private ServerSocket mainMenuServer;


    public MainMenuServer() throws IOException {
        mainMenuServer = new ServerSocket(PORT);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = mainMenuServer.accept();
                    in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new MainMenuServer.ClientHandler(out, in, socket).start();
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
                    if (input.startsWith("onAccountRegion")) {
                        if (LoginPageController.getLoggedInAccount() == null) {
                            response = "Null";
                            System.out.println(response);
                        } else if (LoginPageController.getLoggedInAccount() instanceof Manager) {
                            response = "Manager";
                            System.out.println(response);
                        } else if (LoginPageController.getLoggedInAccount() instanceof Seller) {
                            response = "Seller";
                            System.out.println(response);
                        } else if (LoginPageController.getLoggedInAccount() instanceof Purchaser) {
                            response = "Purchaser";
                            System.out.println(response);
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
