package server.viewServer.userRegion.userAccount.managerAccount;

import server.controller.LoginPageController;
import server.model.account.Account;
import server.model.account.Manager;
import server.viewServer.userRegion.loginPanel.LoginPanelServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ManagerAccountPageServer {
    private final int port = 9000;

    public ManagerAccountPageServer() throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new ManagerAccountPageServer.ClientHandler(out, in, socket).start();
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
                    if (input.startsWith("set_min_amount ")) {
                        processSetMinAmount(Integer.parseInt(input.substring(15)));
                    } else if (input.startsWith("set_wage ")) {
                        processSetWage(Integer.parseInt(input.substring(9)));
                    } else if (input.startsWith("get_information ")) {
                        processGetInformation(input.substring(16));
                    } else if (input.startsWith("logout ")) {
                        processLogout(input.substring(7));
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void processSetMinAmount(int minAmount) {
            for (Manager allManager : Manager.getAllManagers()) {
                allManager.setMinAmount(minAmount);
                allManager.createAndUpdateJson();
            }
        }

        public void processSetWage(int wage) {
            for (Manager allManager : Manager.getAllManagers()) {
                allManager.setWage(wage);
                allManager.createAndUpdateJson();
            }
        }

        public void processGetInformation(String token) {
            String username = null;
            for (String tok : LoginPanelServer.getTokenToUser().keySet()) {
                if (tok.equals(token))
                    username = LoginPanelServer.getTokenToUser().get(tok);
            }
            Account account = Account.getAccountByUsername(username);
            try {
                dataOutputStream.writeUTF("information is : " + account.getFirstName() + " " +
                        account.getLastName() + " " + account.getUserName() + " " + account.getTelephoneNumber() +
                        " " + account.getEMail() + " " + account.getPassword());
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void processLogout(String token) {
            LoginPageController.logout();
        }
    }
}
