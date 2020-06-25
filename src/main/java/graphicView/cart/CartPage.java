package graphicView.cart;

import graphicView.userRegion.loginPanel.LoginPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CartPage {
    static Stage window;

    public static void display() throws IOException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        Scene root = new Scene(FXMLLoader.load(CartPage.class.getResource("/graphicView/cart/CartPage.fxml")));
        window.setScene(root);
        window.setMaximized(true);
        window.show();
    }
}
