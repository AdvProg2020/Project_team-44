package client.graphicView.userRegion.userAccount.managerAccount;

import server.controller.LoginPageController;
import server.controller.ManagerAccountController;
import server.exception.ManagerFieldsNotExistException;
import client.graphicView.mainMenu.MainMenu;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import server.Main;

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
    public void applyChanges() throws  ManagerFieldsNotExistException {
        String newFirstName = newManagerFirstName.getText();
        String newLastName = newManagerLastName.getText();
        String newPassWord = newManagerPassWord.getText();
        String newEmail = newManagerEmail.getText();
        if(!newFirstName.matches("[a-zA-Z]+")){
            alertMessage.setText("Please enter server.Main valid first name!");
            return;
        }
        if(!newLastName.matches("[a-zA-Z]+")){
            alertMessage.setText("Please enter server.Main valid last name!");
            return;
        }
        if (!newPassWord.matches("\\w{8,}")) {
            alertMessage.setText("Please enter server.Main valid password!");
            return;
        }
        if (!newEmail.matches("\\w+[@]\\w+[.]\\w+")) {
            alertMessage.setText("Please enter server.Main valid email!");
            return;
        }
        ManagerAccountController.processEditFieldEach("FIRST_NAME",newFirstName);
        ManagerAccountController.processEditFieldEach("LAST_NAME",newLastName);
        ManagerAccountController.processEditFieldEach("EMAIL",newEmail);
        ManagerAccountController.processEditFieldEach("PASSWORD",newPassWord);;
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
