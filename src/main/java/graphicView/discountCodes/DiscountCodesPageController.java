package graphicView.discountCodes;


import controller.LoginPageController;
import controller.ManagerAccountController;
import controller.PurchaserAccountController;
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
import model.account.Manager;

import java.io.File;
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

    private void playButtonSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/media/sound/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString()));
        mediaPlayer.play();
    }


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
        if (LoginPageController.getLoggedInAccount() instanceof Manager) {
            for (String discountCode : ManagerAccountController.processViewDiscountCodes()) {
                codedDiscountList.add(new CodedDiscount(model.CodedDiscount.getCodedDiscountByCode(discountCode).getDiscountCode(),
                        model.CodedDiscount.getCodedDiscountByCode(discountCode).getInitialDate(),
                        model.CodedDiscount.getCodedDiscountByCode(discountCode).getFinalDate(),
                        (int) model.CodedDiscount.getCodedDiscountByCode(discountCode).getDiscountPercentage(),
                        (int) model.CodedDiscount.getCodedDiscountByCode(discountCode).getMaxAuthorizedPrice()));
            }
        } else {
            for (String discountCode : PurchaserAccountController.processViewDiscountCodes()) {
                codedDiscountList.add(new CodedDiscount(model.CodedDiscount.getCodedDiscountByCode(discountCode).getDiscountCode(),
                        model.CodedDiscount.getCodedDiscountByCode(discountCode).getInitialDate(),
                        model.CodedDiscount.getCodedDiscountByCode(discountCode).getFinalDate(),
                        (int) model.CodedDiscount.getCodedDiscountByCode(discountCode).getDiscountPercentage(),
                        (int) model.CodedDiscount.getCodedDiscountByCode(discountCode).getMaxAuthorizedPrice()));
            }
        }
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
        playButtonSound();
        System.out.println("back");
    }

}
