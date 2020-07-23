package client.graphicView.userRegion.userAccount.managerRequestions.removeProduct;

import server.controller.ManagerAccountController;
import server.exception.RequestNotExistsException;
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
import server.model.product.Product;
import server.model.requests.Request;
import server.model.requests.RequestForRemoveProduct;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class RemoveProductRequestInfoController implements Initializable {
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
    TableColumn<RequesterInfo, StringProperty> productNameColumn;
    @FXML
    TableColumn<RequesterInfo, StringProperty> sellerUsernameColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize the requester info
        sellerFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerFirstName"));
        sellerLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerLastName"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        sellerUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerUsername"));
        requesterInfoTableView.setItems(getRequesterInfo());
    }

    private ObservableList<RequesterInfo> getRequesterInfo() {
        ObservableList<RequesterInfo> requesterInfos = FXCollections.observableArrayList();
        requesterInfos.add(new RequesterInfo(((RequestForRemoveProduct) Request.getRequestById(RemoveProductRequestController.getCurrentRequestId())).getSeller().getFirstName(),
                ((RequestForRemoveProduct) Request.getRequestById(RemoveProductRequestController.getCurrentRequestId())).getSeller().getLastName(),
                ((RequestForRemoveProduct) Request.getRequestById(RemoveProductRequestController.getCurrentRequestId())).getProduct().getName(),
                ((RequestForRemoveProduct) Request.getRequestById(RemoveProductRequestController.getCurrentRequestId())).getSeller().getUserName()));
        return requesterInfos;
    }

    public class RequesterInfo {
        private StringProperty sellerFirstName;
        private StringProperty sellerLastName;
        private StringProperty productName;
        private StringProperty sellerUsername;

        public RequesterInfo(String sellerFirstName, String sellerLastName, String productName, String sellerUsername) {
            this.sellerFirstName = new SimpleStringProperty(sellerFirstName);
            this.sellerLastName = new SimpleStringProperty(sellerLastName);
            this.productName = new SimpleStringProperty(productName);
            this.sellerUsername = new SimpleStringProperty(sellerUsername);
        }

        public StringProperty sellerUsernameProperty() {
            return sellerUsername;
        }

        public StringProperty sellerFirstNameProperty() {
            return sellerFirstName;
        }


        public StringProperty sellerLastNameProperty() {
            return sellerLastName;
        }

        public StringProperty productNameProperty() {
            return productName;
        }
    }

    @FXML
    private void acceptRequest() {
        System.out.println(Product.getAllProducts().size());
        try {
            ManagerAccountController.processAcceptRequestEach(RemoveProductRequestController.getCurrentRequestId());
        } catch (RequestNotExistsException | ParseException e) {
            e.printStackTrace();
        }
        System.out.println(Product.getAllProducts().size());
    }

    @FXML
    private void declineRequest() {
        try {
            ManagerAccountController.processDeclineRequestEach(RemoveProductRequestController.getCurrentRequestId());
        } catch (RequestNotExistsException e) {
            e.printStackTrace();
        }
    }

}
