package client.graphicView.cart;

import client.graphicView.purchasePage.PurchasePage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class CartPageController implements Initializable {
    private final int PORT = 9054;
    private final String IP = "127.0.0.1";
    private static DataOutputStream out;
    private static DataInputStream in;

    @FXML
    private TableView<Cart> cartTableView;

    @FXML
    private TableColumn<Cart, StringProperty> productNameColumn;
    @FXML
    private TableColumn<Cart, IntegerProperty> priceFeeColumn;

    @FXML
    private TableColumn<Cart, IntegerProperty> quantityColumn;
    @FXML
    private TableColumn<Cart, IntegerProperty> totalAmountColumn;
    @FXML
    private TableColumn<Cart, Button> removeButtonColumn;
    @FXML
    private TableColumn<Cart, Button> increaseButtonColumn;
    @FXML
    private TableColumn<Cart, Button> decreaseButtonColumn;

    @FXML
    private Button checkoutButton;
    @FXML
    private Label totalAmountLabel;

    public static int totalAmountToPay() {
        try {
            out.writeUTF("totalAmountToPay");
            out.flush();
            return Integer.parseInt(in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // never hit the catch and this statement
        return 0;
//        return (int) PurchaserAccountController.processShowTotalPriceEach();
    }

    public void processInitialize() {
        try {
            Socket socket = new Socket(IP, PORT);
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
//            new Thread(this::input).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    ObservableList<Cart> getCart() {
        ObservableList<Cart> carts = FXCollections.observableArrayList();
        try {
            out.writeUTF("gatCart");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String[] cartInfo = in.readUTF().split("\n");
            for (String cartProductInfo : cartInfo) {
                String[] productInfo = cartProductInfo.split(":");
                try {
                    carts.add(new Cart(productInfo[0],
                            Integer.parseInt(productInfo[1]),
                            Integer.parseInt(productInfo[2])));
                }catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carts;
    }

    private void playButtonSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/media/sound/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString()));
        mediaPlayer.play();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        processInitialize();
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceFeeColumn.setCellValueFactory(new PropertyValueFactory<>("priceFee"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        removeButtonColumn.setCellValueFactory(new PropertyValueFactory<>("removeButton"));
        increaseButtonColumn.setCellValueFactory(new PropertyValueFactory<>("increaseButton"));
        decreaseButtonColumn.setCellValueFactory(new PropertyValueFactory<>("decreaseButton"));
        cartTableView.setItems(getCart());
    }

    @FXML
    private void gotoNextScene() {
        playButtonSound();
        CartPage.window.close();
        try {
            PurchasePage.display();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void increaseItemInCart(String productName) {
        try {
            out.writeUTF("increaseItemInCart:" + productName);
            out.flush();
            in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void decreaseItemInCart(String productName) {
        try {
            out.writeUTF("decreaseItemInCart:" + productName);
            out.flush();
            in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteItemInCart(String productName) {
        try {
            out.writeUTF("deleteItemInCart:" + productName);
            out.flush();
            in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // class that would be shown in Cart Table
    public class Cart {
        private StringProperty productName;
        private IntegerProperty priceFee;
        private IntegerProperty quantity;
        private IntegerProperty totalAmount;
        private Button removeButton;
        private Button increaseButton;
        private Button decreaseButton;

        Cart(String productName, int priceFee, int quantity) {
            this.productName = new SimpleStringProperty(productName);
            this.priceFee = new SimpleIntegerProperty(priceFee);
            this.quantity = new SimpleIntegerProperty(quantity);
            this.totalAmount = new SimpleIntegerProperty();
            this.removeButton = new Button();
            this.increaseButton = new Button();
            this.decreaseButton = new Button();
            // add listener to quantity, and relation between total amount and quantity
            this.totalAmount.bind(this.priceFee.multiply(quantity));

            // increase button
            increaseButton.setOnAction(actionEvent -> {
                playButtonSound();
                increaseItemInCart(productName);
                cartTableView.setItems(getCart());
                totalAmountLabel.setText("" + totalAmountToPay());
            });
//            try {
//                increaseButton.setGraphic(new ImageView(new Image(new FileInputStream("src/main/resources/media/image/deleteIcon.jpg"))));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }

            //  decrease button
            decreaseButton.setOnAction(actionEvent -> {
                playButtonSound();
                decreaseItemInCart(productName);
                cartTableView.setItems(getCart());
                totalAmountLabel.setText("" + totalAmountToPay());
            });

            //  remove button
            removeButton.setOnAction(actionEvent -> {
                playButtonSound();
                deleteItemInCart(productName);
                cartTableView.setItems(getCart());
                totalAmountLabel.setText("" + totalAmountToPay());
            });
        }

        public StringProperty productNameProperty() {
            return productName;
        }

        public IntegerProperty priceFeeProperty() {
            return priceFee;
        }


        public IntegerProperty quantityProperty() {
            return quantity;
        }

        public IntegerProperty totalAmountProperty() {
            return totalAmount;
        }

        public Button getRemoveButton() {
            return removeButton;
        }

        public Button getIncreaseButton() {
            return increaseButton;
        }

        public Button getDecreaseButton() {
            return decreaseButton;
        }
    }

}
