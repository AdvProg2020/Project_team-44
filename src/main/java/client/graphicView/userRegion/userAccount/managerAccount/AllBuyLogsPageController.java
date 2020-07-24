package client.graphicView.userRegion.userAccount.managerAccount;

import client.graphicView.userRegion.userAccount.managerAccount.BuyLogPageAfterClicked;
import server.controller.LoginPageController;
import client.graphicView.buyLogPage.BuyLogPageController;
import client.graphicView.mainMenu.MainMenu;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import client.Main;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllBuyLogsPageController implements Initializable {
    @FXML
    public TableView<BuyLog> viewBuyLogs;
    @FXML
    public TableColumn<BuyLog, Label> buyLogId;
    @FXML
    public TableColumn<BuyLog, StringProperty> purchaseAddress;
    @FXML
    public TableColumn<BuyLog, StringProperty> sendingStatus;

    private ArrayList<BuyLog> allLogs = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buyLogId.setCellValueFactory(new PropertyValueFactory<>("id"));
        purchaseAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        sendingStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        viewBuyLogs.setItems(getBuyLogs());
    }

    public class BuyLog{
        private Label id;
        private StringProperty address;
        private StringProperty status;

        public Label getId() {
            return id;
        }

        public String getAddress() {
            return address.get();
        }

        public StringProperty addressProperty() {
            return address;
        }

        public String getStatus() {
            return status.get();
        }

        public StringProperty statusProperty() {
            return status;
        }


        public BuyLog(String id, String address, String status) {
            this.id = new Label(id);
            this.address = new SimpleStringProperty(address);
            this.status = new SimpleStringProperty(status);
            allLogs.add(this);
            this.id.setOnMouseClicked (mouseEvent -> {
                AllBuyLogsPage.primaryStage.close();
                try {
                    BuyLogPageController.setCurrentBuyLogId(id);
                    BuyLogPageAfterClicked.display();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    ObservableList<BuyLog> getBuyLogs(){
        ObservableList<BuyLog> allBuyLogs = FXCollections.observableArrayList();
        for (server.model.buyLog.BuyLog buyLog : server.model.buyLog.BuyLog.getAllBuyLogs()) {
            allBuyLogs.add(new BuyLog(buyLog.getLogID(),buyLog.getPurchaserAddress(),buyLog.getStatus().toString()));
        }
        return allBuyLogs;
    }
    @FXML
    public void logout() throws IOException {
        AllBuyLogsPage.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }
    @FXML
    public void back() throws IOException {
        AllBuyLogsPage.primaryStage.close();
        ManagerAccountPage.display();
    }
}
