package graphicView.purchasePage;

import controller.LoginPageController;
import controller.ManagerAccountController;
import graphicView.cart.CartPageController;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.CodedDiscount;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class PurchasePageController implements Initializable {
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
    @FXML
    private Label messageLabel = new Label();
    // disable till tel and address fields are filled
    @FXML
    private Button finishPurchase = new Button();

    @FXML
    private void goPreviousScene() {
        playButtonSound();
        System.out.println("back");
    }

    @FXML
    private void processFinishPurchase() {
        playButtonSound();
        int toPay = CartPageController.totalAmountToPay();
        // if coded discount was valid
        if (!codedDiscountField.getText().equals("")) {
            if (!ManagerAccountController.processViewDiscountCodes().contains(codedDiscountField.getText())) {
                messageLabel.setText("There is not such a code!");
                return;
            }
        }
        if (!codedDiscountField.getText().equals("")) {
            toPay *= (100 - CodedDiscount.getCodedDiscountByCode(codedDiscountField.getText()).getDiscountPercentage()) / 100;
        }
        if (toPay > (int) LoginPageController.getLoggedInAccount().getBalance()) {
            messageLabel.setText("Not enough money!");
            return;
        }
        LoginPanelController.getLoggedInAccount().setBalance(LoginPageController.getLoggedInAccount().getBalance() - toPay);
    }

    private void playButtonSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/media/sound/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString()));
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        finishPurchase.disableProperty().bind((isAddressFieldReady.and(isTelFieldReady).not()));
    }
}
