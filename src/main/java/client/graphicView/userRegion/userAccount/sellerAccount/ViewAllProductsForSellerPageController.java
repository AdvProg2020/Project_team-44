package client.graphicView.userRegion.userAccount.sellerAccount;

import client.Main;
import server.controller.LoginPageController;
import client.graphicView.mainMenu.MainMenu;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import server.model.account.Seller;
import server.model.product.Product;

import java.io.IOException;

public class ViewAllProductsForSellerPageController {
    public static Product currentProduct;
    private TableView<ProductProperty> tableView;

    public static TableView<ProductProperty> setTableView() {
        TableView<ProductProperty> tableView = new TableView<>();
        TableColumn<ProductProperty, String> productId = new TableColumn<>("Product Id");
        productId.setMinWidth(150);
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));

        TableColumn<ProductProperty, String> productName = new TableColumn<>("Name");
        productName.setMinWidth(100);
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ProductProperty, String> productCompanyName = new TableColumn<>("Company name");
        productCompanyName.setMinWidth(100);
        productCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        TableColumn<ProductProperty, Double> productPrice = new TableColumn<>("Price");
        productPrice.setMinWidth(100);
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<ProductProperty, Button> button = new TableColumn<>("Button");
        productPrice.setMinWidth(100);
        button.setCellValueFactory(new PropertyValueFactory<>("button"));

        tableView.setItems(getProductProperties((Seller) LoginPageController.getLoggedInAccount()));
        tableView.getColumns().addAll(productName, productId, productCompanyName, productPrice, button);
        tableView.setLayoutX(126);
        tableView.setLayoutY(150);
        return tableView;
    }

    public static ObservableList<ProductProperty> getProductProperties(Seller seller) {
        ObservableList<ProductProperty> productProperties = FXCollections.observableArrayList();
        for (Product product : seller.getProductsToSell().keySet()) {
            productProperties.add(new ProductProperty(product));
        }
        return productProperties;

    }

    public static class ProductProperty {
        private Product product;
        private Button button;
        private StringProperty name;
        private StringProperty productId;
        private StringProperty companyName;
        private StringProperty price;

        public Product getProduct() {
            return product;
        }

        public Button getButton() {
            return button;
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public String getProductId() {
            return productId.get();
        }

        public StringProperty productIdProperty() {
            return productId;
        }

        public String getCompanyName() {
            return companyName.get();
        }

        public StringProperty companyNameProperty() {
            return companyName;
        }

        public String getPrice() {
            return price.get();
        }

        public StringProperty priceProperty() {
            return price;
        }

        public ProductProperty(Product product) {
            this.product = product;
            this.button = new Button();
            this.name = new SimpleStringProperty(product.getName());
            this.productId = new SimpleStringProperty(product.getProductID());
            this.companyName = new SimpleStringProperty(product.getCompanyName());
            this.price = new SimpleStringProperty(String.valueOf(product.getPrice()));
            button.setOnAction(actionEvent -> {
                ViewAllProductsForSellerPage.primaryStage.close();
                try {
                    currentProduct = product;
                    ProductPageInfoForSeller.display();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    @FXML
    public void back() throws IOException {
        ViewAllProductsForSellerPage.primaryStage.close();
        SellerAccountPage.display();
    }
    @FXML
    public void logout() throws IOException {
        ViewAllProductsForSellerPage.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }
}

