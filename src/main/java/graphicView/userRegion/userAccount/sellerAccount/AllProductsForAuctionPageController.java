package graphicView.userRegion.userAccount.sellerAccount;

import controller.LoginPageController;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import model.account.Seller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllProductsForAuctionPageController implements Initializable {
    @FXML
    public TableView<Product> auctionTable;
    @FXML
    public TableColumn<Product , Label> productName;
    @FXML
    public TableColumn<Product , StringProperty> productPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        auctionTable.setItems(getProduct());
    }


    public class Product{
        private Label name;
        private StringProperty price;

        public Label getName() {
            return name;
        }

        public String getPrice() {
            return price.get();
        }

        public StringProperty priceProperty() {
            return price;
        }

        public Product(String name, String price) {
            this.name = new Label(name);
            this.price = new SimpleStringProperty(price);
            this.name.setOnMouseClicked(mouseEvent -> {
                AllProductsForAuctionPage.primaryStage.close();
                try {
                    SellerAuctionController.setProductToAuction(model.product.Product.getProductByName(name));
                    SellerAuction.display();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    ObservableList<Product> getProduct(){
        ObservableList<Product> allProducts = FXCollections.observableArrayList();
        Seller seller = (Seller)LoginPageController.loggedInAccount;
        for (model.product.Product product : seller.getProductsToSell().keySet()) {
            if(!seller.isProductInAuctionList(product)){
                allProducts.add(new Product(product.getName() , String.valueOf(product.getPrice())));
            }
        }
        return allProducts;
    }
    @FXML
    public void back() throws IOException {
        AllProductsForAuctionPage.primaryStage.close();
        SellerAccountPage.display();
    }
    @FXML
    public void logout() throws IOException {
        AllProductsForAuctionPage.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }
}
