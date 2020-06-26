package graphicView.userRegion.loginPanel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class LoginPanel {
    static Stage window;

    public static void display() throws IOException {
        Main.setMediaPlayer("01 In The Morning Light.mp3");
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        window.setScene(new Scene(FXMLLoader.load(LoginPanel.class.getResource("/graphicView/userRegion/loginPanel/LoginPanel.fxml"))));
        window.setMaximized(true);
        window.show();
    }
}
