package main;

import controller.LoginPageController;
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
    private static ArrayList<Account> onlineAccounts = new ArrayList<>();
    private static ArrayList<Account> offlineAccounts = new ArrayList<>();
    public static HashMap<String, DataOutputStream> allOut = new HashMap<>();
    public static HashMap<String, DataInputStream> allIn = new HashMap<>();
    public static int mostPrice = 0;
    public static String winnerUserName = "";


    public static ArrayList<Account> allPurchasers = new ArrayList<>();
    public static HashMap<String, String> getMessageFromUsername = new HashMap<>();

    public static void setMostPrice(int mostPrice) {
        ShopServer.mostPrice = mostPrice;
    }

    public static int getMostPrice() {
        return mostPrice;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new ClientHandler(out, in, socket).start();
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
                    } else if (input.startsWith("Auction : ")) {
                        processAuction(input.substring(10), socket, dataOutputStream, dataInputStream);
                    } else if (input.startsWith("send_message ")) {
                        processSendMessage(input);
                    } else if (input.startsWith("get_all_purchaser ")) {
                        processGetAllPurchasers(input.substring(18));
                    } else if (input.startsWith("most price : ")) {
                        processSetMostPrice(Integer.parseInt(input.substring(13)));
                    } else if (input.equals("get_most_price")) {
                        processGetMostPrice();
                    } else if (input.equals("get_winner")) {
                        processGetWinner();
                    } else if (input.startsWith("set_winner ")) {
                        processSetWinner(input);
                    }
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

        public void processAuction(String username, Socket socket, DataOutputStream out, DataInputStream in) {
            allPurchasers.add(Account.getAccountByUsername(username));
            allOut.put(username, out);
            allIn.put(username, in);
        }

        public void processGetAllPurchasers(String username) throws IOException {
            StringBuilder all = new StringBuilder();
            for (String s : allOut.keySet()) {
                if (!s.equals(username))
                    all.append(s + " ");
            }
            dataOutputStream.writeUTF(all.toString());
            dataOutputStream.flush();

        }

        public void processSendMessage(String input) throws IOException {
            String sender = input.split("\\s")[2];
            String receiver = input.split("\\s")[4];
            String before = "send_message " + "from " + sender + " to " + receiver + " ";
            String message = input.substring(before.length());
            allOut.get(receiver).writeUTF("from " + sender + " " + message);
            allOut.get(receiver).flush();
        }

        public synchronized void processSetMostPrice(int price) {
            setMostPrice(price);
        }

        public void processGetMostPrice() throws IOException {
            dataOutputStream.writeUTF(String.valueOf(getMostPrice()));
            dataOutputStream.flush();
        }

        public void processGetWinner() throws IOException {
            dataOutputStream.writeUTF(winnerUserName + " " + mostPrice);
            dataOutputStream.flush();
        }

        public void processSetWinner(String input) {
            winnerUserName = input.substring(11);
        }
    }
}

