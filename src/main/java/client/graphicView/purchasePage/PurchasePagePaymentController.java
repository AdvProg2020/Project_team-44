package client.graphicView.purchasePage;

import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class PurchasePagePaymentController implements Initializable {
    @FXML
    public TextField accountId;
    @FXML
    public TextField userNameId;
    @FXML
    public Label receiveMessageId;
    @FXML
    public PasswordField passwordId;
    @FXML
    public Label totalAmountId;
    private final int port = 9009;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        process();
        totalAmountId.setText(String.valueOf(PurchasePagePayment.toPay));
    }

    @FXML
    public void goPaymentAction(ActionEvent actionEvent) throws IOException {
        receiveMessageId.setText("");
        out.writeUTF("get_shop_bank_account_id");
        out.flush();
        String id = in.readUTF();
        out.writeUTF("create_receiptCharge" + "create_receipt " + "move " + Integer.parseInt(totalAmountId.getText()) + " " + accountId.getText() + " " + id + " " + "charge" + " " + userNameId.getText() + " " + passwordId.getText());
        out.flush();
        String response = in.readUTF();
        receiveMessageId.setText(response);
        if (response.equals("done successfully")) {
            out.writeUTF("get_size " + LoginPanelController.token);
            out.flush();
            int size = Integer.parseInt(in.readUTF());
            for (int i = 0; i < size; i++) {
                out.writeUTF("set_each_product " + i);
                out.flush();
            }
        }
    }
}
