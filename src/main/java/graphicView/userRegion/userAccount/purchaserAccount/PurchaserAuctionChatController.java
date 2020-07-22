package graphicView.userRegion.userAccount.purchaserAccount;

import controller.LoginPageController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.ShopServer;
import model.account.Account;
import model.account.Manager;
import model.product.Auction;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PurchaserAuctionChatController implements Initializable {

    @FXML
    public MenuButton purchasersMenuId;
    @FXML
    public Label usernameFieldId;
    @FXML
    public TextArea messageId;
    @FXML
    public Label creditId;
    @FXML
    public Label mostPriceId;
    @FXML
    public TextField enterFieldId;
    @FXML
    public Label warningId;
    @FXML
    public Button enterButton;
    @FXML
    public Label timer;
    @FXML
    public Pane root;

    private DataOutputStream out;
    private DataInputStream in;
    public HashMap<String, Stage> userToStage = new HashMap<>();
    public HashMap<String, Scene> userToScene = new HashMap<>();
    public HashMap<String, Integer> userToVBoxNum = new HashMap<>();
    public HashMap<String, Pane> userToRoot = new HashMap<>();
    public HashMap<String, Boolean> userToISFirstTime = new HashMap<>();
    private int lastEnteredPrice = -1;
    private int second;
    private int minute;
    private int hour;
    private Timeline checkWinner;
    private Timeline mostPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        long diff = PurchaserAuctionChat.thisAuction.getFinalDate().getTime() - PurchaserAuctionChat.thisAuction.getInitialDate().getTime();
        this.second = (int) (diff / 1000 % 60);
        this.minute = (int) (diff / (1000 * 60) % 60);
        this.hour = (int) (diff / (60 * 60 * 1000));
        creditId.setText("Credit : " + LoginPageController.getLoggedInAccount().getBalance() + " $");
        try {
            Socket socket1 = new Socket(ShopServer.IP, ShopServer.port);
            DataOutputStream dataOut1 = new DataOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
            DataInputStream dataIn1 = new DataInputStream(new BufferedInputStream(socket1.getInputStream()));
            this.mostPrice = new Timeline(new KeyFrame(Duration.millis(100), e -> processSetMostPrice(dataOut1, dataIn1)));
            this.mostPrice.setCycleCount(Animation.INDEFINITE);
            this.mostPrice.play();
            Socket socket2 = new Socket(ShopServer.IP, ShopServer.port);
            DataOutputStream dataOut2 = new DataOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
            DataInputStream dataIn2 = new DataInputStream(new BufferedInputStream(socket2.getInputStream()));
            this.checkWinner = new Timeline(new KeyFrame(Duration.seconds(1), e -> checkTheWinner(dataOut2, dataIn2)));
            this.checkWinner.setCycleCount(Animation.INDEFINITE);
            this.checkWinner.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Socket socket = new Socket(ShopServer.IP, ShopServer.port);
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            setOut(out);
            setIn(in);
            out.writeUTF("Auction : " + LoginPageController.getLoggedInAccount().getUserName());
            out.flush();
            new Thread(() -> {
                try {
                    while (true) {
                        String response = getIn().readUTF();
                        String sender = response.split("\\s")[1];
                        String message = response.substring(5 + sender.length() + 1);
                        while (true) {
                            if (userToISFirstTime.containsKey(sender)) {
                                VBox vBox = new VBox();
                                vBox.setLayoutX(60);
                                vBox.setLayoutY(userToVBoxNum.get(sender) * 30);
                                Text textArea = new Text(message);
                                textArea.setFill(Color.DARKRED);
                                vBox.getChildren().add(textArea);
                                if (userToStage.get(sender).isShowing()) {
                                    userToStage.get(sender).close();
                                    userToRoot.get(sender).getChildren().add(vBox);
                                    userToVBoxNum.replace(sender, userToVBoxNum.get(sender) + 1);
                                    userToStage.get(sender).show();
                                } else {
                                    userToRoot.get(sender).getChildren().add(vBox);
                                    userToVBoxNum.replace(sender, userToVBoxNum.get(sender) + 1);
                                }
                                break;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void processSetMostPrice(DataOutputStream dataOut, DataInputStream dataIn) {
        try {
            dataOut.writeUTF("get_most_price");
            dataOut.flush();
            String response = dataIn.readUTF();
            mostPriceId.setText("The most price suggested is : " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public DataInputStream getIn() {
        return in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    @FXML
    public void showAllPurchasersAction(MouseEvent actionEvent) throws IOException {
        ArrayList<Account> allPurchasers = new ArrayList<>();
        purchasersMenuId.getItems().clear();
        Socket socket = new Socket(ShopServer.IP, ShopServer.port);
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        dataOutputStream.writeUTF("get_all_purchaser " + LoginPageController.getLoggedInAccount().getUserName());
        dataOutputStream.flush();
        String response = dataInputStream.readUTF();
        for (String s : response.split("\\s")) {
            if (!s.isBlank())
                allPurchasers.add(Account.getAccountByUsername(s));
        }
        for (int i = 0; i < allPurchasers.size(); i++) {
            MenuItem menuItem = new MenuItem((i + 1) + ". " + allPurchasers.get(i).getUserName());
            int finalI = i;
            if (!userToISFirstTime.containsKey(allPurchasers.get(i).getUserName())) {
                Stage stage = new Stage();
                Pane root = new Pane();
                ScrollPane scrollPane = new ScrollPane(root);
                Scene scene = new Scene(scrollPane, 200, 200);
                stage.setScene(scene);
                userToScene.put(allPurchasers.get(i).getUserName(), scene);
                userToStage.put(allPurchasers.get(i).getUserName(), stage);
                userToRoot.put(allPurchasers.get(i).getUserName(), root);
                userToVBoxNum.put(allPurchasers.get(i).getUserName(), 0);
                userToISFirstTime.put(allPurchasers.get(i).getUserName(), true);
            } else userToISFirstTime.replace(allPurchasers.get(i).getUserName(), false);
            menuItem.setOnAction(e -> {
                usernameFieldId.setText(allPurchasers.get(finalI).getUserName());
                if (!userToISFirstTime.get(allPurchasers.get(finalI).getUserName()))
                    userToStage.get(usernameFieldId.getText()).show();
            });
            purchasersMenuId.getItems().add(menuItem);
        }
    }

    @FXML
    public void sendMessage() throws IOException {
        getOut().writeUTF("send_message " + "from " + LoginPageController.getLoggedInAccount().getUserName() + " to " + usernameFieldId.getText() + " " + messageId.getText());
        getOut().flush();
        VBox vBox = new VBox();
        vBox.setLayoutX(60);
        vBox.setLayoutY(userToVBoxNum.get(usernameFieldId.getText()) * 30);
        Text textArea = new Text(messageId.getText());
        textArea.setFill(Color.DARKGREEN);
        vBox.getChildren().add(textArea);
        if (userToStage.get(usernameFieldId.getText()).isShowing())
            userToStage.get(usernameFieldId.getText()).close();
        userToRoot.get(usernameFieldId.getText()).getChildren().add(vBox);
        userToVBoxNum.replace(usernameFieldId.getText(), userToVBoxNum.get(usernameFieldId.getText()) + 1);
        messageId.setText("");
    }

    @FXML
    public void enterAction(ActionEvent actionEvent) throws IOException {
        if (!enterFieldId.getText().matches("\\d+\\s*"))
            return;
        if (Integer.parseInt(enterFieldId.getText()) > LoginPageController.getLoggedInAccount().getBalance() - Manager.getAllManagers().get(0).getMinAmount()) {
            warningId.setTextFill(Color.PALEVIOLETRED);
            warningId.setText("The entered value is more than as you can");
        } else if (Integer.parseInt(enterFieldId.getText()) < Integer.parseInt(mostPriceId.getText().substring(30)) + 5) {
            warningId.setTextFill(Color.PALEVIOLETRED);
            warningId.setText("The entered value is less than a regular price");
        } else {
            lastEnteredPrice = Integer.parseInt(enterFieldId.getText());
            warningId.setTextFill(Color.FORESTGREEN);
            warningId.setText("Successfully entered");
            Socket socket = new Socket(ShopServer.IP, ShopServer.port);
            DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            dataOut.writeUTF("most price : " + enterFieldId.getText());
            dataOut.flush();
        }
    }

    public void checkTheWinner(DataOutputStream out, DataInputStream in) {
        String hours = String.valueOf(this.hour);
        String minutes = String.valueOf(this.minute);
        String seconds = String.valueOf(this.second);
        if (hour < 10)
            hours = "0" + hour;
        if (minute < 10)
            minutes = "0" + minute;
        if (second < 10)
            seconds = "0" + second;
        timer.setText(hours + ":" + minutes + ":" + seconds);
        second--;
        if (second == -1) {
            second = 59;
            minute--;
        }
        if (minute == -1) {
            minute = 59;
            hour--;
        }
        String response = null;
        try {
            out.writeUTF("get_winner");
            out.flush();
            response = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (hour == -1 && minute == 59 && second == 59) {
            mostPrice.stop();
            if (lastEnteredPrice == Integer.parseInt(mostPriceId.getText().substring(30))) {
                try {
                    out.writeUTF("set_winner " + LoginPageController.getLoggedInAccount().getUserName());
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (response.matches("\\w+\\s\\d+")) {
                String[] parts = response.split("\\s");
                mostPriceId.setText(parts[0] + " won the BID with " + parts[1] + " $");
                enterButton.setDisable(true);
                Auction.getAllAuctions().remove(PurchaserAuctionChat.thisAuction);
            }
            if (LoginPageController.getLoggedInAccount().getUserName().equals(mostPriceId.getText().split("\\s")[0])) {
                LoginPageController.getLoggedInAccount().setBalance(LoginPageController.getLoggedInAccount().getBalance() - lastEnteredPrice);
            }
            lastEnteredPrice = -1;
            checkWinner.stop();
        }

    }
}
