package server.viewServer.userRegion.userAccount.managerRequestons.addOff;

import server.model.requests.RequestForAddOff;
import server.viewServer.userRegion.userAccount.managerAccount.ManagerAccountPageServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AddOffRequestServer {
    public static final int port = 9011;

    public AddOffRequestServer() throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new AddOffRequestServer.ClientHandler(out, in, socket).start();
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
                    if (input.startsWith("get_all_request")) {
                        processGetAllRequest();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void processGetAllRequest() throws IOException {
            StringBuilder all = new StringBuilder();
            for (RequestForAddOff requestForAddOff : RequestForAddOff.getAllRequestsForAddOff()) {
                all.append(requestForAddOff.getRequestId() + " - ");
            }
            dataOutputStream.writeUTF(all.toString());
            dataOutputStream.flush();
        }
    }
}
