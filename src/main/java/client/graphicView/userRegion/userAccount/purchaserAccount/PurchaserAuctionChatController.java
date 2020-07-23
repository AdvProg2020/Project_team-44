package client.graphicView.userRegion.userAccount.purchaserAccount;

import server.controller.LoginPageController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import server.main.ShopServer;
import server.model.account.Account;
import server.model.account.Manager;
import server.model.product.Auction;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

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
    private Timeline countDown;
    private String token;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date date = new Date();
        long diff = PurchaserAuctionChat.thisAuction.getFinalDate().getTime() - date.getTime();
        if (diff > 0) {
            this.second = (int) (diff / 1000 % 60);
            this.minute = (int) (diff / (1000 * 60) % 60);
            this.hour = (int) (diff / (60 * 60 * 1000));
            creditId.setText("Credit : " + LoginPageController.getLoggedInAccount().getBalance() + " $");
            process();
        } else {
            Auction.getAllAuctions().remove(PurchaserAuctionChat.thisAuction);
        }
    }

    public void input() {
        while (true) {
            String input = null;
            try {
                input = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.startsWith("from ")) {
                String sender = input.split("\\s")[1];
                String message = input.substring(5 + sender.length() + 1);
                processChatMessage(sender, message);
            } else if (input.startsWith("the most price is : ")) {
                String finalInput = input;
                Platform.runLater(() -> {
                    mostPriceId.setText("The most price suggested is : " + finalInput.substring(20) + " $");
                    if (lastEnteredPrice == Integer.parseInt(mostPriceId.getText().substring(30).split("\\s")[0])) {
                        try {
                            out.writeUTF("set_winner " + token);
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else if (input.startsWith("the bid winner is : ")) {
                String[] parts = input.substring(20).split("\\s");
                Platform.runLater(() -> mostPriceId.setText(parts[0] + " won the BID with " + parts[1] + " $"));
                Auction.getAllAuctions().remove(PurchaserAuctionChat.thisAuction);
                enterButton.setDisable(true);
                if (LoginPageController.getLoggedInAccount().getUserName().equals(mostPriceId.getText().split("\\s")[0])) {
                    LoginPageController.getLoggedInAccount().setBalance(LoginPageController.getLoggedInAccount().getBalance() - lastEnteredPrice);
                }
                lastEnteredPrice = -1;
                break;
            } else if (input.startsWith("all purchaser is : ")) {
                purchasersMenuId.getItems().clear();
                ArrayList<Account> allPurchasers = new ArrayList<>();
                for (String s : input.substring(19).split("\\s")) {
                    if (!s.isBlank())
                        allPurchasers.add(Account.getAccountByUsername(s));
                }
                for (int i = 0; i < allPurchasers.size(); i++) {
                    MenuItem menuItem = new MenuItem((i + 1) + ". " + allPurchasers.get(i).getUserName());
                    int finalI = i;
                    if (!userToISFirstTime.containsKey(allPurchasers.get(i).getUserName())) {
                        int finalI1 = i;
                        Platform.runLater(() -> {
                            Stage stage = new Stage();
                            Pane root = new Pane();
                            ScrollPane scrollPane = new ScrollPane(root);
                            Scene scene = new Scene(scrollPane, 200, 200);
                            stage.setScene(scene);
                            userToScene.put(allPurchasers.get(finalI1).getUserName(), scene);
                            userToStage.put(allPurchasers.get(finalI1).getUserName(), stage);
                            userToRoot.put(allPurchasers.get(finalI1).getUserName(), root);
                            userToVBoxNum.put(allPurchasers.get(finalI1).getUserName(), 0);
                            userToISFirstTime.put(allPurchasers.get(finalI1).getUserName(), true);
                        });
                    } else userToISFirstTime.replace(allPurchasers.get(i).getUserName(), false);
                    menuItem.setOnAction(e -> {
                        usernameFieldId.setText(allPurchasers.get(finalI).getUserName());
                        if (!userToISFirstTime.get(allPurchasers.get(finalI).getUserName()))
                            userToStage.get(usernameFieldId.getText()).show();
                    });
                    purchasersMenuId.getItems().add(menuItem);
                }
            }
        }
    }

    public void output() {
        try {
            out.writeUTF("get_most_price");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void process() {
        try {
            Socket socket = new Socket(ShopServer.IP, ShopServer.port);
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            setOut(out);
            setIn(in);
            mostPriceId.setText("The most price suggested is : 0 $");
            try {
                out.writeUTF("Auction : " + LoginPageController.getLoggedInAccount().getUserName());
                out.flush();
                String input = in.readUTF();
                if (input.startsWith("token : ")) {
                    token = input.substring(8);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.countDown = new Timeline(new KeyFrame(Duration.millis(1000), e -> processShowCountDown()));
            this.countDown.setCycleCount(Animation.INDEFINITE);
            this.countDown.play();

            new Thread(this::output).start();
            new Thread(this::input).start();
        } catch (
                IOException e) {
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

    public void processShowCountDown() {
        String hours = String.valueOf(this.hour);
        String minutes = String.valueOf(this.minute);
        String seconds = String.valueOf(this.second);
        if (this.hour < 10)
            hours = "0" + this.hour;
        if (this.minute < 10)
            minutes = "0" + this.minute;
        if (this.second < 10)
            seconds = "0" + this.second;
        this.timer.setText(hours + ":" + minutes + ":" + seconds);
        this.second--;
        if (this.second == -1) {
            this.second = 59;
            this.minute--;
        }
        if (this.minute == -1) {
            this.minute = 59;
            this.hour--;
        }
        if (this.hour == -1 && this.minute == 59 && this.second == 59) {
            try {
                out.writeUTF("get_winner");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.countDown.stop();
        }
    }

    public void processChatMessage(String sender, String message) {
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

    @FXML
    public void showAllPurchasersAction(MouseEvent actionEvent) throws IOException {
        getOut().writeUTF("get_all_purchaser " + LoginPageController.getLoggedInAccount().getUserName());
        getOut().flush();
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
        Platform.runLater(() -> messageId.setText(""));
    }

    @FXML
    public void enterAction() throws IOException {
        if (!enterFieldId.getText().matches("\\d+\\s*"))
            return;
        if (Integer.parseInt(enterFieldId.getText()) > LoginPageController.getLoggedInAccount().getBalance() - Manager.getAllManagers().get(0).getMinAmount()) {
            Platform.runLater(() -> {
                warningId.setTextFill(Color.PALEVIOLETRED);
                warningId.setText("The entered value is more than as you can");
            });
        } else if (Integer.parseInt(enterFieldId.getText()) < Integer.parseInt(mostPriceId.getText().substring(30).split("\\s")[0]) + 5) {
            Platform.runLater(() -> {
                warningId.setTextFill(Color.PALEVIOLETRED);
                warningId.setText("The entered value is less than server.main.Main regular price");
            });
        } else {
            lastEnteredPrice = Integer.parseInt(enterFieldId.getText());
            Platform.runLater(() -> {
                warningId.setTextFill(Color.FORESTGREEN);
                warningId.setText("Successfully entered");
            });
            getOut().writeUTF("most price : " + enterFieldId.getText());
            getOut().flush();
        }
    }
}
