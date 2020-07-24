package client.graphicView.userRegion.userAccount.managerRequestions.addOff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddOffRequestController implements Initializable {
    // store the clicked requestId
    private static String currentRequestId;
    private final int port = 9011;
    private final String ip = "127.0.0.1";
    private DataOutputStream out;
    private DataInputStream in;

    public void process() {
        try {
            Socket socket = new Socket(ip, port);
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

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
        try {
            table.setItems(getRequest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<RequestIds> getRequest() throws IOException {
        out.writeUTF("get_all_request");
        out.flush();
        ArrayList<String> allRequest = new ArrayList<>();
        for (String s : in.readUTF().split(" - ")) {
            allRequest.add(s);
        }
        ObservableList<RequestIds> requestIds = FXCollections.observableArrayList();
        for (String requestForAddOff : allRequest) {
            requestIds.add(new RequestIds(requestForAddOff));
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
