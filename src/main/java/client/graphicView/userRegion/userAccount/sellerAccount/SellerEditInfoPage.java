package client.graphicView.userRegion.userAccount.sellerAccount;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.main.Main;

import java.io.IOException;

public class SellerEditInfoPage {
    static Stage primaryStage;

    public static void display() throws IOException {
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(SellerEditInfoPage.class.getResource("/client/graphicView/userRegion/userAccount/sellerAccount/SellerAccountEditInfoPage.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}
