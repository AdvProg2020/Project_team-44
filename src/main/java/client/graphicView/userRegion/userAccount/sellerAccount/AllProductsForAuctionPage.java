package client.graphicView.userRegion.userAccount.sellerAccount;

import client.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AllProductsForAuctionPage {
   public static Stage primaryStage;

    public static void display() throws IOException {
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(AllProductsForAuctionPage.class.getResource("/graphicView/userRegion/userAccount/" +
                "sellerAccount/AllProductsForAuction.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}
