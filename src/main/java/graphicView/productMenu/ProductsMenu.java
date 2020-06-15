package graphicView.productMenu;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.Category;
import model.product.Product;

import java.util.ArrayList;
import java.util.Comparator;

public class ProductsMenu {
    private ArrayList<Product> sorted = Product.getAllProducts();
    @FXML
    public MenuButton productsMenuButton;
    public MenuButton categoriesMenuButton;
    public MenuButton filterMenuButton;
    public CheckMenuItem timeItem;
    public CheckMenuItem scoreItem;
    public CheckMenuItem viewItem;
    public CheckMenuItem priceItem;
    public CheckMenuItem productNameItem;
    public CheckMenuItem categoryItem;

    @FXML
    public void onGoods(Event event) {
        productsMenuButton.getItems().clear();
        for (Product product : sorted) {
            productsMenuButton.getItems().add(new MenuItem(product.getName()));
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

    private void doEachCategory(ArrayList<Category> sub, Menu menu) {
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

    @FXML
    public void onSort(Event event) {

    }

    public void onFilters(Event event) {

    }

    public void clickTime(ActionEvent actionEvent) {
        scoreItem.setSelected(false);
        viewItem.setSelected(false);
        priceItem.setSelected(false);
        sorted.sort(new CompareByDate());
        onGoods(actionEvent);
    }

    public void clickScore(ActionEvent actionEvent) {
        timeItem.setSelected(false);
        viewItem.setSelected(false);
        priceItem.setSelected(false);
        if (scoreItem.isSelected())
        sorted.sort(new CompareByScore());
        else sorted.sort(new CompareByDate());
        onGoods(actionEvent);
    }

    public void clickView(ActionEvent actionEvent) {
        scoreItem.setSelected(false);
        timeItem.setSelected(false);
        priceItem.setSelected(false);
        if (viewItem.isSelected())
            sorted.sort(new CompareByView());
        else sorted.sort(new CompareByDate());
        onGoods(actionEvent);
    }

    public void clickPrice(ActionEvent actionEvent) {
        scoreItem.setSelected(false);
        timeItem.setSelected(false);
        viewItem.setSelected(false);
        if (priceItem.isSelected())
            sorted.sort(new CompareByPrice());
        else sorted.sort(new CompareByDate());
        onGoods(actionEvent);
    }

    public void clickProductName(ActionEvent actionEvent) {
        categoryItem.setSelected(false);
        if (productNameItem.isSelected())
            sorted.sort(new CompareByProductName());
        else
            sorted.sort(new CompareByDate());
        onGoods(actionEvent);
    }

    public void clickCategory(ActionEvent actionEvent) {
        productNameItem.setSelected(false);
        if (categoryItem.isSelected()) {
            sorted.sort(new CompareByCategory());
            onGoods(actionEvent);
        }
    }

    class CompareByProductName implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    class CompareByCategory implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getCategory().getName().compareTo(o2.getCategory().getName());
        }
    }

    class CompareByDate implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            return o2.getGeneratedDate().compareTo(o1.getGeneratedDate());
        }
    }

    class CompareByScore implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            return (int) (o1.getAverageRating() - o2.getAverageRating());
        }
    }

    class CompareByView implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getViewTimes() - o2.getViewTimes();
        }
    }

    class CompareByPrice implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            return (int) (o2.getPrice() - o1.getPrice());
        }
    }
}
