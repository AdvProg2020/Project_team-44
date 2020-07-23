package client.graphicView.userRegion.userAccount.purchaserAccount;

import server.controller.LoginPageController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import server.main.PurchaserAuctionChatControllerServer;
import server.model.ShopBankAccount;
import server.model.account.Account;

import java.io.*;
import java.net.Socket;

public class purchaserWalletController {
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

    @FXML
    public void goPaymentAction() throws IOException {
        Account currentAccount = LoginPageController.getLoggedInAccount();
        if (Double.parseDouble(amountId.getText()) > 0) {
            receiveMessageId.setText("");
            Socket socket = new Socket(PurchaserAuctionChatControllerServer.IP, PurchaserAuctionChatControllerServer.port);
            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            out.writeUTF("create_receiptCharge" + "create_receipt " + "move " + Integer.parseInt(amountId.getText()) + " " + accountId.getText() + " " + ShopBankAccount.getShopBankAccount().getAccountId() + " " + "charge" + " " + userNameId.getText() + " " + passwordId.getText());
            out.flush();
            String response = in.readUTF();
            receiveMessageId.setText(response);
            if (response.equals("done successfully")){
                currentAccount.setBalance(currentAccount.getBalance() + Integer.parseInt(amountId.getText()));
                currentAccount.createAndUpdateJson();
            }
        }
    }
}