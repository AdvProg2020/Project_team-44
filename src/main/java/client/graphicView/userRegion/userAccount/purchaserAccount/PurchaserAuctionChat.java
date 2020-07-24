package client.graphicView.userRegion.userAccount.purchaserAccount;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.main.Main;
import server.model.product.Auction;

import java.io.IOException;

public class PurchaserAuctionChat {
    static Stage primaryStage;
    //    public static Auction thisAuction;
    public static int i;

    public static void display(int num) throws IOException {
//        thisAuction = auction;
        i = num;
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
