package client.graphicView.userRegion.userAccount.purchaserAccount;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.main.Main;

import java.io.IOException;

public class PurchaserAuction {
    static Stage primaryStage;

    public static void display() throws IOException {
//        Main.setMediaPlayer("The Swimmer.mp3");
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(PurchaserAuction.class.getResource("/client/graphicView/userRegion/userAccount/purchaserAccount/PurchaserAuction.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}
