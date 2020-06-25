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

public class PurchaserAccountPageController {
    public Label nameOfPurchaser;
    public Label usernameOfPurchaser;
    public Label phoneNumberOfPurchaser;
    public ImageView imageOfPurchaser;
    public Hyperlink emailOfPurchaser;
    public Label passwordOfPurchaser;
    public Label balanceOfPurchaser;

    public void writeInformationOfPurchaser(){
        Account currentAccount = LoginPageController.getLoggedInAccount();
        nameOfPurchaser.setText(currentAccount.getFirstName()+" "+currentAccount.getLastName());
        usernameOfPurchaser.setText(currentAccount.getUserName());
        phoneNumberOfPurchaser.setText(currentAccount.getTelephoneNumber());
        emailOfPurchaser.setText(currentAccount.getEMail());
        passwordOfPurchaser.setText(currentAccount.getPassword());
        balanceOfPurchaser.setText(String.valueOf(currentAccount.getBalance()));
    }
    public void viewCartForPurchaser() throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Main.window.setScene(new Scene(root, 800, 600));
        Main.window.show();
    }

}
