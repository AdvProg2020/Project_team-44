package server;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;

public class FileUploadClient {
    private static final int PORT = 9060;
    private static final String IP = "127.0.0.1";
    static DataInputStream in;
    static DataOutputStream out;
    static Scanner scanner;
    private static Socket socket;


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String location = scanner.nextLine();
        try {
            socket = new Socket(IP, PORT);
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            File file = new File(location + name);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            out.writeUTF("UploadName:" + name + ":" + fileContent.length);
            out.flush();
            for (byte b : fileContent) {
                out.write(b);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
