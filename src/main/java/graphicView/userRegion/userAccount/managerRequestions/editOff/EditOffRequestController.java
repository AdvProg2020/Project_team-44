package graphicView.userRegion.userAccount.managerRequestions.editOff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.requests.RequestForEditOff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditOffRequestController implements Initializable {
    // store the clicked requestId
    private static String currentRequestId;

    public static String getCurrentRequestId() {
        return currentRequestId;
    }

    public static void setCurrentRequestId(String currentRequestId) {
        EditOffRequestController.currentRequestId = currentRequestId;
    }

    // table to show request ids
    @FXML
    TableView<RequestIds> table;
    @FXML
    TableColumn<RequestIds, Label> requestIdsColumn;
    // back to manager account region
    @FXML
    Button backButton = new Button();

    // backButton onAction
    @FXML
    private void goPreviousScene() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        requestIdsColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        table.setItems(getRequest());
    }

    private ObservableList<RequestIds> getRequest() {
        ObservableList<RequestIds> requestIds = FXCollections.observableArrayList();
        for (RequestForEditOff requestForEditOff : RequestForEditOff.getAllRequestsForEditOff()) {
            requestIds.add(new RequestIds(requestForEditOff.getRequestId()));
        }
        return requestIds;
    }

    public class RequestIds {
        private Label requestId;

        public RequestIds(String requestId) {
            this.requestId = new Label(requestId);
            this.requestId.setOnMousePressed(actionEvent -> {
                EditOffRequest.window.close();
                setCurrentRequestId(requestId);
                try {
                    OffRequestInfo.display();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }

        public Label getRequestId() {
            return requestId;
        }
    }
}
