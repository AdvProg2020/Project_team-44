package graphicView.productMenu;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.Category;
import model.product.Product;

import java.util.ArrayList;

public class ProductsMenu {
    @FXML
    public MenuButton productsMenuButton;
    public MenuButton categoriesMenuButton;

    @FXML
    public void onGoods(Event event) {
        productsMenuButton.getItems().clear();
        for (Product allProduct : Product.getAllProducts()) {
            productsMenuButton.getItems().add(new MenuItem(allProduct.getName()));
        }
    }

    @FXML
    public void onCategories(Event event) {
        categoriesMenuButton.getItems().clear();
        for (Category category : Category.getAllParents()) {
            Menu menu = new Menu(category.getName());
            if (category.getSubCategories().isEmpty()) {
                for (Product allSubProduct : category.getAllSubProducts()) {
                    menu.getItems().add(new MenuItem(allSubProduct.getName()));
                }
                categoriesMenuButton.getItems().add(menu);
            } else {
                categoriesMenuButton.getItems().add(menu);
                doEachCategory(category.getSubCategories(), menu);
            }
        }
    }

    public void doEachCategory(ArrayList<Category> sub, Menu menu) {
        for (Category category : sub) {
            Menu subMenu = new Menu(category.getName());
            if (category.getSubCategories().size() == 0) {
                for (Product allSubProduct : category.getAllSubProducts()) {
                    subMenu.getItems().add(new MenuItem(allSubProduct.getName()));
                }
                menu.getItems().add(subMenu);
            } else {
                menu.getItems().add(subMenu);
                doEachCategory(category.getSubCategories(), subMenu);
            }
        }
    }
}
