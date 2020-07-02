package graphicView.userRegion.userAccount.sellerAccount;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class SellerEditInfoPageController {
    public TextField newSellerFirstName;
    public TextField newSellerLastName;
    public TextField newSellerUsername;
    public TextField newSellerPassword;
    public TextField newSellerEmail;
    public TextField newSellerPhoneNumber;
    public TextField newSellerCompanyName;
    public TextField newSellerCompanyPhoneNumber;
    public TextField newSellerCompanyAddress;
    public static void display() throws IOException {
        Main.setMediaPlayer("The Swimmer.mp3");
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(SellerEditInfoPageController.class.getResource("/graphicView/mainMenu/a.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}
