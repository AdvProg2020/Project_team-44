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
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
