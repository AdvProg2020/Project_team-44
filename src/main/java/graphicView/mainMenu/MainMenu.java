package graphicView.mainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class MainMenu {
    static Stage window;

    public static void display() throws IOException {
        window = new Stage();
        Pane root = FXMLLoader.load(MainMenu.class.getResource("/graphicView/mainMenu/mainMenu.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @FXML
    void onAccountRegion(ActionEvent event) {

    }

    @FXML
    void onOffers(ActionEvent event) {
        System.out.println("noooooo");
    }

    @FXML
    void onProducts(ActionEvent event) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("/graphicView/productsMenu/productsMenu.fxml"));
        Main.window.setScene(new Scene(root));
    }

    @FXML
    void onSignUp(ActionEvent event) {

    }
}
