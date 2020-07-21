package graphicView.userRegion.userAccount.managerAccount;

import controller.LoginPageController;
import controller.ManagerAccountController;
import exception.UsernameNotExistsException;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewAllAccountsPageController implements Initializable {
    @FXML
    public TableView<Account> viewAllAccountsTable;
    @FXML
    public TableColumn<Account, StringProperty> AccountUserName;
    @FXML
    public TableColumn<Account, StringProperty> AccountEmail;
    @FXML
    public TableColumn<Account, StringProperty> AccountPhone;
    @FXML
    public TableColumn<Account, CheckBox> removeUserButton;
    @FXML
    public TableColumn<Account, CheckBox> addMangerButton;


    @FXML
    public void logout() throws IOException {
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        ViewAllAccountsPage.primaryStage.close();
        MainMenu.display(Main.window);
    }
    @FXML
    public void back() throws IOException {
        ViewAllAccountsPage.primaryStage.close();
        ManagerAccountPage.display();
    }
    @FXML
    public void checkout() throws UsernameNotExistsException {
        for (Account account : getAccount()) {
            if(account.removeUser.isSelected()){
                ManagerAccountController.processDeleteUserEach(account.userName.toString());
            }
        }
    }
    ObservableList<Account> getAccount(){
        ObservableList<Account> allAccounts = FXCollections.observableArrayList();
        for (model.account.Account account : model.account.Account.getAllAccounts()) {
            allAccounts.add(new Account(account.getFirstName()+" "+account.getLastName(),
                    account.getEMail(),account.getTelephoneNumber()));
        }
        return allAccounts;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AccountUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        AccountEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        AccountPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        removeUserButton.setCellValueFactory(new PropertyValueFactory<>("removeUser"));
        addMangerButton.setCellValueFactory(new PropertyValueFactory<>("addManager"));
        viewAllAccountsTable.setItems(getAccount());
   }
    public class Account{
        private StringProperty userName;
        private StringProperty email;
        private StringProperty phoneNumber;
        private CheckBox removeUser;
        private CheckBox addManager;

        public String getUserName() {
            return userName.get();
        }

        public StringProperty userNameProperty() {
            return userName;
        }

        public String getEmail() {
            return email.get();
        }

        public StringProperty emailProperty() {
            return email;
        }

        public String getPhoneNumber() {
            return phoneNumber.get();
        }

        public StringProperty phoneNumberProperty() {
            return phoneNumber;
        }

        public CheckBox getRemoveUser() {
            return removeUser;
        }

        public CheckBox getAddManager() {
            return addManager;
        }

        Account(String userName , String email , String phoneNumber){
            this.userName = new SimpleStringProperty(userName);
            this.email = new SimpleStringProperty(email);
            this.phoneNumber = new SimpleStringProperty(phoneNumber);
            this.removeUser = new CheckBox();
            this.addManager = new CheckBox();

        }
    }
}
