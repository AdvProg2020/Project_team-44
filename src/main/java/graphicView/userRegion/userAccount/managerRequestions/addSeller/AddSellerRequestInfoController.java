package graphicView.userRegion.userAccount.managerRequestions.addSeller;

import controller.ManagerAccountController;
import exception.RequestNotExistsException;
import graphicView.userRegion.userAccount.managerRequestions.addProduct.AddProductRequestController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.requests.Request;
import model.requests.RequestForSeller;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AddSellerRequestInfoController implements Initializable {
    @FXML
    Button acceptButton = new Button();
    @FXML
    Button declineButton = new Button();

    // only have 1 row
    @FXML
    TableView<RequesterInfo> requesterInfoTableView;
    @FXML
    TableColumn<RequesterInfo, StringProperty> sellerFirstNameColumn;
    @FXML
    TableColumn<RequesterInfo, StringProperty> sellerLastNameColumn;
    @FXML
    TableColumn<RequesterInfo, StringProperty> sellerUsernameColumn;
    @FXML
    TableColumn<RequesterInfo, StringProperty> companyNameColumn;
    @FXML
    TableColumn<RequesterInfo, StringProperty> companyAddressColumn;
    @FXML
    TableColumn<RequesterInfo, StringProperty> companyTelephoneColumn;
    @FXML
    TableColumn<RequesterInfo, StringProperty> eMailColumn;
    @FXML
    TableColumn<RequesterInfo, StringProperty> telephoneNumberColumn;
    @FXML
    TableColumn<RequesterInfo, StringProperty> passwordColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize the requester info
        sellerFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerFirstName"));
        sellerLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerLastName"));
        sellerUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerUsername"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        companyAddressColumn.setCellValueFactory(new PropertyValueFactory<>("companyAddress"));
        companyTelephoneColumn.setCellValueFactory(new PropertyValueFactory<>("companyTelephone"));
        eMailColumn.setCellValueFactory(new PropertyValueFactory<>("eMail"));
        telephoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        requesterInfoTableView.setItems(getRequesterInfo());
    }

    private ObservableList<RequesterInfo> getRequesterInfo() {
        ObservableList<RequesterInfo> requesterInfos = FXCollections.observableArrayList();
        requesterInfos.add(new RequesterInfo(((RequestForSeller) Request.getRequestById(AddSellerRequestController.getCurrentRequestId())).getFirstName(),
                ((RequestForSeller) Request.getRequestById(AddSellerRequestController.getCurrentRequestId())).getLastName(),
                ((RequestForSeller) Request.getRequestById(AddSellerRequestController.getCurrentRequestId())).getUserName(),
                ((RequestForSeller) Request.getRequestById(AddSellerRequestController.getCurrentRequestId())).getCompanyName(),
                ((RequestForSeller) Request.getRequestById(AddSellerRequestController.getCurrentRequestId())).getCompanyAddress(),
                ((RequestForSeller) Request.getRequestById(AddSellerRequestController.getCurrentRequestId())).getCompanyTelephone(),
                ((RequestForSeller) Request.getRequestById(AddSellerRequestController.getCurrentRequestId())).getEMail(),
                ((RequestForSeller) Request.getRequestById(AddSellerRequestController.getCurrentRequestId())).getTelephoneNumber(),
                ((RequestForSeller) Request.getRequestById(AddSellerRequestController.getCurrentRequestId())).getPassword()));
        return requesterInfos;
    }

    public class RequesterInfo {
        private StringProperty sellerFirstName;
        private StringProperty sellerLastName;
        private StringProperty sellerUsername;
        private StringProperty companyName;
        private StringProperty companyAddress;
        private StringProperty companyTelephone;
        private StringProperty eMail;
        private StringProperty telephoneNumber;
        private StringProperty password;

        public RequesterInfo(String sellerFirstName, String sellerLastName, String sellerUsername, String companyName, String companyAddress, String companyTelephone, String eMail, String telephoneNumber, String password) {
            this.sellerFirstName = new SimpleStringProperty(sellerFirstName);
            this.sellerLastName = new SimpleStringProperty(sellerLastName);
            this.sellerUsername = new SimpleStringProperty(sellerUsername);
            this.companyName = new SimpleStringProperty(companyName);
            this.companyAddress = new SimpleStringProperty(companyAddress);
            this.companyTelephone = new SimpleStringProperty(companyTelephone);
            this.eMail = new SimpleStringProperty(eMail);
            this.telephoneNumber = new SimpleStringProperty(telephoneNumber);
            this.password = new SimpleStringProperty(password);
        }

        public StringProperty sellerFirstNameProperty() {
            return sellerFirstName;
        }

        public StringProperty sellerLastNameProperty() {
            return sellerLastName;
        }


        public StringProperty sellerUsernameProperty() {
            return sellerUsername;
        }


        public StringProperty companyNameProperty() {
            return companyName;
        }

        public StringProperty companyAddressProperty() {
            return companyAddress;
        }

        public StringProperty companyTelephoneProperty() {
            return companyTelephone;
        }

        public StringProperty eMailProperty() {
            return eMail;
        }

        public StringProperty telephoneNumberProperty() {
            return telephoneNumber;
        }

        public StringProperty passwordProperty() {
            return password;
        }
    }
    @FXML
    private void acceptRequest() {
        try {
            ManagerAccountController.processAcceptRequestEach(AddSellerRequestController.getCurrentRequestId());
        } catch (RequestNotExistsException | ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void declineRequest() {
        try {
            ManagerAccountController.processDeclineRequestEach(AddSellerRequestController.getCurrentRequestId());
        } catch (RequestNotExistsException e) {
            e.printStackTrace();
        }
    }
}
