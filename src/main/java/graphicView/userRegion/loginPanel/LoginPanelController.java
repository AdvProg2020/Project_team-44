package graphicView.userRegion.loginPanel;

import controller.LoginPageController;
import exception.UsernameExistsException;
import exception.UsernameNotExistsException;
import exception.WrongPasswordException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.account.Account;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPanelController implements Initializable {
//    pass this to next scene for adding properties
    static Account registeringAccount;

    final ObservableList<String> accountTypesList = FXCollections.observableArrayList("Head Manager",
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
            registerMessageText.setText("Select a type first");
            return;
        }
        try {
            resetFields();
            registeringAccount =  LoginPageController.processCreateAccount(accountTypes.getValue().toLowerCase(),
                    registerUsernameField.getText(),
                    registerPasswordField.getText(),
                    "",
                    "",
                    registerEmailField.getText(),
                    "",
                    null,
                    "",
                    null);
            LoginPanel.window.close();
            try {
                InfoSetPanel.display();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UsernameExistsException exception) {
            registerMessageText.setText(exception.getMessage());
        }
    }

    @FXML
//    set on loginButton action
    private void processLogin() {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registeringAccount = null;
//        manager can register only once
        if (LoginPageController.isIsMainManagerRegistered()) {
            accountTypesList.remove(0);
        }
        accountTypes.setItems(accountTypesList);
    }
}
