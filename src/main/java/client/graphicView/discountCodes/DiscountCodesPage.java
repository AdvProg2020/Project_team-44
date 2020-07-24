package client.graphicView.discountCodes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.Main;

import java.io.IOException;

public class DiscountCodesPage {
    static Stage window;

    public static void display() throws IOException {
        Main.setMediaPlayer("The Swimmer.mp3");
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        Scene root = new Scene(FXMLLoader.load(DiscountCodesPage.class.getResource("/client/graphicView/discountCodes/DiscountCodesPage.fxml")));
        window.setScene(root);
        window.setMaximized(true);
        window.show();
    }
}
