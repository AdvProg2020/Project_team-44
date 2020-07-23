package client.graphicView.mainMenu;

import server.controller.LoginPageController;
import client.graphicView.productMenu.ProductsMenu;
import client.graphicView.userRegion.loginPanel.LoginPanel;
import client.graphicView.userRegion.userAccount.managerAccount.ManagerAccountPage;
import client.graphicView.userRegion.userAccount.purchaserAccount.PurchaserAccountPage;
import client.graphicView.userRegion.userAccount.sellerAccount.SellerAccountPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.Main;
import server.model.account.Manager;
import server.model.account.Purchaser;
import server.model.account.Seller;

import java.io.IOException;

public class MainMenu {

    public static void display(Stage stage) throws IOException {
        Main.window = stage;
        Pane root = FXMLLoader.load(MainMenu.class.getResource("/graphicView/mainMenu/mainMenu.fxml"));
        Scene scene = new Scene(root);
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
        ProductsMenu.mainMenuScene = Main.window.getScene();
        Pane root = FXMLLoader.load(getClass().getResource("/graphicView/productsMenu/productsMenu.fxml"));
        Main.window.setScene(new Scene(root));
    }
}
