package graphicView.userRegion.userAccount.managerRequestions.removeProduct;

import graphicView.userRegion.userAccount.managerRequestions.addOff.OffRequestInfo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RemoveProductRequestInfo {
    static Stage window;

    public static void display() throws IOException {
//        Main.setMediaPlayer("01 In The Morning Light.mp3");
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        Scene root = new Scene(FXMLLoader.load(RemoveProductRequestInfo.class.getResource("/graphicView/userRegion/userAccount/managerRequestions/removeProduct/RemoveProductRequestInfo.fxml")));
        window.setScene(root);
//        window.setMaximized(true);
        window.show();
    }
}
