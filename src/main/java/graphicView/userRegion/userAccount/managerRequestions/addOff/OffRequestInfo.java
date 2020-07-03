package graphicView.userRegion.userAccount.managerRequestions.addOff;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class OffRequestInfo {
    static Stage window;

    public static void display() throws IOException {
//        Main.setMediaPlayer("01 In The Morning Light.mp3");
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        Scene root = new Scene(FXMLLoader.load(OffRequestInfo.class.getResource("/graphicView/userRegion/userAccount/managerRequestions/addOff/OffRequestInfo.fxml")));
        window.setScene(root);
//        window.setMaximized(true);
        window.show();
    }
}
