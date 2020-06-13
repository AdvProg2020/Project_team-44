package aa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("/graphicView/mainMenu/mainMenu.fxml"));
        Scene scene = new Scene(root, 1250, 720);
        stage.setScene(scene);
        stage.show();
    }
}