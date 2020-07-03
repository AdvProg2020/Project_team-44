package graphicView.userRegion.userAccount.managerRequestions.addProduct;

import controller.ManagerAccountController;
import exception.RequestNotExistsException;
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
import model.product.Product;
import model.requests.Request;
import model.requests.RequestForAddProduct;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AddProductRequestInfoController implements Initializable {
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
    TableColumn<RequesterInfo, StringProperty> categoryNameColumn;
    @FXML
    TableColumn<RequesterInfo, IntegerProperty> priceColumn;
    @FXML
    TableColumn<RequesterInfo, StringProperty> explanationTextColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize the requester info
        sellerFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerFirstName"));
        sellerLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerLastName"));
        categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        explanationTextColumn.setCellValueFactory(new PropertyValueFactory<>("explanationText"));
        requesterInfoTableView.setItems(getRequesterInfo());
    }

    private ObservableList<RequesterInfo> getRequesterInfo() {
        ObservableList<RequesterInfo> requesterInfos = FXCollections.observableArrayList();
        requesterInfos.add(new RequesterInfo(((RequestForAddProduct) Request.getRequestById(AddProductRequestController.getCurrentRequestId())).getSeller().getFirstName(),
                ((RequestForAddProduct) Request.getRequestById(AddProductRequestController.getCurrentRequestId())).getSeller().getLastName(),
                ((RequestForAddProduct) Request.getRequestById(AddProductRequestController.getCurrentRequestId())).getCategory().getName(),
                (int) ((RequestForAddProduct) Request.getRequestById(AddProductRequestController.getCurrentRequestId())).getPrice(),
                ((RequestForAddProduct) Request.getRequestById(AddProductRequestController.getCurrentRequestId())).getExplanationText()
        ));
        return requesterInfos;
    }

    public class RequesterInfo {
        private StringProperty sellerFirstName;
        private StringProperty sellerLastName;
        private StringProperty categoryName;
        private IntegerProperty price;
        private StringProperty explanationText;

        public RequesterInfo(String sellerFirstName, String sellerLastName, String categoryName, int price, String explanationText) {
            this.sellerFirstName = new SimpleStringProperty(sellerFirstName);
            this.sellerLastName = new SimpleStringProperty(sellerLastName);
            this.categoryName = new SimpleStringProperty(categoryName);
            this.price = new SimpleIntegerProperty(price);
            this.explanationText = new SimpleStringProperty(explanationText);
        }

        public StringProperty sellerFirstNameProperty() {
            return sellerFirstName;
        }

        public StringProperty sellerLastNameProperty() {
            return sellerLastName;
        }

        public StringProperty categoryNameProperty() {
            return categoryName;
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
            ManagerAccountController.processAcceptRequestEach(AddProductRequestController.getCurrentRequestId());
        } catch (RequestNotExistsException | ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void declineRequest() {
        try {
            ManagerAccountController.processDeclineRequestEach(AddProductRequestController.getCurrentRequestId());
        } catch (RequestNotExistsException e) {
            e.printStackTrace();
        }
    }
}
