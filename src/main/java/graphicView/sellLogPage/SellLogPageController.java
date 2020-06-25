package graphicView.sellLogPage;

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

import java.net.URL;
import java.util.ResourceBundle;

public class SellLogPageController implements Initializable {
    // count the row number in the table
    static int counter = 1;

    @FXML
    private TableView<SellLogIds> sellLogIdsTableView;

    @FXML
    private TableColumn<SellLogIds, StringProperty> sellLogIdColumn;
    @FXML
    private TableColumn<SellLogIds, IntegerProperty> rowNumberColumn;

    @FXML
    private Button backButton = new Button();

    // define to use in table for show
    public class SellLogIds {
        // change the type label to use set on action function
        private StringProperty sellLogId;
        // number of row, start from 1
        private IntegerProperty rowNumber;

        public SellLogIds(String sellLogId) {
            this.sellLogId = new SimpleStringProperty(sellLogId);
            rowNumber = new SimpleIntegerProperty(counter);
            counter++;
        }

        public StringProperty sellLogIdProperty() {
            return sellLogId;
        }

        public IntegerProperty rowNumberProperty() {
            return rowNumber;
        }
    }

    ObservableList<SellLogIds> getSellLogIds() {
        ObservableList<SellLogIds> sellLogs = FXCollections.observableArrayList();
        sellLogs.addAll(new SellLogIds("1234"));
        sellLogs.addAll(new SellLogIds("2345"));
        sellLogs.addAll(new SellLogIds("5678"));
        sellLogs.addAll(new SellLogIds("9637"));
        return sellLogs;
    }

    @FXML
    private void goPreviousScene() {
        System.out.println("back");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rowNumberColumn.setCellValueFactory(new PropertyValueFactory<>("rowNumber"));
        sellLogIdColumn.setCellValueFactory(new PropertyValueFactory<>("sellLogId"));
        sellLogIdsTableView.setItems(getSellLogIds());
    }
}
