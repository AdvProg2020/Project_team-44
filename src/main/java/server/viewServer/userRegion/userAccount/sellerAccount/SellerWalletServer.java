package server.viewServer.userRegion.userAccount.sellerAccount;

import server.main.BankAPI;
import server.model.ShopBankAccount;
import server.model.account.Account;
import server.model.account.Manager;
import server.viewServer.userRegion.LoginPanel.LoginPanelServer;
import server.viewServer.userRegion.userAccount.managerAccount.ManagerAccountPageServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SellerWalletServer {
    private final int port = 9003;

    public SellerWalletServer() throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new SellerWalletServer.ClientHandler(out, in, socket).start();
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
                    if (input.equals("get_at_least_amount")) {
                        processGetAtLeastAmount();
                    } else if (input.startsWith("get_balance ")) {
                        processGetBalance(input.substring(12));
                    } else if (input.startsWith("create_receipt")) {
                        processCreateReceipt(input);
                    } else if (input.startsWith("set_balance ")) {
                        processSetBalance(input.substring(12));
                    } else if (input.equals("get_shop_bank_account_id")) {
                        processGetShopBankAAccountId();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void processGetAtLeastAmount() throws IOException {
            dataOutputStream.writeUTF("At least amount is : " + String.valueOf(Manager.getAllManagers().get(0).getMinAmount()));
            dataOutputStream.flush();
        }

        public void processGetBalance(String token) throws IOException {
            Account account = Account.getAccountByUsername(LoginPanelServer.tokenToUser.get(token));
            dataOutputStream.writeUTF(String.valueOf(account.getBalance()));
            dataOutputStream.flush();
        }

        public String processGetToken(String path) throws IOException {
            BankAPI bankAPI = new BankAPI();
            bankAPI.SendMessage(path);
            String response = bankAPI.getInputStream();
            if (response.equals("invalid username or password")) {
                dataOutputStream.writeUTF(response);
                dataOutputStream.flush();
            }
            return response;
        }

        public void processCreateReceipt(String path) throws IOException {
            BankAPI bankAPI = new BankAPI();
            String[] parts = path.split("\\s");
            String token;
            String response = null;
            if (path.startsWith("create_receiptReceive")) {
                token = processGetToken("get_token " + ShopBankAccount.getShopBankAccount().getUserName() + " " + ShopBankAccount.getShopBankAccount().getPassword());
                if (!token.equals("invalid username or password")) {
                    StringBuilder message = new StringBuilder("create_receipt " + token);
                    for (int i = 1; i < parts.length; i++) {
                        message.append(" " + parts[i]);
                    }
                    bankAPI.SendMessage(message.toString());
                    response = bankAPI.getInputStream();
                } else {
                    dataOutputStream.writeUTF(token);
                    dataOutputStream.flush();
                    return;
                }
            } else if (path.startsWith("create_receiptCharge")) {
                token = processGetToken("get_token " + parts[parts.length - 2] + " " + parts[parts.length - 1]);
                if (!token.equals("invalid username or password")) {
                    StringBuilder message = new StringBuilder("create_receipt " + token);
                    for (int i = 1; i < parts.length - 2; i++) {
                        message.append(" " + parts[i]);
                    }
                    bankAPI.SendMessage(message.toString());
                    response = bankAPI.getInputStream();
                } else {
                    dataOutputStream.writeUTF(token);
                    dataOutputStream.flush();
                    return;
                }
            }
            if (!response.matches("\\d+")) {
                dataOutputStream.writeUTF(response);
            } else {
                bankAPI.SendMessage("pay " + Integer.parseInt(response));
                dataOutputStream.writeUTF(bankAPI.getInputStream());
            }
            dataOutputStream.flush();
//            bankAPI.SendMessage("exit");
        }

        public void processSetBalance(String path) throws IOException {
            int amount = Integer.parseInt(path.split("\\s")[0]);
            String token = path.substring(path.split("\\s")[0].length() + 1);
            Account account = Account.getAccountByUsername(LoginPanelServer.tokenToUser.get(token));
            account.setBalance(account.getBalance() - amount);
            account.createAndUpdateJson();
            dataOutputStream.writeUTF(String.valueOf(account.getBalance()));
            dataOutputStream.flush();
        }

        public void processGetShopBankAAccountId() throws IOException {
            dataOutputStream.writeUTF(String.valueOf(ShopBankAccount.getShopBankAccount().getAccountId()));
            dataOutputStream.flush();
        }

    }
}
