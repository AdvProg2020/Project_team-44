package client.graphicView.userRegion.userAccount.managerRequestions.addOff;

import server.controller.ManagerAccountController;
import server.exception.RequestNotExistsException;
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
import server.model.product.Product;
import server.model.requests.Request;
import server.model.requests.RequestForAddOff;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

public class OffRequestInfoController implements Initializable {
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
    TableColumn<RequesterInfo, IntegerProperty> discountPercentageColumn;
    @FXML
    TableColumn<RequesterInfo, Date> initialDateColumn;
    @FXML
    TableColumn<RequesterInfo, Date> finalDateColumn;

    public class RequesterInfo {
        private StringProperty sellerFirstName;
        private StringProperty sellerLastName;
        private IntegerProperty discountPercentage;
        private Date initialDate;
        private Date finalDate;

        public RequesterInfo(String sellerFirstName, String sellerLastName, int discountPercentage, Date initialDate, Date finalDate) {
            this.sellerFirstName = new SimpleStringProperty(sellerFirstName);
            this.sellerLastName = new SimpleStringProperty(sellerLastName);
            this.discountPercentage = new SimpleIntegerProperty(discountPercentage);
            this.initialDate = initialDate;
            this.finalDate = finalDate;
        }

        public StringProperty sellerFirstNameProperty() {
            return sellerFirstName;
        }

        public StringProperty sellerLastNameProperty() {
            return sellerLastName;
        }

        public IntegerProperty discountPercentageProperty() {
            return discountPercentage;
        }

        public Date getInitialDate() {
            return initialDate;
        }

        public Date getFinalDate() {
            return finalDate;
        }
    }

    @FXML
    TableView<OfferProducts> productsTableView;
    @FXML
    TableColumn<OfferProducts, StringProperty> productNameColumn;
    @FXML
    TableColumn<OfferProducts, StringProperty> productIdColumn;

    public class OfferProducts {
        private StringProperty productName;
        private StringProperty productId;

        public OfferProducts(String productName, String productId) {
            this.productName = new SimpleStringProperty(productName);
            this.productId = new SimpleStringProperty(productId);
        }

        public StringProperty productNameProperty() {
            return productName;
        }

        public StringProperty productIdProperty() {
            return productId;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize the requester info
        sellerFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerFirstName"));
        sellerLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerLastName"));
        discountPercentageColumn.setCellValueFactory(new PropertyValueFactory<>("discountPercentage"));
        initialDateColumn.setCellValueFactory(new PropertyValueFactory<>("initialDate"));
        finalDateColumn.setCellValueFactory(new PropertyValueFactory<>("finalDate"));
        requesterInfoTableView.setItems(getRequesterInfo());
        // initialize the products table
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productsTableView.setItems(getProducts());
    }

    private ObservableList<OfferProducts> getProducts() {
        ObservableList<OfferProducts> offerProducts = FXCollections.observableArrayList();
        for (Product product : ((RequestForAddOff) Request.getRequestById(AddOffRequestController.getCurrentRequestId())).getProductList()) {
            offerProducts.add(new OfferProducts(product.getName(),
                    product.getProductID()));
        }
        return offerProducts;
    }

    private ObservableList<RequesterInfo> getRequesterInfo() {
        ObservableList<RequesterInfo> requesterInfos = FXCollections.observableArrayList();
        requesterInfos.add(new RequesterInfo(((RequestForAddOff) Request.getRequestById(AddOffRequestController.getCurrentRequestId())).getSeller().getFirstName(),
                ((RequestForAddOff) Request.getRequestById(AddOffRequestController.getCurrentRequestId())).getSeller().getLastName(),
                ((RequestForAddOff) Request.getRequestById(AddOffRequestController.getCurrentRequestId())).getDiscountPercentage(),
                ((RequestForAddOff) Request.getRequestById(AddOffRequestController.getCurrentRequestId())).getInitialDate(),
                ((RequestForAddOff) Request.getRequestById(AddOffRequestController.getCurrentRequestId())).getFinalDate()));
        return requesterInfos;
    }

    @FXML
    private void acceptRequest() {
        try {
            ManagerAccountController.processAcceptRequestEach(AddOffRequestController.getCurrentRequestId());
        } catch (RequestNotExistsException | ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void declineRequest() {
        System.out.println(RequestForAddOff.getAllRequestsForAddOff().size());
        try {
            ManagerAccountController.processDeclineRequestEach(AddOffRequestController.getCurrentRequestId());
        } catch (RequestNotExistsException e) {
            e.printStackTrace();
        }
        System.out.println(RequestForAddOff.getAllRequestsForAddOff().size());
    }
}
