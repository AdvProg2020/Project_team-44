package graphicView.userRegion.userAccount.managerAccount;

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
import model.Category;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoryPageController implements Initializable {
    private static Category currentCategory;
    @FXML
    public Label categoryName;
    @FXML
    public TableView<Attribute> attributesTable;
    @FXML
    public TableColumn<Attribute, StringProperty> attributesColumn;
    @FXML
    public TableView<Product> subProductsTable;
    @FXML
    public TableColumn<Product, StringProperty> subProductsColumn;


    public static void setCurrentCategory(Category currentCategory) {
        CategoryPageController.currentCategory = currentCategory;
    }

    public static Category getCurrentCategory() {
        return currentCategory;
    }

    ObservableList<Attribute> getAttributes() {
        ObservableList<Attribute> allAttributes = FXCollections.observableArrayList();
        for (String attribute : CategoryPageController.currentCategory.getAttributes()) {
            allAttributes.add(new Attribute(attribute));
        }
        return allAttributes;
    }

    ObservableList<Product> getProducts() {
        ObservableList<Product> allProducts = FXCollections.observableArrayList();
        for (model.product.Product subProduct : CategoryPageController.currentCategory.getAllSubProducts()) {
            allProducts.add(new Product(subProduct.getName()));
        }
        return allProducts;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryName.setText(CategoryPageController.currentCategory.getName());
        attributesColumn.setCellValueFactory(new PropertyValueFactory<>("attributes"));
        attributesTable.setItems(getAttributes());
        subProductsColumn.setCellValueFactory(new PropertyValueFactory<>("subProducts"));
        subProductsTable.setItems(getProducts());
    }

    @FXML
    public void logout() throws IOException {
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        CategoryPage.primaryStage.close();
        MainMenu.display(Main.window);
    }

    @FXML
    public void back() throws IOException {
        CategoryPage.primaryStage.close();
        ViewAllCategoriesPage.display();
    }

    @FXML
    public void edit() throws IOException {
        CategoryPage.primaryStage.close();
        EditCategoryPage.display();
    }

    public class Attribute {
        private final StringProperty attributes;

        public Attribute(String attributes) {
            this.attributes = new SimpleStringProperty(attributes);
        }

        public StringProperty attributesProperty() {
            return attributes;
        }
    }

    public class Product {
        private final StringProperty subProducts;

        public Product(String productName) {
            this.subProducts = new SimpleStringProperty(productName);
        }

        public String getSubProducts() {
            return subProducts.get();
        }

        public StringProperty subProductsProperty() {
            return subProducts;
        }
    }
}
