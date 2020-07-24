package client.graphicView.purchasePage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.main.Main;

import java.io.IOException;

public class PurchasePage {
    static Stage primaryStage = new Stage();
    public static void display() throws IOException {
        Main.setMediaPlayer("The Swimmer.mp3");
        Parent root = FXMLLoader.load(PurchasePage.class.getResource("/graphicView/purchasePage/PurchasePage.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
