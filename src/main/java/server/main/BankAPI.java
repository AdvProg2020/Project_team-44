package server.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This class handles initiating connection to bankAPI ,sending requests to Bank server
 * and also responses from Bank server.
 */
public class BankAPI {
    public static final int PORT = 2222;
    //    public static final String IP = "192.168.1.4";
    public static final String IP = "127.0.0.1";

    private static DataOutputStream outputStream;
    private static DataInputStream inputStream;

    public BankAPI() {
        System.out.println("here1");
        try {
            ConnectToBankServer();
            this.getInputStream();
//            StartListeningOnInput();
//            Scanner scanner = new Scanner(System.in);
//            while (true) {
//                SendMessage(scanner.nextLine());
//            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method is used to add initiating socket and IN/OUT data stream .
     *
     * @throws IOException when IP/PORT hasn't been set up properly.
     */
//    public static void ConnectToBankServer() throws IOException {
//        try {
//            Socket socket = new Socket(IP, PORT);
//            outputStream = new DataOutputStream(socket.getOutputStream());
//            inputStream = new DataInputStream(socket.getInputStream());
//        } catch (IOException e) {
//            throw new IOException("Exception while initiating connection:");
//        }
//    }
    public void ConnectToBankServer() throws IOException {
        try {
            Socket socket = new Socket(IP, PORT);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new IOException("Exception while initiating connection:");
        }
    }

    /**
     * This method is used to start server.Main Thread ,listening on IN data stream.
     */
//    public static String getInputStream() throws IOException {
//        return inputStream.readUTF();
//    }
    public String getInputStream() throws IOException {
        return inputStream.readUTF();
    }

//    public static void StartListeningOnInput() {
//        new Thread(() -> {
//            while (true) {
//                try {
//                    System.out.println(inputStream.readUTF());
//                } catch (IOException e) {
//                    System.out.println("disconnected");
//                    System.exit(0);
//                }
//            }
//        }).start();
//    }

    /**
     * This method is used to send message with value
     *
     * @param msg to Bank server.
     * @throws IOException when OUT data stream been interrupted.
     */
//    public static void SendMessage(String msg) throws IOException {
//        try {
//            outputStream.writeUTF(msg);
//        } catch (IOException e) {
//            throw new IOException("Exception while sending message:");
//        }
//    }
    public void SendMessage(String msg) throws IOException {
        try {
            outputStream.writeUTF(msg);
            outputStream.flush();
        } catch (IOException e) {
            throw new IOException("Exception while sending message:");
        }
    }

    /**
     * This method is used to illustrate an example of using methods of this class.
     */
//    public static void server.main(String[] args) {
//        System.out.println("here");
//        try {
//             ConnectToBankServer();
////            StartListeningOnInput();
//            Scanner scanner = new Scanner(System.in);
//            while (true) {
//                SendMessage(scanner.nextLine());
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//
//    }


}
