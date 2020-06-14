package graphicView.mainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;

public class MainMenu {

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
