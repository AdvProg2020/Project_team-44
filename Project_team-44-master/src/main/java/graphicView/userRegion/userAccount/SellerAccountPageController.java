package graphicView.userRegion.userAccount;

import controller.LoginPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.Main;
import model.account.Account;
import model.account.Seller;

import java.io.IOException;

public class SellerAccountPageController {
    public Label nameOfSeller;
    public Label usernameOfSeller;
    public Hyperlink phoneNumberOfSeller;
    public Hyperlink emailOfSeller;
    public Label passwordOfSeller;
    public Label companyName;
    public Label companyAddress;
    public Hyperlink companyPhone;
    public ImageView imageOfSeller;

    public void writeInformationOfSeller() {
        Account currentAccount = LoginPageController.getLoggedInAccount();
        nameOfSeller.setText(currentAccount.getFirstName() + " " + currentAccount.getLastName());
        usernameOfSeller.setText(currentAccount.getUserName());
        phoneNumberOfSeller.setText(currentAccount.getTelephoneNumber());
        emailOfSeller.setText(currentAccount.getEMail());
        passwordOfSeller.setText(currentAccount.getPassword());
        Seller currentSeller = (Seller) currentAccount;
        companyName.setText(currentSeller.getCompanyAddress());
        companyAddress.setText(currentSeller.getCompanyAddress());
        companyPhone.setText(currentSeller.getCompanyTelephone());
    }

    public void viewSaleHistory() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("saleLog.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }

    public void editInformation() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }

    public void allProductsForSale() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }

    public void requestForAddProduct() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }

    public void viewOffs() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }
}
