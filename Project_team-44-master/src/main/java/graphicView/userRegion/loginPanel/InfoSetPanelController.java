package graphicView.userRegion.loginPanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InfoSetPanelController {
    @FXML
    private TextField firstNameField = new TextField();
    @FXML
    private TextField secondNameField = new TextField();
    @FXML
    private TextField telField = new TextField();
    @FXML
    private TextField addressField = new TextField();
    @FXML
    private Label messageLabel = new Label();

    //    set on nextButton action
    public void goNext() {
        if (!firstNameField.getText().matches("[a-z|A-Z]+") ||
                !secondNameField.getText().matches("[a-z|A-Z]+") ||
                !telField.getText().matches("[0][9]\\d{9}") ||
                addressField.getText() == null) {

            messageLabel.setText("Invalid. Try Again");
        }
        LoginPanelController.registeringAccount.setFirstName(firstNameField.getText());
        LoginPanelController.registeringAccount.setLastName(secondNameField.getText());
        LoginPanelController.registeringAccount.setTelephoneNumber(telField.getText());
//        LoginPanelController.registeringAccount.setAddress(addressField.getText());
//        TODO: goto next scene
    }
}
