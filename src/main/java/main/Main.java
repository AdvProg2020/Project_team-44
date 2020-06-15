package main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Pane root = FXMLLoader.load(getClass().getResource("/graphicView/userRegion/userAccount/PurchaserAccount.fxml"));
        primaryStage.setTitle("Project App");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}