package graphicView.userRegion.userAccount.managerAccount;

import controller.LoginPageController;
import exception.FilterNotExistsException;
import graphicView.mainMenu.MainMenu;
import graphicView.productMenu.ProductsMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.Main;
import model.Category;
import model.product.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewAllCategoriesPageController{
    @FXML
    public MenuButton allCategoriesButton;

    @FXML
    public void onCategories() {
        allCategoriesButton.getItems().clear();
        for (Category category : Category.getAllParents()) {
            Menu menu = new Menu(category.getName());
            allCategoriesButton.getItems().add(menu);
            menu.setOnAction(actionEvent -> {
                try {
                    ViewAllCategoriesPage.primaryStage.close();
                    CategoryPageController.setCurrentCategory(category);
                    CategoryPage.display();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            for (Category subCategory : category.getSubCategories()) {
                MenuItem subMenu = new MenuItem(subCategory.getName());
                subMenu.setOnAction(actionEvent -> {
                    try {
                        ViewAllCategoriesPage.primaryStage.close();
                        CategoryPageController.setCurrentCategory(subCategory);
                        CategoryPage.display();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                menu.getItems().add(subMenu);
                for (Category subSubCategory : subCategory.getSubCategories()) {
                    Menu subSubMenu = new Menu(subSubCategory.getName());
                    subSubMenu.setOnAction(actionEvent -> {
                        try {
                            ViewAllCategoriesPage.primaryStage.close();
                            CategoryPageController.setCurrentCategory(subSubCategory);
                            CategoryPage.display();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }
    }
    @FXML
    public void logout() throws IOException {
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        ViewAllCategoriesPage.primaryStage.close();
        MainMenu.display(Main.window);
    }
    @FXML
    public void back() throws IOException {
        ViewAllCategoriesPage.primaryStage.close();
        ManagerAccountPage.display();
    }
    @FXML
    public void addCategory() throws IOException {
        ViewAllCategoriesPage.primaryStage.close();
        AddCategoryPage.display();
    }
}
