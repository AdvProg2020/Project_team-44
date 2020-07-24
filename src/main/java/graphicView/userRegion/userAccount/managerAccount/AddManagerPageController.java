package graphicView.userRegion.userAccount.managerAccount;

import controller.LoginPageController;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;
import model.account.Manager;

import java.io.IOException;

import static javafx.scene.paint.Color.GREEN;

public class AddManagerPageController {
    public TextField newFirstName;
    public TextField newLastName;
    public TextField newUserName;
    public TextField newPassWord;
    public TextField newEmail;
    public TextField newPhoneNumber;
    public Label alertMessage;

    @FXML
    public void addManager(){
        String firstName = newFirstName.getText();
        String lastName = newLastName.getText();
        String userName = newUserName.getText();
        String passWord = newPassWord.getText();
        String email = newEmail.getText();
        String phoneNumber = newPhoneNumber.getText();
        if(!firstName.matches("[a-zA-Z]+")){
            alertMessage.setText("Please enter a valid first name!");
            return;
        }
        if(!lastName.matches("[a-zA-Z]+")){
            alertMessage.setText("Please enter a valid last name!");
            return;
        }
        if (!userName.matches("\\w{8,}")) {
            alertMessage.setText("Please enter a valid username!");
            return;
        }
        if (!passWord.matches("\\w{8,}")) {
            alertMessage.setText("Please enter a valid password!");
            return;
        }
        if (!email.matches("\\w+[@]\\w+[.]\\w+")) {
            alertMessage.setText("Please enter a valid email!");
            return;
        }
        if(!phoneNumber.matches("09\\d\\d\\d\\d\\d\\d\\d\\d\\d")){
            alertMessage.setText("Please enter a valid tel!");
            return;
        }
        Manager manager = new Manager(userName,firstName,lastName,email,phoneNumber,passWord);
        alertMessage.setText("");
        alertMessage.setTextFill(GREEN);
        alertMessage.setText("create new manager successful");
    }
    @FXML
    public void logout() throws IOException {
        AddManagerPage.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }
    @FXML
    public void back() throws IOException {
        AddManagerPage.primaryStage.close();
        ManagerAccountPage.display();
    }
}
