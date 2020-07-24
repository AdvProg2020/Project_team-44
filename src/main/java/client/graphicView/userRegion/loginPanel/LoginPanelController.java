package client.graphicView.userRegion.loginPanel;

import server.controller.LoginPageController;
import server.controller.ValidationController;
import server.exception.UsernameExistsException;
import server.exception.UsernameNotExistsException;
import server.exception.WrongPasswordException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import server.model.account.Account;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginPanelController implements Initializable {
    //    pass this to next scene for adding properties
    private static Account loggedInAccount;

    public static void setLoggedInAccount(Account loggedInAccount) {
        LoginPanelController.loggedInAccount = loggedInAccount;
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static String token;

    final ObservableList<String> accountTypesList = FXCollections.observableArrayList("Head Manager",
            "Seller",
            "Purchaser");
    @FXML
    private ComboBox<String> accountTypes = new ComboBox<>();

    // check if clicked, then can login
    @FXML
    private RadioButton agreeButton = new RadioButton();

    @FXML
    private TextField loginUsernameField = new TextField();

    @FXML
    private TextField registerUsernameField = new TextField();

    @FXML
    private PasswordField loginPasswordField = new PasswordField();

    @FXML
    private PasswordField registerPasswordField = new PasswordField();

    @FXML
    private Label loginMessageText = new Label();

    @FXML
    private Label registerMessageText = new Label();

    @FXML
    private TextField registerEmailField = new TextField();

    //    after hit the buttons, call this
    void resetFields() {
        registerEmailField.setText("");
        registerPasswordField.setText("");
        registerUsernameField.setText("");
        loginPasswordField.setText("");
        loginUsernameField.setText("");
        loginMessageText.setText("");
        registerMessageText.setText("");
    }

    @FXML
//    set on registerButton action
    private void processRegister() {
        playButtonSound();
//           if agrees the terms, then can register
        if (!agreeButton.isSelected()) {
            registerMessageText.setText("Agree terms first!");
            return;
        }
//            check username only consist words. numbers and longer than 8
        if (!registerUsernameField.getText().matches("\\w{8,}")) {
            registerMessageText.setText("Invalid username");
            return;
        }
//            check password only consist words, numbers and longer than 8
        if (!registerPasswordField.getText().matches("\\w{8,}")) {
            registerMessageText.setText("Invalid password");
            return;
        }
//       we dont check the domain in this case
        if (!registerEmailField.getText().matches("\\w+[@]\\w+[.]\\w+")) {
            registerMessageText.setText("Invalid email");
            return;
        }
        if (accountTypes.getValue() == null) {
            registerMessageText.setText("Select server.main.Main type first");
            return;
        }
        try {
            ValidationController.checkUsernameForRegistration(registerUsernameField.getText());
            LoginPanel.window.close();
            if (accountTypes.getValue().equals("Head Manager")) {
                ManagerInfoSetPanel.display(registerUsernameField.getText(),
                        registerPasswordField.getText(),
                        registerEmailField.getText());
            } else if (accountTypes.getValue().equals("Seller")) {
                SellerInfoSetPanel.display(registerUsernameField.getText(),
                        registerPasswordField.getText(),
                        registerEmailField.getText());
            } else {
                PurchaserInfoSetPanel.display(registerUsernameField.getText(),
                        registerPasswordField.getText(),
                        registerEmailField.getText());
            }
            resetFields();
        } catch (UsernameExistsException | IOException exception) {
            registerMessageText.setText(exception.getMessage());
        }
    }

    @FXML
//    set on loginButton action
    private void processLogin() {
        playButtonSound();
        //            check username only consist words, numbers and longer than 8
        if (!loginUsernameField.getText().matches("\\w{8,}")) {
            loginMessageText.setText("Invalid username");
            return;
        }
//            check password only consist words, numbers and longer than 8
        if (!loginPasswordField.getText().matches("\\w{8,}")) {
            loginMessageText.setText("Invalid password");
            return;
        }
        try {
            LoginPageController.processLogin(loginUsernameField.getText(),
                    loginPasswordField.getText());
            resetFields();
            LoginPanel.window.close();
//            TODO: goto next scene
        } catch (UsernameNotExistsException | WrongPasswordException exception) {
            loginMessageText.setText(exception.getMessage());
        }
    }

    private void playButtonSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/server.main/resources/media/sound/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString()));
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loggedInAccount = null;
//        manager can register only once
        if (LoginPageController.isIsMainManagerRegistered()) {
            accountTypesList.remove(0);
        }
        accountTypes.setItems(accountTypesList);
    }
}
