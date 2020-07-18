package graphicView.userRegion.userAccount.purchaserAccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.BankAPI;
import model.ShopBankAccount;

import java.io.IOException;

public class PurchaserBankAccountController {
    @FXML
    public TextField firstNameId;
    @FXML
    public TextField userNameId;
    @FXML
    public TextField lastNameId;
    @FXML
    public PasswordField repeatPasswordId;
    @FXML
    public PasswordField passwordId;
    @FXML
    public Label receiveMessageId;

    @FXML
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
