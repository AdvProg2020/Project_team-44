package client.graphicView.userRegion.loginPanel;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerInfoSetPanelController implements Initializable {
    private final int PORT = 9051;
    private final String IP = "127.0.0.1";
    private DataOutputStream out;
    private DataInputStream in;
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
            return;
        }
        out.writeUTF("finishRegister:" +
                "\n" + ManagerInfoSetPanel.getManagerUsername() +
                "\n" + ManagerInfoSetPanel.getManagerPassword() +
                "\n" + firstNameField.getText() +
                "\n" + secondNameField.getText() +
                "\n" + ManagerInfoSetPanel.getManagerEmail() +
                "\n" + telField.getText());
        out.flush();
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


    public void input() {
        while (true) {
            String input;
            try {
                input = in.readUTF();
                if (input.startsWith("registerSuccessful")) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ManagerInfoSetPanel.window.close();
                            try {
                                LoginPanel.display();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } else if (input.startsWith("registerUnSuccessful:")) {
                    int colonIndex = input.indexOf(":");
                    String errorStackTrace = input.substring(colonIndex + 1);
                    throw new Exception(errorStackTrace);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        processInitialize();
    }
}
