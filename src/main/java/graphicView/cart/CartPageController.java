package graphicView.cart;

import controller.PurchaserAccountController;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    //    ObservableList<Cart> getCart() {
//        ObservableList<Cart> carts = FXCollections.observableArrayList();
//        for (Product product : PurchaserAccountController.getCart()) {
//            carts.add(new Cart(product.getName(), (int) product.getPrice()));
//        }
//        return carts;
//    }
    ObservableList<Cart> getCart() {
        ObservableList<Cart> carts = FXCollections.observableArrayList();
        carts.add(new Cart("Apple", 5));
        carts.add(new Cart("Orange", 4));
        carts.add(new Cart("Lemon", 3));
        carts.add(new Cart("Banana", 2));
        return carts;
    }

//    SimpleStringProperty totalAmountToPay() {
//        int amount = 0;
//        for (Cart cart : allCarts) {
//            amount += cart.totalAmount.intValue();
//        }
//        return new SimpleStringProperty("" + (new SimpleIntegerProperty(amount)).getValue());
//    }

    SimpleStringProperty totalAmountToPay() {
        int amount = 0;
        for (Cart cart : allCarts) {
            amount += cart.totalAmount.intValue();
        }
        return new SimpleStringProperty("" + (new SimpleIntegerProperty(amount)).getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        System.out.println("We are fudged up!");
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

        Cart(String productName, int priceFee) {
            this.productName = new SimpleStringProperty(productName);
            this.priceFee = new SimpleIntegerProperty(priceFee);
            this.quantity = new SimpleIntegerProperty(1);
            this.totalAmount = new SimpleIntegerProperty();
            this.removeButton = new Button();
            this.increaseButton = new Button();
            this.decreaseButton = new Button();
            // add listener to quantity, and relation between total amount and quantity
            this.totalAmount.bind(this.priceFee.multiply(quantity));

            // increase button
            increaseButton.setOnAction(actionEvent -> {
                quantity.setValue(quantity.getValue() + 1);
                PurchaserAccountController.increaseItemInCart(productName);
                cartTableView.setItems(getCart());
                totalAmountLabel.setText(totalAmountToPay().getValue());
            });
            try {
                increaseButton.setGraphic(new ImageView(new Image(new FileInputStream("src\\main\\resources\\media\\image\\deleteIcon.png"))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //  decrease button
            decreaseButton.setOnAction(actionEvent -> {
                quantity.setValue(quantity.getValue() - 1);
                PurchaserAccountController.decreaseItemInCart(productName);
                cartTableView.setItems(getCart());
                totalAmountLabel.setText(totalAmountToPay().getValue());
            });

            //  remove button
            removeButton.setOnAction(actionEvent -> {
                PurchaserAccountController.deleteItemInCart(productName);
                cartTableView.setItems(getCart());
                totalAmountLabel.setText(totalAmountToPay().getValue());
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
