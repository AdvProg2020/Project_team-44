package client.graphicView.userRegion.userAccount.sellerAccount;

import client.Main;
import server.controller.LoginPageController;
import server.controller.SellerAccountController;
import server.exception.SellerFieldsNotExistException;
import client.graphicView.mainMenu.MainMenu;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static javafx.scene.paint.Color.GREEN;

public class SellerEditInfoPageController {
    @FXML
    public TextField newSellerFirstName;
    @FXML
    public TextField newSellerLastName;
    @FXML
    public TextField newSellerUsername;
    @FXML
    public TextField newSellerPassword;
    @FXML
    public TextField newSellerEmail;
    @FXML
    public TextField newSellerPhoneNumber;
    @FXML
    public TextField newSellerCompanyName;
    @FXML
    public TextField newSellerCompanyPhoneNumber;
    @FXML
    public TextField newSellerCompanyAddress;
    @FXML
    public Label alertMessage;
    @FXML
    public void applyChanges() throws SellerFieldsNotExistException {
        String newFirstName = newSellerFirstName.getText();
        String newLastName = newSellerLastName.getText();
        String newUsername = newSellerUsername.getText();
        String newPassWord = newSellerPassword.getText();
        String newEmail = newSellerEmail.getText();
        String newPhoneNumber = newSellerPhoneNumber.getText();
        String newCompanyName = newSellerCompanyName.getText();
        String newCompanyPhoneNumber = newSellerCompanyPhoneNumber.getText();
        String newCompanyAddress = newSellerCompanyAddress.getText();
        if(!newFirstName.matches("[a-zA-Z]+")){
            alertMessage.setText("Please enter server.main.Main valid first name!");
            return;
        }
        if(!newLastName.matches("[a-zA-Z]+")){
            alertMessage.setText("Please enter server.main.Main valid last name!");
            return;
        }
        if (!newUsername.matches("\\w{8,}")) {
                alertMessage.setText("Please enter server.main.Main valid username!");
                return;
            }
        if (!newPassWord.matches("\\w{8,}")) {
            alertMessage.setText("Please enter server.main.Main valid password!");
            return;
        }
        if (!newEmail.matches("\\w+[@]\\w+[.]\\w+")) {
            alertMessage.setText("Please enter server.main.Main valid email!");
            return;
        }
        if(!newPhoneNumber.matches("09\\d\\d\\d\\d\\d\\d\\d\\d\\d")){
            alertMessage.setText("Please enter server.main.Main valid tel!");
            return;
        }
        if(!newCompanyName.matches("[a-zA-Z]+")){
            alertMessage.setText("Please enter server.main.Main valid company name!");
            return;
        }
        if(!newCompanyPhoneNumber.matches("\\d{11}")){
            alertMessage.setText("Please enter server.main.Main valid company phone number!");
            return;
        }
        if(!newCompanyAddress.matches("\\w+")){
            alertMessage.setText("Please enter server.main.Main valid company address!");
            return;
        }
        SellerAccountController.processEditFieldEach("FIRST_NAME", newFirstName);
        SellerAccountController.processEditFieldEach("LAST_NAME", newLastName);
        SellerAccountController.processEditFieldEach("USER_NAME", newUsername);
        SellerAccountController.processEditFieldEach("PASSWORD", newPassWord);
        SellerAccountController.processEditFieldEach("EMAIL", newEmail);
        SellerAccountController.processEditFieldEach("TELEPHONE_NUMBER", newPhoneNumber);
        SellerAccountController.processEditFieldEach("COMPANY_NAME", newCompanyName);
        SellerAccountController.processEditFieldEach("COMPANY_TELEPHONE_NUMBER", newCompanyPhoneNumber);
        SellerAccountController.processEditFieldEach("COMPANY_ADDRESS", newCompanyAddress);
        alertMessage.setText("");
        alertMessage.setTextFill(GREEN);
        alertMessage.setText("Edit info successful");
    }
    @FXML
    public void logout() throws IOException {
        SellerEditInfoPage.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }
    @FXML
    public void back() throws IOException {
        SellerEditInfoPage.primaryStage.close();
        SellerAccountPage.display();
    }
}
