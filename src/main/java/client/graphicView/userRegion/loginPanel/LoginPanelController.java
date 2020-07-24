package client.graphicView.userRegion.loginPanel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import server.model.account.Account;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPanelController implements Initializable {
    //    pass this to next scene for adding properties
    private static Account loggedInAccount;
    private static String token;
    final ObservableList<String> accountTypesList = FXCollections.observableArrayList("Head Manager",
            "Seller",
            "Purchaser");
    private final int PORT = 9050;
    private final String IP = "127.0.0.1";
    private DataOutputStream out;
    private DataInputStream in;
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

    public static String getToken() {
        return token;
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static void setLoggedInAccount(Account loggedInAccount) {
        LoginPanelController.loggedInAccount = loggedInAccount;
    }

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
            registerMessageText.setText("Select server.Main type first");
            return;
        }
        try {
            out.writeUTF("checkUsernameForRegistration:" + registerUsernameField.getText());
            out.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
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
            out.writeUTF("processLogin:" + loginUsernameField.getText() + "," + loginPasswordField.getText());
            out.flush();
            //            TODO: goto next scene
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playButtonSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/media/sound/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString()));
        mediaPlayer.play();
    }


    public void input() {
        while (true) {
            String input;
            try {
                input = in.readUTF();
                if (input.startsWith("MainManagerIsRegistered")) {
                    accountTypesList.remove(0);
                    accountTypes.setItems(accountTypesList);

                } else if (input.startsWith("MainManagerIsNotRegistered")) {
                    accountTypes.setItems(accountTypesList);

                } else if (input.startsWith("checkUsernameForRegistrationFalse")) {
                    int colonIndex = input.indexOf(":");
                    String exceptionMessage = input.substring(colonIndex + 1);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            registerMessageText.setText(exceptionMessage);
                        }
                    });

                } else if (input.startsWith("checkUsernameForRegistrationTrue")) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            processGoToInfoSetPanel();
                        }
                    });

                } else if (input.startsWith("loginSuccessful:")) {
                    int colonIndex = input.indexOf(":");
                    LoginPanelController.token = input.substring(colonIndex + 1);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            resetFields();
                            LoginPanel.window.close();
                        }
                    });

                } else if (input.startsWith("loginUnsuccessful:")) {
                    int colonIndex = input.indexOf(":");
                    String exceptionMessage = input.substring(colonIndex + 1);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            loginMessageText.setText(exceptionMessage);
                        }
                    });

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void processGoToInfoSetPanel() {
        LoginPanel.window.close();
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        resetFields();
    }

    public void processInitialize() {
        try {
            Socket socket = new Socket(IP, PORT);
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            new Thread(this::input).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        processInitialize();
        loggedInAccount = null;
//        manager can register only once
        try {
            out.writeUTF("IsMainManagerRegistered");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
