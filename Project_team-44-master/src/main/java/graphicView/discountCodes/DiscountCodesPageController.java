package graphicView.discountCodes;


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
import java.util.Date;
import java.util.ResourceBundle;

public class DiscountCodesPageController implements Initializable {
    // count the row number of the table
    static int counter = 1;
    // button to go to previous scene
    @FXML
    private Button backButton = new Button();
    // contents of the table
    @FXML
    TableView<CodedDiscount> codedDiscountTableView = new TableView<>();
    @FXML
    TableColumn<CodedDiscount, IntegerProperty> rowNumberColumn;
    @FXML
    TableColumn<CodedDiscount, StringProperty> discountCodeColumn;
    @FXML
    TableColumn<CodedDiscount, Date> initialDateColumn;
    @FXML
    TableColumn<CodedDiscount, Date> finalDateColumn;
    @FXML
    TableColumn<CodedDiscount, IntegerProperty> discountPercentageColumn;
    @FXML
    TableColumn<CodedDiscount, IntegerProperty> maxAuthorizedPriceColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rowNumberColumn.setCellValueFactory(new PropertyValueFactory<>("rowNumber"));
        discountCodeColumn.setCellValueFactory(new PropertyValueFactory<>("discountCode"));
        initialDateColumn.setCellValueFactory(new PropertyValueFactory<>("initialDate"));
        finalDateColumn.setCellValueFactory(new PropertyValueFactory<>("finalDate"));
        discountPercentageColumn.setCellValueFactory(new PropertyValueFactory<>("discountPercentage"));
        maxAuthorizedPriceColumn.setCellValueFactory(new PropertyValueFactory<>("maxAuthorizedPrice"));
        codedDiscountTableView.setItems(getCodedDiscounts());
    }

    private ObservableList<CodedDiscount> getCodedDiscounts() {
        ObservableList<CodedDiscount> codedDiscountList = FXCollections.observableArrayList();
        codedDiscountList.add(new CodedDiscount("100",
                new Date(),
                new Date(),
                10,
                20000));
        codedDiscountList.add(new CodedDiscount("200",
                new Date(),
                new Date(),
                36,
                1000000));
        codedDiscountList.add(new CodedDiscount("300",
                new Date(),
                new Date(),
                12,
                89332));
        codedDiscountList.add(new CodedDiscount("400",
                new Date(),
                new Date(),
                90,
                88));
        return codedDiscountList;
    }

    // inner class for table elements
    public class CodedDiscount {
        private IntegerProperty rowNumber;
        private StringProperty discountCode;
        private Date initialDate;
        private Date finalDate;
        private IntegerProperty discountPercentage;
        private IntegerProperty maxAuthorizedPrice;

        public CodedDiscount(String discountCode, Date initialDate, Date finalDate, int discountPercentage, int maxAuthorizedPrice) {
            this.rowNumber = new SimpleIntegerProperty(counter);
            this.discountCode = new SimpleStringProperty(discountCode);
            this.initialDate = initialDate;
            this.finalDate = finalDate;
            this.discountPercentage = new SimpleIntegerProperty(discountPercentage);
            this.maxAuthorizedPrice = new SimpleIntegerProperty(maxAuthorizedPrice);
            counter++;
        }

        public IntegerProperty rowNumberProperty() {
            return rowNumber;
        }

        public StringProperty discountCodeProperty() {
            return discountCode;
        }

        public Date getInitialDate() {
            return initialDate;
        }

        public Date getFinalDate() {
            return finalDate;
        }

        public IntegerProperty discountPercentageProperty() {
            return discountPercentage;
        }

        public IntegerProperty maxAuthorizedPriceProperty() {
            return maxAuthorizedPrice;
        }
    }

    @FXML
    private void goPreviousScene() {
        System.out.println("back");
    }


}
