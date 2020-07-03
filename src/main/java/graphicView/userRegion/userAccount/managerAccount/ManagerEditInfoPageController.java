package graphicView.userRegion.userAccount.managerAccount;

import controller.LoginPageController;
import controller.ManagerAccountController;
import controller.SellerAccountController;
import exception.ManagerFieldsNotExistException;
import exception.SellerFieldsNotExistException;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import graphicView.userRegion.userAccount.sellerAccount.SellerAccountPage;
import graphicView.userRegion.userAccount.sellerAccount.SellerEditInfoPage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;

import java.io.IOException;

import static javafx.scene.paint.Color.GREEN;

public class ManagerEditInfoPageController {

    public TextField newManagerFirstName;
    public TextField newManagerLastName;
    public TextField newManagerUsername;
    public TextField newManagerPassWord;
    public TextField newManagerEmail;
    public Label alertMessage;
    @FXML
    public void applyChanges() throws SellerFieldsNotExistException, ManagerFieldsNotExistException {
        String newFirstName = newManagerFirstName.getText();
        String newLastName = newManagerLastName.getText();
        String newUsername = newManagerUsername.getText();
        String newPassWord = newManagerPassWord.getText();
        String newEmail = newManagerEmail.getText();
        if(!newFirstName.matches("[a-zA-Z]+")){
            alertMessage.setText("Please enter a valid first name!");
            return;
        }
        if(!newLastName.matches("[a-zA-Z]+")){
            alertMessage.setText("Please enter a valid last name!");
            return;
        }
        if (!newUsername.matches("\\w{8,}")) {
            alertMessage.setText("Please enter a valid username!");
            return;
        }
        if (!newPassWord.matches("\\w{8,}")) {
            alertMessage.setText("Please enter a valid password!");
            return;
        }
        if (!newEmail.matches("\\w+[@]\\w+[.]\\w+")) {
            alertMessage.setText("Please enter a valid email!");
            return;
        }
        ManagerAccountController.processEditFieldEach("firstName",newFirstName);
        ManagerAccountController.processEditFieldEach("lastName",newLastName);
        ManagerAccountController.processEditFieldEach("EMail",newEmail);
        ManagerAccountController.processEditFieldEach("password",newPassWord);;
        alertMessage.setText("");
        alertMessage.setTextFill(GREEN);
        alertMessage.setText("Edit info successful");
    }
    @FXML
    public void logout() throws IOException {
        ManagerEditInfoPage.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }
    @FXML
    public void back() throws IOException {
        ManagerEditInfoPage.primaryStage.close();
        ManagerAccountPage.display();
    }
}
