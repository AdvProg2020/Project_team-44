package client.graphicView.userRegion.userAccount.purchaserAccount;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.Main;
import server.model.product.Auction;

import java.io.IOException;

public class PurchaserAuctionChat {
    static Stage primaryStage;
    public static Auction thisAuction;

    public static void display(Auction auction) throws IOException {
        thisAuction = auction;
        Main.setMediaPlayer("The Swimmer.mp3");
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(PurchaserAuctionChat.class.getResource("/graphicView/userRegion/userAccount/purchaserAccount/PurchaserAuctionChat.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}
