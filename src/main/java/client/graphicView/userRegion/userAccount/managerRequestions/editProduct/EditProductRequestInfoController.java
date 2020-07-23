package client.graphicView.userRegion.userAccount.managerRequestions.editProduct;

import server.controller.ManagerAccountController;
import server.exception.RequestNotExistsException;
import client.graphicView.userRegion.userAccount.managerRequestions.addProduct.AddProductRequestController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import server.model.requests.Request;
import server.model.requests.RequestForAddProduct;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class EditProductRequestInfoController implements Initializable {
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
    TableColumn<RequesterInfo, IntegerProperty> priceColumn;
    @FXML
    TableColumn<RequesterInfo, StringProperty> explanationTextColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize the requester info
        sellerFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerFirstName"));
        sellerLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerLastName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        explanationTextColumn.setCellValueFactory(new PropertyValueFactory<>("explanationText"));
        requesterInfoTableView.setItems(getRequesterInfo());
    }

    private ObservableList<RequesterInfo> getRequesterInfo() {
        ObservableList<RequesterInfo> requesterInfos = FXCollections.observableArrayList();
        requesterInfos.add(new RequesterInfo(((RequestForAddProduct) Request.getRequestById(AddProductRequestController.getCurrentRequestId())).getSeller().getFirstName(),
                ((RequestForAddProduct) Request.getRequestById(AddProductRequestController.getCurrentRequestId())).getSeller().getLastName(),
                (int) ((RequestForAddProduct) Request.getRequestById(AddProductRequestController.getCurrentRequestId())).getPrice(),
                ((RequestForAddProduct) Request.getRequestById(AddProductRequestController.getCurrentRequestId())).getExplanationText()
        ));
        return requesterInfos;
    }

    public class RequesterInfo {
        private StringProperty sellerFirstName;
        private StringProperty sellerLastName;
        private IntegerProperty price;
        private StringProperty explanationText;

        public RequesterInfo(String sellerFirstName, String sellerLastName, int price, String explanationText) {
            this.sellerFirstName = new SimpleStringProperty(sellerFirstName);
            this.sellerLastName = new SimpleStringProperty(sellerLastName);
            this.price = new SimpleIntegerProperty(price);
            this.explanationText = new SimpleStringProperty(explanationText);
        }

        public StringProperty sellerFirstNameProperty() {
            return sellerFirstName;
        }

        public StringProperty sellerLastNameProperty() {
            return sellerLastName;
        }

        public IntegerProperty priceProperty() {
            return price;
        }

        public StringProperty explanationTextProperty() {
            return explanationText;
        }
    }

    @FXML
    private void acceptRequest() {
        try {
            ManagerAccountController.processAcceptRequestEach(EditProductRequestController.getCurrentRequestId());
        } catch (RequestNotExistsException | ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void declineRequest() {
        try {
            ManagerAccountController.processDeclineRequestEach(EditProductRequestController.getCurrentRequestId());
        } catch (RequestNotExistsException e) {
            e.printStackTrace();
        }
    }
}
