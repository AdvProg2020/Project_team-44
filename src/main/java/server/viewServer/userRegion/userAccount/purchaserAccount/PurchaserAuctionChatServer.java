package server.viewServer.userRegion.userAccount.purchaserAccount;

import client.graphicView.userRegion.userAccount.purchaserAccount.PurchaserAuctionChat;
import server.controller.LoginPageController;
import server.main.BankAPI;
import server.model.ShopBankAccount;
import server.model.account.Account;
import server.model.account.Manager;
import server.model.product.Auction;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

public class PurchaserAuctionChatServer {
    public static final int port = 9007;
    private int clientNumber;
    private static ArrayList<Account> onlineAccounts = new ArrayList<>();
    private static ArrayList<Account> offlineAccounts = new ArrayList<>();
    public static HashMap<String, DataOutputStream> allOut = new HashMap<>();
    public static HashMap<String, DataInputStream> allIn = new HashMap<>();
    public static int mostPrice = 0;
    public static String winnerUserName = "";
    public static HashMap<String, String> allUserToToken = new HashMap<>();

    public static ArrayList<Account> allPurchasers = new ArrayList<>();

    public static HashMap<String, String> getMessageFromUsername = new HashMap<>();


    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static void setMostPrice(int mostPrice) {
        PurchaserAuctionChatServer.mostPrice = mostPrice;
    }

    public static int getMostPrice() {
        return mostPrice;
    }

    public PurchaserAuctionChatServer() throws IOException {
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
                    } else if (input.startsWith("Auction")) {
                        processAuction();
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
                    } else if (input.equals("get_min_amount")) {
                        processGetMinAmount();
                    } else if (input.startsWith("get_time ")) {
                        processGetTime(Integer.parseInt(input.substring(9)));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void processAuction() {

            allPurchasers.add(LoginPageController.getLoggedInAccount());
            allOut.put(LoginPageController.getLoggedInAccount().getUserName(), dataOutputStream);
            allIn.put(LoginPageController.getLoggedInAccount().getUserName(), dataInputStream);
            try {
                dataOutputStream.writeUTF(LoginPageController.getLoggedInAccount().getUserName() + " " + generateNewToken());
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void processGetAllPurchasers(String username) throws IOException {
            StringBuilder all = new StringBuilder("all purchaser is : ");
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


        public synchronized void processSetMostPrice(int price) throws IOException {
            setMostPrice(price);
            for (DataOutputStream output : allOut.values()) {
                output.writeUTF("the most price is : " + getMostPrice());
                output.flush();
            }
        }

        public void processGetMostPrice() throws IOException {
            dataOutputStream.writeUTF("the most price is : " + getMostPrice());
            dataOutputStream.flush();
        }

        public void processGetWinner() throws IOException {
            dataOutputStream.writeUTF("the bid winner is : " + winnerUserName + " " + mostPrice);
            dataOutputStream.flush();
            Auction.getAllAuctions().remove(PurchaserAuctionChat.i);
            Account.getAccountByUsername(winnerUserName).setBalance(Account.getAccountByUsername(winnerUserName).getBalance() - mostPrice);
        }

        public void processSetWinner(String input) {
            for (String s : allUserToToken.keySet()) {
                if (allUserToToken.get(s).equals(input.substring(11))) {
                    winnerUserName = s;
                    break;
                }
            }
        }

        public String generateNewToken() {
            byte[] randomBytes = new byte[24];
            secureRandom.nextBytes(randomBytes);
            for (String value : allUserToToken.values()) {
                if (value.equals(base64Encoder.encodeToString(randomBytes)))
                    generateNewToken();
            }
            allUserToToken.put(LoginPageController.getLoggedInAccount().getUserName(), base64Encoder.encodeToString(randomBytes));
            return base64Encoder.encodeToString(randomBytes);
        }

        public void processGetMinAmount() {
            try {
                dataOutputStream.writeUTF(String.valueOf(Manager.getAllManagers().get(0).getMinAmount()));
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void processGetTime(int i) throws IOException {
            Auction auction = Auction.getAllAuctions().get(i);
            dataOutputStream.writeUTF(String.valueOf(auction.getFinalDate().getTime()));
            dataOutputStream.flush();
        }
    }
}

