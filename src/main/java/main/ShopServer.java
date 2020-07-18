package main;

import model.ShopBankAccount;
import model.account.Account;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ShopServer {
    public static final String IP = "127.0.0.1";
    public static final int port = 8888;
    private int clientNumber;
    private ArrayList<Account> onlineAccounts = new ArrayList<>();
    private ArrayList<Account> offlineAccounts = new ArrayList<>();

    public ShopServer() throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        this.clientNumber = 0;
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new ClientHandler(out, in).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    class ClientHandler extends Thread {
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;

        private ClientHandler(DataOutputStream dataOutputStream, DataInputStream dataInputStream) {
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
        }

        @Override
        public void run() {
            try {
                String input = dataInputStream.readUTF();
                if (input.startsWith("connected to Shop")) {
                    onlineAccounts.add(Account.getAccountByUsername(input.substring(17)));
                    dataOutputStream.writeUTF("client is online");
                    dataOutputStream.flush();
                } else if (input.startsWith("exit")) {
                    onlineAccounts.remove(Account.getAccountByUsername(input.substring(4)));
                    offlineAccounts.add(Account.getAccountByUsername(input.substring(4)));
                    dataOutputStream.writeUTF("client is offline");
                    dataOutputStream.flush();
                } else if (input.startsWith("create_receipt")) {
                    processCreateReceipt(input);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    }
}

