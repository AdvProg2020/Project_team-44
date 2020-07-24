package client.graphicView.userRegion.loginPanel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.Main;

import java.io.IOException;

public class PurchaserInfoSetPanel {
    static Stage window;
    private static String purchaserUsername;
    private static String purchaserPassword;
    private static String purchaserEmail;

    public static String getPurchaserUsername() {
        return purchaserUsername;
    }

    public static void setPurchaserUsername(String purchaserUsername) {
        PurchaserInfoSetPanel.purchaserUsername = purchaserUsername;
    }

    public static String getPurchaserPassword() {
        return purchaserPassword;
    }

    public static void setPurchaserPassword(String purchaserPassword) {
        PurchaserInfoSetPanel.purchaserPassword = purchaserPassword;
    }

    public static String getPurchaserEmail() {
        return purchaserEmail;
    }

    public static void setPurchaserEmail(String purchaserEmail) {
        PurchaserInfoSetPanel.purchaserEmail = purchaserEmail;
    }

    public static void display(String username, String password, String email) throws IOException {
        setPurchaserEmail(email);
        setPurchaserUsername(username);
        setPurchaserPassword(password);
        Main.setMediaPlayer("01 In The Morning Light.mp3");
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        window.setScene(new Scene(FXMLLoader.load(PurchaserInfoSetPanel.class.getResource("/graphicView/userRegion/loginPanel/PurchaserInfoSetPanel.fxml"))));
        window.setMaximized(true);
        window.show();
    }
}
