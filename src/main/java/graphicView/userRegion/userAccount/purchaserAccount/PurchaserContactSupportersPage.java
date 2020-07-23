package graphicView.userRegion.userAccount.purchaserAccount;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class PurchaserContactSupportersPage {
    static Stage primaryStage;

    public static void display() throws IOException {
        primaryStage = new Stage();
        primaryStage.initModality(APPLICATION_MODAL);
        Parent root = FXMLLoader.load(PurchaserAccountPage.class.getResource("/graphicView/userRegion/userAccount/purchaserAccount/PurchaserContactSupportersPage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}