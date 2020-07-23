package client.graphicView.userRegion.userAccount.managerRequestions.addOff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import server.model.requests.RequestForAddOff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddOffRequestController implements Initializable {
    // store the clicked requestId
    private static String currentRequestId;

    public static String getCurrentRequestId() {
        return currentRequestId;
    }

    public static void setCurrentRequestId(String currentRequestId) {
        AddOffRequestController.currentRequestId = currentRequestId;
    }

    // table to show request ids
    @FXML
    TableView<RequestIds> table;
    @FXML
    TableColumn<RequestIds, Label> requestIdsColumn;
    // back to manager account region
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        requestIdsColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        table.setItems(getRequest());
    }

    private ObservableList<RequestIds> getRequest() {
        ObservableList<RequestIds> requestIds = FXCollections.observableArrayList();
        for (RequestForAddOff requestForAddOff : RequestForAddOff.getAllRequestsForAddOff()) {
            requestIds.add(new RequestIds(requestForAddOff.getRequestId()));
        }
        return requestIds;
    }

    public class RequestIds {
        private Label requestId;

        public RequestIds(String requestId) {
            this.requestId = new Label(requestId);
            this.requestId.setOnMousePressed(actionEvent -> {
                AddOffRequest.window.close();
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
