package client.graphicView.userRegion.loginPanel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.main.Main;

import java.io.IOException;

public class SellerInfoSetPanel {
    static Stage window;
    private static String sellerUsername;
    private static String sellerPassword;
    private static String sellerEmail;

    public static String getSellerUsername() {
        return sellerUsername;
    }

    public static void setSellerUsername(String sellerUsername) {
        SellerInfoSetPanel.sellerUsername = sellerUsername;
    }

    public static String getSellerPassword() {
        return sellerPassword;
    }

    public static void setSellerPassword(String sellerPassword) {
        SellerInfoSetPanel.sellerPassword = sellerPassword;
    }

    public static String getSellerEmail() {
        return sellerEmail;
    }

    public static void setSellerEmail(String sellerEmail) {
        SellerInfoSetPanel.sellerEmail = sellerEmail;
    }

    public static void display(String username, String password, String email) throws IOException {
        setSellerEmail(email);
        setSellerUsername(username);
        setSellerPassword(password);
        Main.setMediaPlayer("01 In The Morning Light.mp3");
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hello World");
        window.setScene(new Scene(FXMLLoader.load(SellerInfoSetPanel.class.getResource("SellerInfoSetPanel.fxml"))));
        window.setMaximized(true);
        window.show();
    }
}
