package client.graphicView.userRegion.userAccount.sellerAccount;

import client.Main;
import client.graphicView.mainMenu.MainMenu;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class SellerAuctionController implements Initializable {
    private static String productToAuction;
    private final int port = 9006;
    private final String ip = "127.0.0.1";
    @FXML
    public TextField finalYear;
    @FXML
    public TextField finalMonth;
    @FXML
    public TextField finalDay;
    @FXML
    public TextField finalHour;
    @FXML
    public TextField finalMinute;
    @FXML
    public Label alertMessage;
    private DataOutputStream out;
    private DataInputStream in;

    public static void setProductToAuction(String productToAuctionName) {
        SellerAuctionController.productToAuction = productToAuctionName;
    }

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
    public void back() throws IOException {
        AllProductsForAuctionPage.primaryStage.close();
        SellerAccountPage.display();
    }

    @FXML
    public void logout() throws IOException {
        AllProductsForAuctionPage.primaryStage.close();
        out.writeUTF("logout");
        out.flush();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }

    @FXML
    public void addAuction() throws IOException {
        if (!finalYear.getText().matches("\\d\\d\\d\\d")) {
            alertMessage.setText("Please enter a valid year!");
            return;
        }
        if (!finalMonth.getText().matches("\\d\\d")) {
            alertMessage.setText("Please enter a valid month!");
            return;
        }
        if (!finalDay.getText().matches("\\d\\d")) {
            alertMessage.setText("Please enter a valid day!");
            return;
        }
        if (!finalHour.getText().matches("\\d\\d")) {
            alertMessage.setText("Please enter a valid hour!");
            return;
        }
        if (!finalMinute.getText().matches("\\d\\d")) {
            alertMessage.setText("Please enter a valid minute!");
            return;
        }
        int year = Integer.parseInt(finalYear.getText()) - 1900;
        int month = Integer.parseInt(finalMonth.getText()) - 1;
        int day = Integer.parseInt(finalDay.getText());
        int hour = Integer.parseInt(finalHour.getText());
        int minute = Integer.parseInt(finalMinute.getText());
        out.writeUTF("create_auction " + productToAuction + " " + year +
                " " + month + " " + day + " " + hour + " " + minute);
        out.flush();
        alertMessage.setText("");
        alertMessage.setTextFill(Color.GREEN);
        alertMessage.setText("Add Auction Successful!");

    }
}
