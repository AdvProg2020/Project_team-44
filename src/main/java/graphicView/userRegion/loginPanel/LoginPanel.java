package graphicView.userRegion.loginPanel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPanel {
    static Stage window;

    public static void display() throws IOException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        window.setScene(new Scene(FXMLLoader.load(LoginPanel.class.getResource("/graphicView/userRegion/loginPanel/LoginPanel.fxml"))));
        window.setMaxHeight(400);
        window.setMaxWidth(600);
        window.show();
    }
}
