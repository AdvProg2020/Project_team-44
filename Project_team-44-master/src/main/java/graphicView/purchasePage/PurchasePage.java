package graphicView.purchasePage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PurchasePage {
    public static void display() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(PurchasePage.class.getResource("/graphicView/purchasePage/PurchasePage.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
