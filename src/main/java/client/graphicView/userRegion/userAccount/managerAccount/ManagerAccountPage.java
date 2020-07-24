package client.graphicView.userRegion.userAccount.managerAccount;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.main.Main;

import java.io.IOException;

public class ManagerAccountPage {
    public static Stage primaryStage;

    public static void display() throws IOException {
        Main.setMediaPlayer("The Swimmer.mp3");
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(ManagerAccountPage.class.getResource("/graphicView/userRegion/userAccount/managerAccount/ManagerAccountPage.fxml"));
        primaryStage.setTitle("Hello World");
        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
                Main.launch();
            }
        });
        back.setLayoutX(1250);
        Pane backRoot = new Pane();
        backRoot.getChildren().add(back);
        backRoot.getChildren().add(root);
        Scene scene = new Scene(backRoot);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        Main.setAccountRegionStage(primaryStage);
        primaryStage.show();
    }
}
