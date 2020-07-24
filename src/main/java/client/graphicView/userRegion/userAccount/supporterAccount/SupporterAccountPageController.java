package graphicView.userRegion.userAccount.supporterAccount;

import controller.LoginPageController;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.Main;
import model.account.Account;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupporterAccountPageController implements Initializable {
    public Label supporterName;
    public Label supporterUserName;
    public Label SupporterPassWord;
    public Label supporterPhoneNumber;
    public Label supporterEmail;

    @FXML
    public void logout() throws IOException {
        SupporterAccountPage.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }
    @FXML
    public void chat(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Account loggedInAccount = LoginPageController.getLoggedInAccount();
        supporterName.setText(loggedInAccount.getFirstName()+" "+loggedInAccount.getLastName());
        supporterPhoneNumber.setText(loggedInAccount.getTelephoneNumber());
        supporterUserName.setText(loggedInAccount.getUserName());
        SupporterPassWord.setText(loggedInAccount.getPassword());
        supporterEmail.setText(loggedInAccount.getEMail());
    }
}
