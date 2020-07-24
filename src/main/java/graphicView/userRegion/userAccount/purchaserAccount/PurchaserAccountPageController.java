package graphicView.userRegion.userAccount.purchaserAccount;

import controller.LoginPageController;
import graphicView.buyLogPage.BuyLogPage;
import graphicView.cart.CartPage;
import graphicView.discountCodes.DiscountCodesPage;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import main.Main;
import model.account.Account;
import model.account.Purchaser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PurchaserAccountPageController implements Initializable {
    @FXML
    private Button contactSupporters;
    @FXML
    private Label purchaserName;
    @FXML
    private Label purchaserUserName;
    @FXML
    private Label purchaserBalance;
    @FXML
    private Label purchaserPhoneNumber;
    @FXML
    private Label purchaserEmail;
    @FXML
    private Label purchaserPassword;
    @FXML
    private ImageView purchaserImage;
    @FXML
    private Button viewBuysHistory = new Button();
    @FXML
    private Button viewCart = new Button();
    @FXML
    private Button viewDiscountCodes = new Button();
    @FXML
    private Button editInfo;
    @FXML
    private Button logout;

    @FXML
    private void logout() throws IOException {
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        PurchaserAccountPage.primaryStage.close();
        MainMenu.display(Main.window);
    }

    @FXML
    private void processViewSupporters() {
        // button sound TODO
        try {
            PurchaserContactSupportersPage.display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void processViewCart() {
        // button sound TODO
        try {
            CartPage.display();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    private void processBuysHistory() {
        // button sound TODO
        try {
            BuyLogPage.display();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    private void processViewDiscountCodes() {
        // button sound TODO
        try {
            DiscountCodesPage.display();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void writeInformation() {
        Account currentAccount = LoginPageController.getLoggedInAccount();
        purchaserName.setText(currentAccount.getFirstName() + " " + currentAccount.getLastName());
        purchaserUserName.setText(currentAccount.getUserName());
        purchaserPhoneNumber.setText(currentAccount.getTelephoneNumber());
        purchaserEmail.setText(currentAccount.getEMail());
        purchaserPassword.setText(currentAccount.getPassword());
        new Thread(() -> purchaserBalance.setText(String.valueOf(currentAccount.getBalance()))).start();
//        purchaserImage.setImage();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        writeInformation();
    }

    public void wallet() {
        try {
            PurchaserWallet.display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNewBankAccountAction() {
        try {
            PurchaserBankAccount.display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToAuctionPageAction(ActionEvent actionEvent) throws IOException {
        PurchaserAccountPage.primaryStage.close();
        //PurchaserAuction.display();
    }
}
