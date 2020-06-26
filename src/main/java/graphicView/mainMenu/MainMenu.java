package graphicView.mainMenu;

import graphicView.userRegion.userAccount.PurchaserAccountPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class MainMenu {

    public static void display() throws IOException {
        Main.setMediaPlayer("The Swimmer.mp3");
        Main.window = new Stage();
        Main.window.setMaximized(true);
        Pane root = FXMLLoader.load(MainMenu.class.getResource("/graphicView/mainMenu/mainMenu.fxml"));
        Scene scene = new Scene(root);
        Main.window.setScene(scene);
        Main.window.show();
    }

    @FXML
    void onAccountRegion(ActionEvent event) throws IOException {
//        Pane root = FXMLLoader.load(getClass().getResource("/graphicView/userRegion/userAccount/PurchaserAccountPage.fxml"));
//        PurchaserAccountPageController.writeInformation();
//        Main.window.setScene(new Scene(root, 800, 600));
////        Main.window.show();
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
}
