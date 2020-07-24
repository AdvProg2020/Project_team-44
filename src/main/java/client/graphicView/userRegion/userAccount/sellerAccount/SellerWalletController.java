package client.graphicView.userRegion.userAccount.sellerAccount;

import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class SellerWalletController implements Initializable {
    public static int atLeastAmount;
    @FXML
    public Label receiveMessageId;
    @FXML
    public TextField amountId;
    @FXML
    public TextField destinationId;
    @FXML
    public Label creditId;
    @FXML
    public TextField accountUsernameId;
    @FXML
    public PasswordField accountPasswordId;
    private final int port = 9003;
    private final String ip = "127.0.0.1";
    private DataOutputStream out;
    private DataInputStream in;

//    public SellerWalletController() {
//       creditId.setText("Credit " + LoginPageController.getLoggedInAccount().getBalance() + " $");
//    }

    public void process() {
        try {
            Socket socket = new Socket(ip, port);
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out.writeUTF("get_at_least_amount");
            out.flush();
            String response = in.readUTF();
            atLeastAmount = Integer.parseInt(response.substring(21));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        process();
        String balance = null;
        try {
            out.writeUTF("get_balance " + LoginPanelController.token);
            out.flush();
            balance = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String finalBalance = balance;
        new Thread(() -> creditId.setText("Credit " + finalBalance + " $")).start();
    }

    @FXML
    public void receiveAction() throws IOException {
        String balance = null;
        try {
            out.writeUTF("get_balance " + LoginPanelController.token);
            out.flush();
            balance = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String finalBalance = balance;
        if (Double.parseDouble(finalBalance) - Double.parseDouble(amountId.getText()) < atLeastAmount) {
            receiveMessageId.setText("The amount request is more than as you can...");
        } else if (Double.parseDouble(amountId.getText()) > 0) {
            receiveMessageId.setText("");
            out.writeUTF("get_shop_bank_account_id");
            out.flush();
            String id = in.readUTF();
            out.writeUTF("create_receiptReceive" + "create_receipt " + "move " + Integer.parseInt(amountId.getText()) + " " + id + " " + Integer.parseInt(destinationId.getText()) + " " + "receive");
            out.flush();
            String respond = in.readUTF();
            receiveMessageId.setText(respond);
            if (respond.equals("done successfully")) {
                out.writeUTF("set_balance " + amountId.getText() + " " + LoginPanelController.token);
                out.flush();
                String amount = in.readUTF();
                creditId.setText("Credit " + amount + " $");
            }
            amountId.setText("");
            accountUsernameId.setText("");
            accountPasswordId.setText("");
            destinationId.setText("");
        }
    }
}
