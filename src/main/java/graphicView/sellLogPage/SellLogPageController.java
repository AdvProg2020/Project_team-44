package graphicView.sellLogPage;

import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.account.Seller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SellLogPageController implements Initializable {
    private static String currentSellLogId;

    public static String getCurrentSellLogId() {
        return currentSellLogId;
    }

    public static void setCurrentSellLog(String currentSellLogId) {
        SellLogPageController.currentSellLogId = currentSellLogId;
    }

    // count the row number in the table
    private static int counter = 1;

    @FXML
    private TableView<SellLogIds> sellLogIdsTableView;

    @FXML
    private TableColumn<SellLogIds, Label> sellLogIdColumn;
    @FXML
    private TableColumn<SellLogIds, IntegerProperty> rowNumberColumn;

    @FXML
    private Button backButton = new Button();

    // define to use in table for show
    public class SellLogIds {
        // change the type label to use set on action function
        private Label sellLogId;
        // number of row, start from 1
        private IntegerProperty rowNumber;

        public SellLogIds(String sellLogId) {
            this.sellLogId = new Label(sellLogId);
            rowNumber = new SimpleIntegerProperty(counter);
            counter++;
            this.sellLogId.setOnMousePressed(actionEvent -> {
                playButtonSound();
                setCurrentSellLog(sellLogId);
                try {
                    SellLogPage.window.close();
                    SellLog.display();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }

        public Label getSellLogId() {
            return sellLogId;
        }

        public IntegerProperty rowNumberProperty() {
            return rowNumber;
        }
    }

    ObservableList<SellLogIds> getSellLogIds() {
        ObservableList<SellLogIds> sellLogs = FXCollections.observableArrayList();
        for (String sellLogId : ((Seller) LoginPanelController.getLoggedInAccount()).getAllSellLogIds()) {
            sellLogs.add(new SellLogIds(sellLogId));
        }
        return sellLogs;
    }

    @FXML
    private void goPreviousScene() {
        playButtonSound();
        System.out.println("back");
    }

    private void playButtonSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/media/sound/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString()));
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rowNumberColumn.setCellValueFactory(new PropertyValueFactory<>("rowNumber"));
        sellLogIdColumn.setCellValueFactory(new PropertyValueFactory<>("sellLogId"));
        sellLogIdsTableView.setItems(getSellLogIds());
        currentSellLogId = null;
    }
}
