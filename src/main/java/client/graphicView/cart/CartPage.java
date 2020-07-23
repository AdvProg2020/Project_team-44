package client.graphicView.cart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.main.Main;

import java.io.IOException;

public class CartPage {
    static Stage window;

    public static void display() throws IOException {
        Main.setMediaPlayer("01 In The Morning Light.mp3");
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        Scene root = new Scene(FXMLLoader.load(CartPage.class.getResource("/client/graphicView/cart/CartPage.fxml")));
        window.setScene(root);
        window.setMaximized(true);
        window.show();
    }
}
