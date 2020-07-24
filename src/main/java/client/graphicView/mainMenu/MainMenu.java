package client.graphicView.mainMenu;

import client.graphicView.productMenu.ProductsMenu;
import client.graphicView.userRegion.loginPanel.LoginPanel;
import client.graphicView.userRegion.userAccount.managerAccount.ManagerAccountPage;
import client.graphicView.userRegion.userAccount.purchaserAccount.PurchaserAccountPage;
import client.graphicView.userRegion.userAccount.sellerAccount.SellerAccountPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.Main;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    private static DataOutputStream out;
    private static DataInputStream in;
    private final int PORT = 9055;
    private final String IP = "127.0.0.1";

    public static void display(Stage stage) throws IOException {
        Main.window = stage;
        Pane root = FXMLLoader.load(MainMenu.class.getResource("/graphicView/mainMenu/mainMenu.fxml"));
        Scene scene = new Scene(root);
        Main.window.setScene(scene);
        Main.window.show();
    }

    public void processInitialize() {
        try {
            Socket socket = new Socket(IP, PORT);
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onAccountRegion() throws IOException {
        out.writeUTF("onAccountRegion");
        out.flush();
        String type = in.readUTF();
        if (type.equals("Null")) {
            LoginPanel.display();
        } else if (type.equals("Manager")) {
            ManagerAccountPage.display();
        } else if (type.equals("Seller")) {
            SellerAccountPage.display();
        } else if (type.equals("Purchaser")) {
            PurchaserAccountPage.display();
        } else if (type.equals("Supporter")) {
//            SupporterAccountPage.display();
        }
    }

    @FXML
    void onProducts() throws IOException {
        ProductsMenu.mainMenuScene = Main.window.getScene();
        Pane root = FXMLLoader.load(getClass().getResource("/graphicView/productsMenu/productsMenu.fxml"));
        Main.window.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        processInitialize();
    }
}
