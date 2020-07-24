package client.graphicView.userRegion.userAccount.sellerAccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.Socket;

public class SellerBankAccountController {
    private final int port = 9008;
    private final String ip = "127.0.0.1";
    @FXML
    public TextField firstNameId;
    @FXML
    public TextField userNameId;
    @FXML
    public TextField lastNameId;
    @FXML
    public PasswordField repeatPasswordId;
    @FXML
    public PasswordField passwordId;
    @FXML
    public Label receiveMessageId;
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

    @FXML
    public void createAction(ActionEvent actionEvent) throws IOException {
        out.writeUTF("create_account " + firstNameId.getText() + " " + lastNameId.getText() + " " + userNameId.getText() + " " + passwordId.getText() + " " + repeatPasswordId.getText());
        out.flush();
        String response = in.readUTF();
        receiveMessageId.setText("");
        if (!response.matches("\\d+")) {
            receiveMessageId.setTextFill(Color.INDIANRED);
            receiveMessageId.setText(response);
        } else {
            receiveMessageId.setTextFill(Color.FORESTGREEN);
            receiveMessageId.setText("Bank account successfully created....");
        }
    }
}
