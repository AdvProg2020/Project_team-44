package graphicView.userRegion.userAccount;

import controller.LoginPageController;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import main.Main;
import model.account.Account;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerAccountPageController implements Initializable {
    @FXML
    public ImageView managerImage;
    @FXML
    public Label managerName;
    @FXML
    public Label managerUsername;
    @FXML
    public Label managerPhoneNumber;
    @FXML
    public Label managerEmail;
    @FXML
    public Label managerPassword;
    @FXML
    private MenuButton requests;
    @FXML
    private Button viewAllCategories;
    @FXML
    private Button viewAllDiscountCodes;
    @FXML
    private Button viewAllAccounts;
    @FXML
    private Button editInfo;
    @FXML
    private MenuItem addOff;
    @FXML
    private MenuItem editOff;
    @FXML
    private MenuItem addProduct;
    @FXML
    private MenuItem editProduct;
    @FXML
    private MenuItem removeProduct;
    @FXML
    private MenuItem addSeller;
    @FXML
    private Button backButton;
    @FXML
    private Button logout;

    @FXML
    private void logout() throws IOException {
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        ManagerAccountPage.primaryStage.close();
        MainMenu.display(Main.window);
    }
    public void writeInformationForManager() {
        Account currentAccount = LoginPageController.getLoggedInAccount();
        managerName.setText(currentAccount.getFirstName() + " " + currentAccount.getLastName());
        managerUsername.setText(currentAccount.getUserName());
        managerPhoneNumber.setText(currentAccount.getTelephoneNumber());
        managerEmail.setText(currentAccount.getEMail());
        managerPassword.setText(currentAccount.getEMail());
//        managerImage =new ImageView()
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        writeInformationForManager();
    }

    public void goPreviousScene(ActionEvent actionEvent) {
    }
}
