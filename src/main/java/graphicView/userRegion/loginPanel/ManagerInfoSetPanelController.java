package graphicView.userRegion.loginPanel;

import controller.LoginPageController;
import exception.UsernameExistsException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.account.Manager;

import java.io.File;
import java.io.IOException;

public class ManagerInfoSetPanelController {
    @FXML
    private TextField firstNameField = new TextField();
    @FXML
    private TextField secondNameField = new TextField();
    @FXML
    private TextField telField = new TextField();
    @FXML
    private Label messageLabel = new Label();
    @FXML
    private Button nextButton = new Button();

    private void playButtonSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/media/sound/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString()));
        mediaPlayer.play();
    }

    //    set on nextButton action
    public void goNext() throws IOException {
        playButtonSound();
        if (!firstNameField.getText().matches("[a-z|A-Z]+") ||
                !secondNameField.getText().matches("[a-z|A-Z]+") ||
                !telField.getText().matches("[0][9]\\d{9}")) {

            messageLabel.setText("Invalid. Try Again");
        }
        // never reach catch clause cause it was the exception was checked in previous scene
        try {
            LoginPanelController.setLoggedInAccount(LoginPageController.processCreateAccount("Head Manager",
                    ManagerInfoSetPanel.getManagerUsername(),
                    ManagerInfoSetPanel.getManagerPassword(),
                    firstNameField.getText(),
                    secondNameField.getText(),
                    ManagerInfoSetPanel.getManagerEmail(),
                    telField.getText(),
                    null,
                    null,
                    null));
        } catch (UsernameExistsException e) {
            e.printStackTrace();
        }
        ManagerInfoSetPanel.window.close();
        LoginPanel.display();
    }
}
