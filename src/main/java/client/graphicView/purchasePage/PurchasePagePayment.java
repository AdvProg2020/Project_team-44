package client.graphicView.purchasePage;

import client.graphicView.userRegion.userAccount.purchaserAccount.PurchaserAccountPage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.main.Main;

import java.io.IOException;

public class PurchasePagePayment {
    static Stage primaryStage;
   public static int toPay;

    public static void display(int money) throws IOException {
        PurchasePage.primaryStage.close();
        toPay = money;
        Main.setMediaPlayer("The Swimmer.mp3");
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(PurchasePagePayment.class.getResource("/graphicView/purchasePage/PurchasePagePayment.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}
