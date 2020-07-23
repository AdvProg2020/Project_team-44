package client.graphicView.userRegion.userAccount.sellerAccount;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.Main;

import java.io.IOException;

public class ViewAllProductsForSellerPage {
    static Stage primaryStage;

    public static void display() throws IOException {
        Main.setMediaPlayer("The Swimmer.mp3");
        primaryStage = new Stage();
        Pane root = FXMLLoader.load(ViewAllProductsForSellerPage.class.getResource("/graphicView/userRegion/userAccount/sellerAccount/ViewAllProductsForSellerPage.fxml"));
        root.getChildren().add(ViewAllProductsForSellerPageController.setTableView());
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}
