package graphicView.userRegion.userAccount;

import controller.LoginPageController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.account.Account;
import model.account.Seller;

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
}
