package client.graphicView.userRegion.userAccount.purchaserAccount;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PurchaserContactSupportersPageController implements Initializable {

    @FXML
    private TableView<Supporter> supportersTableView;

    @FXML
    private TableColumn<Supporter, Label> supporterUsernameColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        supporterUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("supporterUsername"));
        supportersTableView.setItems(getSupporters());
    }

    // only displays the online supporters
    private ObservableList<Supporter> getSupporters() {
        ObservableList<Supporter> supporters = FXCollections.observableArrayList();
        for (server.model.account.Supporter supporter : server.model.account.Supporter.getAllSupporters()) {
            // check online
            if (supporter.isLoggedIn()) {
                supporters.add(new Supporter(supporter.getUserName()));
            }
        }
        return supporters;
    }

    public class Supporter {
        private final Label supporterUsername;

        public Supporter(String supporterUsername) {
            this.supporterUsername = new Label(supporterUsername);
            this.supporterUsername.setOnMouseClicked(actionEvent -> {
                processChat(supporterUsername);
            });
        }

        private void processChat(String supporterUsername) {
            System.out.println("chat!");
        }

        public Label getSupporterUsername() {
            return supporterUsername;
        }
    }
}