package graphicView.buyLogPage;

import controller.LoginPageController;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import model.account.Purchaser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuyLogPageController implements Initializable {
    private static String currentBuyLogId;
    @FXML
    public TableColumn<BuyLogIds , StringProperty> statusColumn;

    public static String getCurrentBuyLogId() {
        return currentBuyLogId;
    }

    public static void setCurrentBuyLogId(String currentBuyLogId) {
        BuyLogPageController.currentBuyLogId = currentBuyLogId;
    }

    @FXML
    private TableView<BuyLogIds> buyLogIdsTableView;

    @FXML
    private TableColumn<BuyLogIds, Label> buyLogIdColumn;

    @FXML
    private Button backButton = new Button();

    // define to use in table for show
    public class BuyLogIds {
        // change the type label to use set on action function
        private Label buyLogId;
        private StringProperty status;

        public String getStatus() {
            return status.get();
        }

        public StringProperty statusProperty() {
            return status;
        }

        public BuyLogIds(String buyLogId , String status) {
            this.buyLogId = new Label(buyLogId);
            this.status = new SimpleStringProperty(status);
            this.buyLogId.setOnMousePressed(actionEvent -> {
                playButtonSound();
                setCurrentBuyLogId(buyLogId);
                BuyLogPage.window.close();
                try {
                    BuyLog.display();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }

        public Label getBuyLogId() {
            return buyLogId;
        }
    }

    ObservableList<BuyLogIds> getBuyLogIds() {
        ObservableList<BuyLogIds> buyLogs = FXCollections.observableArrayList();
        for (String buyLogId : ((Purchaser) LoginPageController.getLoggedInAccount()).getAllBuyLogIds()) {
            buyLogs.add(new BuyLogIds(buyLogId, model.buyLog.BuyLog.getBuyLogById(buyLogId).getStatus().toString()));
        }
        return buyLogs;
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
        buyLogIdColumn.setCellValueFactory(new PropertyValueFactory<>("buyLogId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        buyLogIdsTableView.setItems(getBuyLogIds());
    }
}
