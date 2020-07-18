package graphicView.userRegion.userAccount.managerAccount;

import graphicView.userRegion.userAccount.sellerAccount.SellerEditInfoPage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class ManagerAccountBankAccount {
    static Stage primaryStage;

    public static void display() throws IOException {
        Main.setMediaPlayer("The Swimmer.mp3");
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(SellerEditInfoPage.class.getResource("/graphicView/userRegion/userAccount/managerAccount/BankAccount.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}
