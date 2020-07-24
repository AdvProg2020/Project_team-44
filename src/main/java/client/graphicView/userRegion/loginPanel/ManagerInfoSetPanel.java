package client.graphicView.userRegion.loginPanel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.Main;

import java.io.IOException;

public class ManagerInfoSetPanel {
    static Stage window;
    private static String managerUsername;
    private static String managerPassword;
    private static String managerEmail;

    public static String getManagerUsername() {
        return managerUsername;
    }

    public static void setManagerUsername(String managerUsername) {
        ManagerInfoSetPanel.managerUsername = managerUsername;
    }

    public static String getManagerPassword() {
        return managerPassword;
    }

    public static void setManagerPassword(String managerPassword) {
        ManagerInfoSetPanel.managerPassword = managerPassword;
    }

    public static String getManagerEmail() {
        return managerEmail;
    }

    public static void setManagerEmail(String managerEmail) {
        ManagerInfoSetPanel.managerEmail = managerEmail;
    }

    public static void display(String username, String password, String email) throws IOException {
        setManagerEmail(email);
        setManagerPassword(password);
        setManagerUsername(username);
        Main.setMediaPlayer("01 In The Morning Light.mp3");
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        window.setScene(new Scene(FXMLLoader.load(ManagerInfoSetPanel.class.getResource("/graphicView/userRegion/loginPanel/ManagerInfoSetPanel.fxml"))));
        window.setMaximized(true);
        window.show();
    }
}
