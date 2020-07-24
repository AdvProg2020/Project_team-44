package server.graphicViewServer.cart;

import server.controller.LoginPageController;
import server.controller.PurchaserAccountController;
import server.model.account.Purchaser;
import server.model.product.Product;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CartPageServer {
    public static final int PORT = 9054;
    private DataOutputStream out;
    private DataInputStream in;
    private ServerSocket cartPageServer;

    public CartPageServer() throws IOException {
        cartPageServer = new ServerSocket(PORT);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = cartPageServer.accept();
                    in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new CartPageServer.ClientHandler(out, in, socket).start();
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
                    if (input.startsWith("gatCart")) {
                        response = "";
                        System.out.println("in server!");
                        System.out.println( PurchaserAccountController.getCartProducts().size());
                        for (Product product : PurchaserAccountController.getCartProducts()) {
                            response += product.getName() + ":" +
                                    (int) product.getPrice() + ":" +
                                    ((Purchaser) LoginPageController.getLoggedInAccount()).getCart().get(product) +
                                    "\n";
                        }
                        System.out.print(response);
                        System.out.println("not server");
                    } else if (input.startsWith("totalAmountToPay")) {
                        response = "" + (int) PurchaserAccountController.processShowTotalPriceEach();

                    } else if (input.startsWith("increaseItemInCart:")) {
                        int colonIndex = input.indexOf(":");
                        String productName = input.substring(colonIndex + 1);
                        PurchaserAccountController.increaseItemInCart(productName);
                        response = "";
                    } else if (input.startsWith("decreaseItemInCart:")) {
                        int colonIndex = input.indexOf(":");
                        String productName = input.substring(colonIndex + 1);
                        PurchaserAccountController.decreaseItemInCart(productName);
                        response = "";
                    } else if (input.startsWith("deleteItemInCart:")) {
                        int colonIndex = input.indexOf(":");
                        String productName = input.substring(colonIndex + 1);
                        PurchaserAccountController.deleteItemInCart(productName);
                        response = "";
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
