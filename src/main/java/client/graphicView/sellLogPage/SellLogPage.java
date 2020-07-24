package client.graphicView.sellLogPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.Main;

import java.io.IOException;

public class SellLogPage {
    static Stage window;

    public static void display() throws IOException {
        Main.setMediaPlayer("Camille Saint-Saëns-Danse Macabre.mp3");
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        Scene root = new Scene(FXMLLoader.load(SellLogPage.class.getResource("/client/graphicView/sellLogPage/SellLogPage.fxml")));
        window.setScene(root);
        window.setMaximized(true);
        window.show();
    }
}
