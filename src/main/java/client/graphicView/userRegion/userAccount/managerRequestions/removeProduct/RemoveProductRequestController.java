package client.graphicView.userRegion.userAccount.managerRequestions.removeProduct;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import server.model.requests.RequestForRemoveProduct;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RemoveProductRequestController implements Initializable {
    // store the clicked requestId
    private static String currentRequestId;

    public static String getCurrentRequestId() {
        return currentRequestId;
    }

    public static void setCurrentRequestId(String currentRequestId) {
        RemoveProductRequestController.currentRequestId = currentRequestId;
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
        for (RequestForRemoveProduct requestForRemoveProduct : RequestForRemoveProduct.getAllRequestForRemoveProduct()) {
            requestIds.add(new RequestIds(requestForRemoveProduct.getRequestId()));
        }
        return requestIds;
    }

    public class RequestIds {
        private Label requestId;

        public RequestIds(String requestId) {
            this.requestId = new Label(requestId);
            this.requestId.setOnMousePressed(actionEvent -> {
                RemoveProductRequest.window.close();
                setCurrentRequestId(requestId);
                try {
                    RemoveProductRequestInfo.display();
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
