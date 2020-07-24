package client.graphicView.userRegion.userAccount.purchaserAccount;

import client.graphicView.userRegion.loginPanel.LoginPanel;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.Initializable;
import server.controller.LoginPageController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class PurchaserWalletController implements Initializable {
    @FXML
    public TextField accountId;
    @FXML
    public TextField userNameId;
    @FXML
    public TextField amountId;
    @FXML
    public Label receiveMessageId;
    @FXML
    public PasswordField passwordId;

    private final int port = 9007;
    private final String ip = "127.0.0.1";
    private DataOutputStream out;
    private DataInputStream in;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        process();
    }

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

    @FXML
    public void goPaymentAction() throws IOException {
        if (Double.parseDouble(amountId.getText()) > 0) {
            receiveMessageId.setText("");
            out.writeUTF("get_shop_bank_account_id");
            out.flush();
            String id = in.readUTF();
            out.writeUTF("create_receiptCharge" + "create_receipt " + "move " + Integer.parseInt(amountId.getText()) + " " + accountId.getText() + " " + id + " " + "charge" + " " + userNameId.getText() + " " + passwordId.getText());
            out.flush();
            String response = in.readUTF();
            receiveMessageId.setText(response);
            if (response.equals("done successfully")) {
                out.writeUTF("set_balance " + amountId.getText() + " " + LoginPanelController.token);
                out.flush();
            }
        }
    }
}