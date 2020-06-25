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

import java.io.IOException;

public class ManagerAccountPageController {
    public ImageView imageOfManager;
    public Label nameOfManager;
    public Label usernameOfManager;
    public Hyperlink phoneNumberOfManager;
    public Hyperlink emailOfManager;
    public Label passwordOfManager;
    public void writeInformationForManager(){
        Account currentAccount = LoginPageController.getLoggedInAccount();
        nameOfManager.setText(currentAccount.getFirstName()+" "+currentAccount.getLastName());
        usernameOfManager.setText(currentAccount.getUserName());
        phoneNumberOfManager.setText(currentAccount.getTelephoneNumber());
        emailOfManager.setText(currentAccount.getEMail());
        passwordOfManager.setText(currentAccount.getEMail());
    }
    public void addCategory() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }
    public void viewCategories() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }
    public void editInformation() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }
    public void viewRequests() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }
    public void viewDiscountCodes() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("DiscountCodesPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }
    public void addDiscountCode() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }
    public void viewUsers() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }
    public void addManager() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }
}
