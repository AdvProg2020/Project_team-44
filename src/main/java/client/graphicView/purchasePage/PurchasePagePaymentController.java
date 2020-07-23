package client.graphicView.purchasePage;

import server.controller.LoginPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import server.main.ShopServer;
import server.model.ShopBankAccount;
import server.model.account.Purchaser;
import server.model.account.Seller;
import server.model.product.Product;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalAmountId.setText(String.valueOf(PurchasePagePayment.toPay));
    }

    @FXML
    public void goPaymentAction(ActionEvent actionEvent) throws IOException {
        receiveMessageId.setText("");
        Socket socket = new Socket(ShopServer.IP, ShopServer.port);
        DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        out.writeUTF("create_receiptCharge" + "create_receipt " + "move " + Integer.parseInt(totalAmountId.getText()) + " " + accountId.getText() + " " + ShopBankAccount.getShopBankAccount().getAccountId() + " " + "charge" + " " + userNameId.getText() + " " + passwordId.getText());
        out.flush();
        String response = in.readUTF();
        receiveMessageId.setText(response);
        if (response.equals("done successfully")) {
            for (Product product : ((Purchaser) LoginPageController.getLoggedInAccount()).getSellerSelectedForEachProduct().keySet()) {
                Seller seller = ((Purchaser) LoginPageController.getLoggedInAccount()).getSellerSelectedForEachProduct().get(product);
                seller.setBalance(seller.getBalance() + ((100 - PurchasePageController.wage) * ((Purchaser) LoginPageController.getLoggedInAccount()).getCart().get(product) * product.getPrice()) / 100);
                seller.createAndUpdateJson();
            }
        }
    }
}
