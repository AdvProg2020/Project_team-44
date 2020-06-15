package graphicView.cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CartPageController implements Initializable {
    //    require to go to model
    public ObservableList<Cart> getCart() {
        ObservableList<Cart> carts = FXCollections.observableArrayList();
        carts.add(new Cart("Apple", "Digikala", "5", "5", ""));
        carts.add(new Cart("Boob", "Refah", "100", "28", ""));
        carts.add(new Cart("Pussy", "Shahrvand", "1000", "2", ""));
        carts.add(new Cart("Dick", "Shixon", "1", "1", ""));
        return carts;
    }
    @FXML
    TableView<Cart> cartTableView = new TableView<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
