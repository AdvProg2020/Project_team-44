package graphicView.userRegion.loginPanel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class InfoSetPanel {
    static Stage window;

    public static void display() throws IOException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        window.setScene(new Scene(FXMLLoader.load(InfoSetPanel.class.getResource("InfoSetPanel.fxml"))));
        window.setMaximized(true);
        window.show();
    }
}
