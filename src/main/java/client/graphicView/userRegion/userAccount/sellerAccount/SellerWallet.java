package client.graphicView.userRegion.userAccount.sellerAccount;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.main.Main;

import java.io.IOException;

public class SellerWallet {
    static Stage primaryStage;

    public static void display() throws IOException {
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(SellerWallet.class.getResource("/client/graphicView/userRegion/userAccount/sellerAccount/SellerWallet.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}
