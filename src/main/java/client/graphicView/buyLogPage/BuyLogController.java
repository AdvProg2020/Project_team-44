package client.graphicView.buyLogPage;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import server.model.account.Seller;
import server.model.buyLog.BuyLog;
import server.model.product.Product;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BuyLogController implements Initializable {
    // button to go to previous scene
    @FXML
    private Button backButton = new Button();
    // table to show purchased products
    @FXML
    private TableView<PurchasedProduct> purchasedProductsTableView;
    @FXML
    private TableColumn<PurchasedProduct, StringProperty> purchasedProductColumn;
    @FXML
    private TableColumn<PurchasedProduct, StringProperty> sellerFirstNameColumn;
    @FXML
    private TableColumn<PurchasedProduct, StringProperty> sellerLastNameColumn;

    // inner class for purchased product table elements
    public static class PurchasedProduct {
        private StringProperty purchasedProduct;
        private StringProperty sellerFirstName;
        private StringProperty sellerLastName;

        public PurchasedProduct(String purchasedProduct, String sellerFirstName, String sellerLastName) {
            this.purchasedProduct = new SimpleStringProperty(purchasedProduct);
            this.sellerFirstName = new SimpleStringProperty(sellerFirstName);
            this.sellerLastName = new SimpleStringProperty(sellerLastName);
        }

        public StringProperty purchasedProductProperty() {
            return purchasedProduct;
        }

        public StringProperty sellerFirstNameProperty() {
            return sellerFirstName;
        }

        public StringProperty sellerLastNameProperty() {
            return sellerLastName;
        }
    }

    // table to show other info of buyLog, and have only 1 row
    @FXML
    private TableView<BuyLogInfo> buyLogInfoTable;
    @FXML
    private TableColumn<BuyLogInfo, Date> dateColumn;
    @FXML
    private TableColumn<BuyLogInfo, IntegerProperty> discountCodeAmountUsedColumn;
    @FXML
    private TableColumn<BuyLogInfo, IntegerProperty> moneyPaidColumn;
    @FXML
    private TableColumn<BuyLogInfo, StringProperty> statusColumn;

    // info inner class
    public static class BuyLogInfo {
        private Date date;
        private IntegerProperty discountCodeAmountUsed;
        private IntegerProperty moneyPaid;
        private StringProperty status;

        public BuyLogInfo(Date date, int discountCodeAmountUsed, int moneyPaid, String status) {
            this.date = date;
            this.discountCodeAmountUsed = new SimpleIntegerProperty(discountCodeAmountUsed);
            this.moneyPaid = new SimpleIntegerProperty(moneyPaid);
            this.status = new SimpleStringProperty(status);
        }

        public Date getDate() {
            return date;
        }

        public IntegerProperty discountCodeAmountUsedProperty() {
            return discountCodeAmountUsed;
        }

        public IntegerProperty moneyPaidProperty() {
            return moneyPaid;
        }

        public StringProperty statusProperty() {
            return status;
        }
    }

    // produce elements in info table
    ObservableList<BuyLogInfo> getBuyLogInfo() {
        ObservableList<BuyLogInfo> buyLogInfo = FXCollections.observableArrayList();
        buyLogInfo.add(new BuyLogInfo(BuyLog.getBuyLogById(BuyLogPageController.getCurrentBuyLogId()).getDate(),
                (int) BuyLog.getBuyLogById(BuyLogPageController.getCurrentBuyLogId()).getDiscountCodeAmountUsed(),
                (int) BuyLog.getBuyLogById(BuyLogPageController.getCurrentBuyLogId()).getMoneyPaid(),
                BuyLog.getBuyLogById(BuyLogPageController.getCurrentBuyLogId()).getStatus().toString()));
        return buyLogInfo;
    }

    // produce elements in purchased products table
    ObservableList<PurchasedProduct> getBuyPurchasedProducts() {
        ObservableList<PurchasedProduct> purchasedProducts = FXCollections.observableArrayList();
        HashMap<Product, Seller> hashMap = BuyLog.getBuyLogById(BuyLogPageController.getCurrentBuyLogId()).getSellerForEachProduct();
        System.out.println(hashMap.size());
        for (Product purchasedProduct : hashMap.keySet()) {
            purchasedProducts.add(new PurchasedProduct(purchasedProduct.getName(), hashMap.get(purchasedProduct).getFirstName(), hashMap.get(purchasedProduct).getLastName()));
        }
        return purchasedProducts;
    }

    @FXML
    private void goPreviousScene() {
        playButtonSound();
        client.graphicView.buyLogPage.BuyLog.window.close();
        try {
            BuyLogPage.display();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void playButtonSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/server.main/resources/media/sound/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString()));
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize the purchased products table
        purchasedProductColumn.setCellValueFactory(new PropertyValueFactory<>("purchasedProduct"));
        sellerFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerFirstName"));
        sellerLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerLastName"));
        purchasedProductsTableView.setItems(getBuyPurchasedProducts());
        // initialize the info table
        discountCodeAmountUsedColumn.setCellValueFactory(new PropertyValueFactory<>("discountCodeAmountUsed"));
        moneyPaidColumn.setCellValueFactory(new PropertyValueFactory<>("moneyPaid"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        buyLogInfoTable.setItems(getBuyLogInfo());
    }
}
