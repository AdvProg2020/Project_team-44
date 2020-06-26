package graphicView.mainMenu;

import controller.LoginPageController;
import graphicView.productMenu.ProductsMenu;
import graphicView.userRegion.loginPanel.LoginPanel;
import graphicView.userRegion.userAccount.ManagerAccountPage;
import graphicView.userRegion.userAccount.PurchaserAccountPage;
import graphicView.userRegion.userAccount.SellerAccountPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;
import model.account.Manager;
import model.account.Purchaser;
import model.account.Seller;

import java.io.IOException;

public class MainMenu {

    public static void display(Stage stage) throws IOException {
        Main.window = stage;
        Pane root = FXMLLoader.load(MainMenu.class.getResource("/graphicView/mainMenu/mainMenu.fxml"));
        root.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, null, null)));
        Scene scene = new Scene(root);
        scene.setFill(Color.FORESTGREEN);
        Main.window.setScene(scene);
        Main.window.show();
    }

    @FXML
    void onAccountRegion(ActionEvent event) throws IOException {
        if (LoginPageController.getLoggedInAccount() == null) {
            LoginPanel.display();
        } else if (LoginPageController.getLoggedInAccount() instanceof Manager) {
            ManagerAccountPage.display();
        } else if (LoginPageController.getLoggedInAccount() instanceof Seller) {
            SellerAccountPage.display();
        } else if (LoginPageController.getLoggedInAccount() instanceof Purchaser) {
            PurchaserAccountPage.display();
        }
    }

    @FXML
    void onProducts(ActionEvent event) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("/graphicView/productsMenu/productsMenu.fxml"));
        Main.window.setScene(new Scene(root));
    }
}
