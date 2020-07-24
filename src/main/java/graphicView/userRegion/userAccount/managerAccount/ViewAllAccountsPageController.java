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
import java.util.ArrayList;
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
    public TableColumn<Account,StringProperty> isUserOnline;


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
        for (Account account : allAccounts) {
            if(account.removeUser.isSelected()){
                ManagerAccountController.processDeleteUserEach(account.userName.getValue());
            }
        }
    }
    ObservableList<Account> getAccount(){
        ObservableList<Account> allAccounts = FXCollections.observableArrayList();
        for (model.account.Account account : model.account.Account.getAllAccounts()) {
            allAccounts.add(new Account(account.getUserName(),
                    account.getEMail(),
                    account.getTelephoneNumber() , account.isLoggedIn()));
        }
        return allAccounts;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AccountUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        AccountEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        AccountPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        removeUserButton.setCellValueFactory(new PropertyValueFactory<>("removeUser"));
        isUserOnline.setCellValueFactory(new PropertyValueFactory<>("isOnline"));
        viewAllAccountsTable.setItems(getAccount());
   }
    private ArrayList<Account> allAccounts = new ArrayList<>();
    public class Account{
        private StringProperty userName;
        private StringProperty email;
        private StringProperty phoneNumber;
        private CheckBox removeUser;
        private StringProperty isOnline;
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

        public String getIsOnline() {
            return isOnline.get();
        }

        public StringProperty isOnlineProperty() {
            return isOnline;
        }

        Account(String userName , String email , String phoneNumber , boolean isOnline){
            this.userName = new SimpleStringProperty(userName);
            this.email = new SimpleStringProperty(email);
            this.phoneNumber = new SimpleStringProperty(phoneNumber);
            this.removeUser = new CheckBox();
            if(isOnline){
                this.isOnline = new SimpleStringProperty("yes");
            }
            else
                this.isOnline = new SimpleStringProperty("no");
            allAccounts.add(this);

        }
    }
}
