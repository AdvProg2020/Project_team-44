package client.graphicView.userRegion.userAccount.sellerAccount;

import client.Main;
import server.controller.LoginPageController;
import client.graphicView.mainMenu.MainMenu;
import client.graphicView.sellLogPage.SellLogPage;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import server.model.account.Account;
import server.model.account.Seller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SellerAccountPageController implements Initializable {
    @FXML
    public Label sellerName;
    @FXML
    public Label sellerUsername;
    @FXML
    public Label sellerPhoneNumber;
    @FXML
    public Label sellerEmail;
    @FXML
    public Label sellerPassword;
    @FXML
    public Label companyName;
    @FXML
    public Label companyAddress;
    @FXML
    public Label companyPhone;
    @FXML
    public ImageView sellerImage;
    @FXML
    private Button requestForAddProduct;
    @FXML
    private Button allProductsForSale;
    @FXML
    private Button viewOffs;
    @FXML
    private Button viewSalesHistory;
    @FXML
    private Button editInfo;
    @FXML
    private Button logout;

    @FXML
    private void logout() throws IOException {
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        SellerAccountPage.primaryStage.close();
        MainMenu.display(Main.window);
    }

    public void writeInformationOfSeller() {
        Account currentAccount = LoginPageController.getLoggedInAccount();
        sellerName.setText(currentAccount.getFirstName() + " " + currentAccount.getLastName());
        sellerUsername.setText(currentAccount.getUserName());
        sellerPhoneNumber.setText(currentAccount.getTelephoneNumber());
        sellerEmail.setText(currentAccount.getEMail());
        sellerPassword.setText(currentAccount.getPassword());
        Seller currentSeller = (Seller) currentAccount;
        companyName.setText(currentSeller.getCompanyAddress());
        companyAddress.setText(currentSeller.getCompanyAddress());
        companyPhone.setText(currentSeller.getCompanyTelephone());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        writeInformationOfSeller();
    }

    @FXML
    public void editInformation() throws IOException {
        SellerAccountPage.primaryStage.close();
        SellerEditInfoPage.display();
    }

    @FXML
    public void viewSaleHistory() throws IOException {
        SellerAccountPage.primaryStage.close();
        SellLogPage.display();
    }

    @FXML
    public void viewAllProductsForSale() throws IOException {
        SellerAccountPage.primaryStage.close();
        ViewAllProductsForSellerPage.display();
    }

    @FXML
    public void requestForAddProduct() throws IOException {
        SellerAccountPage.primaryStage.close();
        RequestForAddProductPage.display();
    }

    @FXML
    public void editInfo() throws IOException {
        SellerAccountPage.primaryStage.close();
        SellerEditInfoPage.display();
    }

    @FXML
    public void wallet(ActionEvent actionEvent) throws IOException {
        SellerAccountPage.primaryStage.close();
        SellerWallet.display();
    }

    @FXML
    public void createNewBankAccountAction() throws IOException {
        SellerAccountPage.primaryStage.close();
        SellerBankAccount.display();
    }

    @FXML
    public void createAuction() throws IOException {
        SellerAccountPage.primaryStage.close();
        SellerAuction.display();
    }

}
