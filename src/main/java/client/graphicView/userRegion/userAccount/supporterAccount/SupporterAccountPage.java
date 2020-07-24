package client.graphicView.userRegion.userAccount.supporterAccount;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.Main;

import java.io.IOException;

public class SupporterAccountPage {
    static Stage primaryStage;

    public static void display() throws IOException {
        Main.setMediaPlayer("The Swimmer.mp3");
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(SupporterAccountPage.class.getResource("/graphicView/userRegion/userAccount/" +
                "supporterAccount/SupporterAccountPage.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}
