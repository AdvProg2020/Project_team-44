package graphicView.userRegion.userAccount.managerAccount;

import graphicView.userRegion.userAccount.PurchaserAccountPage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class AddCategoryPage {
    static Stage primaryStage;

    public static void display() throws IOException {
        Main.setMediaPlayer("The Swimmer.mp3");
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(AddCategoryPage.class.getResource("/graphicView/userRegion/userAccount/managerAccount/AddCategory.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}
