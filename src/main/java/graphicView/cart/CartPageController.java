package graphicView.cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CartPageController implements Initializable {
    //    require to go to model
    public ObservableList<Cart> getCart() {
        ObservableList<Cart> carts = FXCollections.observableArrayList();
        carts.add(new Cart("Apple", 5));
        carts.add(new Cart("Orange", 4));
        carts.add(new Cart("Lemon", 3));
        carts.add(new Cart("Banana", 2));
        return carts;
    }

    @FXML
    TableView<Cart> cartTableView = new TableView<>();
//    @FXML
//    TableView<>
    TableColumn<Cart, String> productNameColumn = new TableColumn<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        productNameColumn.setCellValueFactory();
    }
}
