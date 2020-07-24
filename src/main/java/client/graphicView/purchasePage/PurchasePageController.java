package client.graphicView.purchasePage;

import client.graphicView.cart.CartPageController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import server.controller.LoginPageController;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class PurchasePageController implements Initializable {
    public static int wage;
    private static DataOutputStream out;
    private static DataInputStream in;
    private final int PORT = 9056;
    private final String IP = "127.0.0.1";
    @FXML
    public Button creditCardId;
    @FXML
    public Button paymentId;
    @FXML
    private TextField addressField = new TextField();
    //    private TextField addressField = new TextField();
    @FXML
    private TextField telField = new TextField();
    //    private TextField telField = new JFXTextField();
    @FXML
    private TextField codedDiscountField = new TextField();
    @FXML
    private Button backButton = new Button();
    // disable till tel and address fields are filled
    @FXML
    private Label messageLabel = new Label();

    @FXML
    private void goPreviousScene() {
        playButtonSound();
        System.out.println("back");
    }

    public void processInitialize() {
        try {
            Socket socket = new Socket(IP, PORT);
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void creditCardAction() {

        playButtonSound();
        int toPay = CartPageController.totalAmountToPay();
        // if coded discount was valid
        if (!codedDiscountField.getText().equals("")) {
            try {
                out.writeUTF("checkDiscountCodeExistence:" + codedDiscountField.getText());
                out.flush();
                if (in.readUTF().equals("checkDiscountCodeExistenceFalse")) {
                    messageLabel.setText("There is not such a code!");
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!codedDiscountField.getText().equals("")) {
            double percentage = 0;
            try {
                out.writeUTF("codedDiscountPercentage:" + codedDiscountField.getText());
                out.flush();
                percentage = Double.parseDouble(in.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
            toPay *= (double) (100 - percentage) / 100;
        }
        try {
            out.writeUTF("checkEnoughMoney:" + toPay);
            out.flush();
            if (in.readUTF().equals("checkEnoughMoneyFalse")) {
                messageLabel.setText("Not enough money!");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.writeUTF("finishPurchase:" + toPay);
            out.flush();
            in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageLabel.setText("Successfully payed from credit card.");
        System.out.println(LoginPageController.getLoggedInAccount().getBalance());
//        PurchaserAccountPageController.writeInformation();????
    }

    @FXML
    public void paymentAction() {
        int toPay = CartPageController.totalAmountToPay();
        // if coded discount was valid
        if (!codedDiscountField.getText().equals("")) {
            try {
                out.writeUTF("checkDiscountCodeExistence:" + codedDiscountField.getText());
                out.flush();
                if (in.readUTF().equals("checkDiscountCodeExistenceFalse")) {
                    messageLabel.setText("There is not such a code!");
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!codedDiscountField.getText().equals("")) {
            int percentage = 0;
            try {
                out.writeUTF("codedDiscountPercentage:" + codedDiscountField.getText());
                out.flush();
                percentage = Integer.parseInt(in.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
            toPay *= (double) (100 - percentage) / 100;
        }
        try {
            PurchasePagePayment.display(toPay);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playButtonSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/media/sound/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString()));
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        processInitialize();
        try {
            out.writeUTF("getWage");
            out.flush();
            wage = Integer.parseInt(in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // finish button is available only when address and tel are valid
        BooleanProperty isAddressFieldReady = new SimpleBooleanProperty(false);
        BooleanProperty isTelFieldReady = new SimpleBooleanProperty(false);
        addressField.textProperty().addListener((v, oldValue, newValue) -> {
            // address should only consist of words
            if (!newValue.matches("\\w+")) {
                isAddressFieldReady.set(false);
            } else {
                isAddressFieldReady.set(true);
            }
        });

        telField.textProperty().addListener((v, oldValue, newValue) -> {
            // number format, eg: 09120604351
            if (!newValue.matches("[0][9]\\d{9}")) {
                isTelFieldReady.set(false);
            } else {
                isTelFieldReady.set(true);
            }
        });
        // finish button is available when tel and address fields are filled
        creditCardId.disableProperty().bind((isAddressFieldReady.and(isTelFieldReady).not()));
        paymentId.disableProperty().bind((isAddressFieldReady.and(isTelFieldReady).not()));
    }
}
