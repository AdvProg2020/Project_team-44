package graphicView.purchasePage;

import controller.LoginPageController;
import controller.ManagerAccountController;
import graphicView.cart.CartPageController;
import graphicView.userRegion.userAccount.sellerAccount.SellerWalletController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.CodedDiscount;
import model.account.Manager;
import model.account.Purchaser;
import model.account.Seller;
import model.product.Product;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PurchasePageController implements Initializable {
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
    @FXML
    private Label messageLabel = new Label();
    // disable till tel and address fields are filled

    public static int wage = Manager.getAllManagers().get(0).getWage();

    @FXML
    private void goPreviousScene() {
        playButtonSound();
        System.out.println("back");
    }

    @FXML
    private void creditCardAction() {

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
        if (toPay > (int) LoginPageController.getLoggedInAccount().getBalance() - SellerWalletController.atLeastAmount) {
            messageLabel.setText("Not enough money!");
            return;
        }
        Purchaser purchaser = (Purchaser) LoginPageController.getLoggedInAccount();
        purchaser.setBalance(purchaser.getBalance() - toPay);
        purchaser.createAndUpdateJson();
        for (Product product : ((Purchaser) LoginPageController.getLoggedInAccount()).getSellerSelectedForEachProduct().keySet()) {
            Seller seller = purchaser.getSellerSelectedForEachProduct().get(product);
            seller.setBalance(seller.getBalance() + ((100 - wage) * purchaser.getCart().get(product) * product.getPrice()) / 100);
            seller.createAndUpdateJson();
        }
        messageLabel.setText("Successfully payed from credit card.");
//        PurchaserAccountPageController.writeInformation();
        // پول به فروشنده ها اضافه شود
    }

    @FXML
    public void paymentAction(ActionEvent actionEvent) {
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
