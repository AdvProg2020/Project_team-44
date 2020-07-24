package client.graphicView.userRegion.userAccount.managerAccount;

import client.Main;
import client.graphicView.mainMenu.MainMenu;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;

import static javafx.scene.paint.Color.GREEN;

public class ManagerEditInfoPageController {

    @FXML
    public TextField newManagerFirstName;
    @FXML
    public TextField newManagerLastName;
    @FXML
    public TextField newManagerUsername;
    @FXML
    public TextField newManagerPassWord;
    @FXML
    public TextField newManagerEmail;
    @FXML
    public Label alertMessage;

    private final int port = 9000;
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


    @FXML
    public void applyChanges() {
        process();
        String newFirstName = newManagerFirstName.getText();
        String newLastName = newManagerLastName.getText();
        String newPassWord = newManagerPassWord.getText();
        String newEmail = newManagerEmail.getText();
        if (!newFirstName.matches("[a-zA-Z]+")) {
            alertMessage.setText("Please enter server.main.Main valid first name!");
            return;
        }
        if (!newLastName.matches("[a-zA-Z]+")) {
            alertMessage.setText("Please enter server.main.Main valid last name!");
            return;
        }
        if (!newPassWord.matches("\\w{8,}")) {
            alertMessage.setText("Please enter server.main.Main valid password!");
            return;
        }
        if (!newEmail.matches("\\w+[@]\\w+[.]\\w+")) {
            alertMessage.setText("Please enter server.main.Main valid email!");
            return;
        }
        try {
            out.writeUTF("edit_field_each " + newFirstName + " " + newLastName + " " + newEmail +
                    " " + newPassWord);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        alertMessage.setText("");
        alertMessage.setTextFill(GREEN);
        alertMessage.setText("Edit info successful");
    }

    @FXML
    public void logout() throws IOException {
        ManagerEditInfoPage.primaryStage.close();
        out.writeUTF("logout " + LoginPanelController.getToken());
        out.flush();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }

    @FXML
    public void back() throws IOException {
        ManagerEditInfoPage.primaryStage.close();
        ManagerAccountPage.display();
    }
}
