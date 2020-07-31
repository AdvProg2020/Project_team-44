//package server;
//
//import java.io.*;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class FileDownloadClient {
//    private static final int PORT = 9060;
//    private static final String IP = "127.0.0.1";
//    static DataInputStream in;
//    static DataOutputStream out;
//    static Scanner scanner;
//    private static Socket socket;
//
//    public static void main(String[] args) {
//        scanner = new Scanner(System.in);
//        String name = scanner.nextLine();
//        String path = scanner.nextLine();
//        try {
//            socket = new Socket(IP, PORT);
//            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
//            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
//            out.writeUTF("DownloadName:" + name);
//            out.flush();
//            int byteNumber = (int) Double.parseDouble(in.readUTF());
//            byte[] allBytes = new byte[byteNumber];
//            for (int i = 0; i < byteNumber; i++) {
//                allBytes[i] = in.readByte();
//            }
//            FileOutputStream fileOutputStream = new FileOutputStream(path);
//            fileOutputStream.write(allBytes);
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
