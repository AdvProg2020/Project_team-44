package graphicView.userRegion.userAccount.sellerAccount;

import controller.LoginPageController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.BankAPI;
import main.ShopServer;
import model.ShopBankAccount;
import model.account.Account;
import model.account.Manager;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class SellerWalletController implements Initializable {
    public static int atLeastAmount = Manager.getAllManagers().get(0).getMinAmount();
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

//    public SellerWalletController() {
//       creditId.setText("Credit " + LoginPageController.getLoggedInAccount().getBalance() + " $");
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> creditId.setText("Credit " + LoginPageController.getLoggedInAccount().getBalance() + " $")).start();
    }

    @FXML
    public void receiveAction() throws IOException {
        Account currentAccount = LoginPageController.getLoggedInAccount();
        if (currentAccount.getBalance() - Double.parseDouble(amountId.getText()) < atLeastAmount) {
            receiveMessageId.setText("The amount request is more than as you can...");
        } else if (Double.parseDouble(amountId.getText()) > 0) {
            receiveMessageId.setText("");
            Socket socket = new Socket(ShopServer.IP, ShopServer.port);
            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));


            out.writeUTF("create_receiptReceive" + "create_receipt " + "move " + Integer.parseInt(amountId.getText()) + " " + ShopBankAccount.getShopBankAccount().getAccountId() + " " + Integer.parseInt(destinationId.getText()) + " " + "receive");
            out.flush();
            String respond = in.readUTF();
            receiveMessageId.setText(respond);
            if (respond.equals("done successfully")) {
                currentAccount.setBalance(currentAccount.getBalance() - Integer.parseInt(amountId.getText()));
                currentAccount.createAndUpdateJson();
                creditId.setText("Credit " + LoginPageController.getLoggedInAccount().getBalance() + " $");
            }
            amountId.setText("");
            accountUsernameId.setText("");
            accountPasswordId.setText("");
            destinationId.setText("");
        }
    }
}
