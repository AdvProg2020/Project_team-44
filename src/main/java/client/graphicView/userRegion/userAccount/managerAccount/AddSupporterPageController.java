package graphicView.userRegion.userAccount.managerAccount;

import controller.LoginPageController;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.Main;
import model.account.Supporter;

import java.io.IOException;

public class AddSupporterPageController {
    public TextField newFirstName;
    public TextField newLastName;
    public TextField newUserName;
    public TextField newPassWord;
    public TextField newEmail;
    public TextField newPhoneNumber;
    public Label alertMessage;

    @FXML
     public void addSupporter(){
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
    Supporter supporter = new Supporter(userName,firstName,lastName,email,phoneNumber,passWord);
        alertMessage.setText("create new supporter successful");
        alertMessage.setTextFill(Color.GREEN);
        alertMessage.setText("create new supporter successful");
}
    @FXML
    public void logout() throws IOException {
        AddSupporterPage.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }
    @FXML
    public void back() throws IOException {
        AddSupporterPage.primaryStage.close();
        ManagerAccountPage.display();
    }
}
