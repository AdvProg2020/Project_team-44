package client.graphicView.bankAPI;

import client.graphicView.buyLogPage.BuyLog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BankAPI {
    static Stage window;

    public static void display() throws IOException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        Scene root = new Scene(FXMLLoader.load(BankAPI.class.getResource("/client/graphicView/bankAPI/BankAPI.fxml")));
        window.setScene(root);
        window.setMaximized(true);
        window.show();
    }
}
