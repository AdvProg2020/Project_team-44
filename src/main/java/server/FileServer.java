package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class FileServer {
    public static final int PORT = 9060;
    private static final String PATH = "src\\main\\resources\\";
    private DataOutputStream out;
    private DataInputStream in;
    private ServerSocket fileServer;

    public FileServer() throws IOException {
        fileServer = new ServerSocket(PORT);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Socket socket = fileServer.accept();
                        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                        new main.java.FileServer.ClientHandler(out, in, socket).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
                while (true) {
                    input = dataInputStream.readUTF();
                    if (input.startsWith("DownloadName:")) {
                        int colonIndex = input.indexOf(":");
                        String fileName = input.substring(colonIndex + 1);
                        File file = new File(PATH + fileName);
                        byte[] fileContent = Files.readAllBytes(file.toPath());
                        dataOutputStream.writeUTF("" + fileContent.length);
                        dataOutputStream.flush();
                        for (byte b : fileContent) {
                            dataOutputStream.write(b);
                            dataOutputStream.flush();
                        }
                        return;
                    } else if (input.startsWith("UploadName:")) {
                        String[] parts = input.split(":");
                        String fileName = parts[1];
                        int fileByteSize = Integer.parseInt(parts[2]);
                        byte[] allBytes = new byte[fileByteSize];
                        for (int i = 0; i < fileByteSize; i++) {
                            allBytes[i] = dataInputStream.readByte();
                        }
                        FileOutputStream fileOutputStream = new FileOutputStream(PATH + fileName);
                        fileOutputStream.write(allBytes);
                        fileOutputStream.close();
                        return;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
