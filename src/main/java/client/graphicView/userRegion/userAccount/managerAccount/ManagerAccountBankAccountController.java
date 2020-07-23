package client.graphicView.userRegion.userAccount.managerAccount;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import server.main.BankAPI;
import server.model.ShopBankAccount;

import java.io.IOException;

public class ManagerAccountBankAccountController {
    public TextField firstNameId;
    public TextField userNameId;
    public TextField lastNameId;
    public PasswordField repeatPasswordId;
    public PasswordField passwordId;
    public Label receiveMessageId;

    public void createAction(ActionEvent actionEvent) throws IOException {
        BankAPI bankAPI = new BankAPI();
        bankAPI.SendMessage("create_account " + firstNameId.getText() + " " + lastNameId.getText() + " " + userNameId.getText() + " " + passwordId.getText() + " " + repeatPasswordId.getText());
        String response = bankAPI.getInputStream();
        receiveMessageId.setText("");
        if (!response.matches("\\d+")) {
            receiveMessageId.setTextFill(Color.INDIANRED);
            receiveMessageId.setText(response);
        } else {
            new ShopBankAccount(firstNameId.getText(), lastNameId.getText(), userNameId.getText(), passwordId.getText(), Integer.parseInt(response));
            receiveMessageId.setTextFill(Color.FORESTGREEN);
            receiveMessageId.setText("Bank account successfully created....");
        }
    }
}
