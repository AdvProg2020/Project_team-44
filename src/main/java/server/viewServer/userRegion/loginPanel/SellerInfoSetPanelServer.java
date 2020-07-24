package server.viewServer.userRegion.loginPanel;

import server.model.requests.RequestForSeller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SellerInfoSetPanelServer {
    public static final int PORT = 9053;
    private DataOutputStream out;
    private DataInputStream in;
    private ServerSocket sellerInfoSetPanelServer;


    public SellerInfoSetPanelServer() throws IOException {
        sellerInfoSetPanelServer = new ServerSocket(PORT);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = sellerInfoSetPanelServer.accept();
                    in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new SellerInfoSetPanelServer.ClientHandler(out, in, socket).start();
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
                        String[] sellerLoginInfo = input.split("\n");
                        String username = sellerLoginInfo[1];
                        String password = sellerLoginInfo[2];
                        String firstName = sellerLoginInfo[3];
                        String secondName = sellerLoginInfo[4];
                        String email = sellerLoginInfo[5];
                        String tel = sellerLoginInfo[6];
                        String address = sellerLoginInfo[7];
                        String companyName = sellerLoginInfo[8];
                        String companyTel = sellerLoginInfo[9];

                        new RequestForSeller(companyName, address, companyTel
                                , username, firstName, secondName,
                                email, tel, password);
                        response = "registerSuccessful";
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
