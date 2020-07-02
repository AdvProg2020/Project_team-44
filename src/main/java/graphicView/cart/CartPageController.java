package graphicView.cart;

import controller.LoginPageController;
import controller.PurchaserAccountController;
import graphicView.purchasePage.PurchasePage;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.account.Purchaser;
import model.product.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CartPageController implements Initializable {
    private ArrayList<Cart> allCarts = new ArrayList<>();

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

    ObservableList<Cart> getCart() {
        ObservableList<Cart> carts = FXCollections.observableArrayList();
        for (Product product : PurchaserAccountController.getCartProducts()) {
            carts.add(new Cart(product.getName(),
                    (int) product.getPrice(),
                    ((Purchaser) LoginPageController.getLoggedInAccount()).getCart().get(Product.getProductByName(product.getName()))));
        }
        return carts;
    }

    public static int totalAmountToPay() {
        return (int) PurchaserAccountController.processShowTotalPriceEach();
    }

    private void playButtonSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/media/sound/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString()));
        mediaPlayer.play();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("hey");
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
                PurchaserAccountController.increaseItemInCart(productName);
                cartTableView.setItems(getCart());
                totalAmountLabel.setText("" + totalAmountToPay());
            });
            try {
                increaseButton.setGraphic(new ImageView(new Image(new FileInputStream("src\\main\\resources\\media\\image\\deleteIcon.png"))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //  decrease button
            decreaseButton.setOnAction(actionEvent -> {
                playButtonSound();
                PurchaserAccountController.decreaseItemInCart(productName);
                cartTableView.setItems(getCart());
                totalAmountLabel.setText("" + totalAmountToPay());
            });

            //  remove button
            removeButton.setOnAction(actionEvent -> {
                playButtonSound();
                PurchaserAccountController.deleteItemInCart(productName);
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
