package graphicView.buyLogPage;

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

import java.net.URL;
import java.util.ResourceBundle;

public class BuyLogPageController implements Initializable {
    @FXML
    private TableView<BuyLogIds> buyLogIdsTableView;

    @FXML
    private TableColumn<BuyLogIds, StringProperty> buyLogIdColumn;

    @FXML
    private Button backButton = new Button();

    // define to use in table for show
    public class BuyLogIds {
        // change the type label to use set on action function
        private StringProperty buyLogId;

        public BuyLogIds(String buyLogId) {
            this.buyLogId = new SimpleStringProperty(buyLogId);
        }

        public StringProperty buyLogIdProperty() {
            return buyLogId;
        }
    }

    ObservableList<BuyLogIds> getBuyLogIds() {
        ObservableList<BuyLogIds> buyLogs = FXCollections.observableArrayList();
        buyLogs.add(new BuyLogIds("1234"));
        buyLogs.add(new BuyLogIds("4567"));
        buyLogs.add(new BuyLogIds("2373"));
        buyLogs.add(new BuyLogIds("1999"));
        return buyLogs;
    }

    @FXML
    private void goPreviousScene() {
        System.out.println("back");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buyLogIdColumn.setCellValueFactory(new PropertyValueFactory<>("buyLogId"));
        buyLogIdsTableView.setItems(getBuyLogIds());
    }
}
