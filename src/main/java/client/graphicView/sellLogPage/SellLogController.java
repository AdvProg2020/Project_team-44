package client.graphicView.sellLogPage;

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
import server.model.product.Product;
import server.model.sellLog.SellLog;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class SellLogController implements Initializable {
    static Integer counter = 1;
    // button to go to previous scene
    @FXML
    private Button backButton = new Button();
    // table to show sold products
    @FXML
    private TableView<SoldProducts> soldProductsTableView;
    @FXML
    private TableColumn<SoldProducts, StringProperty> soldProductsColumn;
    @FXML
    private TableColumn<SoldProducts, IntegerProperty> rowNumberColumn;

    // table to show other info of sellLog, and have only 1 row
    @FXML
    private TableView<SellLogInfo> sellLogInfoTable;
    @FXML
    private TableColumn<SellLogInfo, StringProperty> purchaserFirstNameColumn;
    @FXML
    private TableColumn<SellLogInfo, StringProperty> purchaserLastNameColumn;
    @FXML
    private TableColumn<SellLogInfo, Date> dateColumn;
    @FXML
    private TableColumn<SellLogInfo, IntegerProperty> offerLossMoneyColumn;
    @FXML
    private TableColumn<SellLogInfo, IntegerProperty> moneyGainedColumn;
    @FXML
    private TableColumn<SellLogInfo, StringProperty> statusColumn;

    // sold products inner class
    public class SoldProducts {
        private StringProperty soldProductName;
        private IntegerProperty rowNumber;

        public SoldProducts(String soldProductName) {
            this.soldProductName = new SimpleStringProperty(soldProductName);
            this.rowNumber = new SimpleIntegerProperty(counter);
            counter++;
        }

        public StringProperty soldProductNameProperty() {
            return soldProductName;
        }

        public IntegerProperty rowNumberProperty() {
            return rowNumber;
        }
    }

    // info inner class
    public class SellLogInfo {
        private IntegerProperty offerLossMoney;
        private IntegerProperty moneyGained;
        private StringProperty status;
        private StringProperty purchaserFirstName;
        private StringProperty purchaserLastName;
        private Date date;

        public SellLogInfo(int offerLossMoney, int moneyGained, String status, String purchaserFirstName, String purchaserLastName, Date date) {
            this.offerLossMoney = new SimpleIntegerProperty(offerLossMoney);
            this.moneyGained = new SimpleIntegerProperty(moneyGained);
            this.status = new SimpleStringProperty(status);
            this.purchaserFirstName = new SimpleStringProperty(purchaserFirstName);
            this.purchaserLastName = new SimpleStringProperty(purchaserLastName);
            this.date = date;
        }

        public IntegerProperty offerLossMoneyProperty() {
            return offerLossMoney;
        }

        public IntegerProperty moneyGainedProperty() {
            return moneyGained;
        }

        public StringProperty statusProperty() {
            return status;
        }


        public StringProperty purchaserFirstNameProperty() {
            return purchaserFirstName;
        }

        public StringProperty purchaserLastNameProperty() {
            return purchaserLastName;
        }

        public Date getDate() {
            return date;
        }
    }

    // produce elements in info table
    ObservableList<SellLogInfo> getSellLogInfo() {
        ObservableList<SellLogInfo> sellLogInfo = FXCollections.observableArrayList();
        sellLogInfo.add(new SellLogInfo((int) SellLog.getSellLogById(SellLogPageController.getCurrentSellLogId()).getOfferLossMoney(),
                (int) SellLog.getSellLogById(SellLogPageController.getCurrentSellLogId()).getMoneyGained(),
                SellLog.getSellLogById(SellLogPageController.getCurrentSellLogId()).getStatus().toString(),
                SellLog.getSellLogById(SellLogPageController.getCurrentSellLogId()).getPurchaserFirstName(),
                SellLog.getSellLogById(SellLogPageController.getCurrentSellLogId()).getPurchaserLastName(),
                SellLog.getSellLogById(SellLogPageController.getCurrentSellLogId()).getDate()));
        return sellLogInfo;
    }

    // produce elements in sold products  table
    ObservableList<SoldProducts> getSellLogSoldProducts() {
        ObservableList<SoldProducts> soldProducts = FXCollections.observableArrayList();
        for (Product sellProduct : SellLog.getSellLogById(SellLogPageController.getCurrentSellLogId()).getAllSellProducts()) {
            soldProducts.add(new SoldProducts(sellProduct.getName()));
        }
        return soldProducts;
    }

    private void playButtonSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/server.main/resources/media/sound/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString()));
        mediaPlayer.play();
    }

    @FXML
    private void goPreviousScene() {
        playButtonSound();
        client.graphicView.sellLogPage.SellLog.window.close();
        try {
            SellLogPage.display();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize the sold products table
        soldProductsColumn.setCellValueFactory(new PropertyValueFactory<>("soldProductName"));
        rowNumberColumn.setCellValueFactory(new PropertyValueFactory<>("rowNumber"));
        soldProductsTableView.setItems(getSellLogSoldProducts());
        // initialize the info table
        purchaserFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("purchaserFirstName"));
        purchaserLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("purchaserLastName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        offerLossMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("offerLossMoney"));
        moneyGainedColumn.setCellValueFactory(new PropertyValueFactory<>("moneyGained"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        sellLogInfoTable.setItems(getSellLogInfo());
    }
}
