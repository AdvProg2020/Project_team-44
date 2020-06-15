package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Category;
import model.account.Account;
import model.account.Manager;
import model.account.Purchaser;
import model.account.Seller;
import model.product.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {
    static ArrayList<Category> all = new ArrayList<>();
    public static Stage window;

    public static void main(String[] args) {
//        reload();
//        new Manager("a", "b", "c", "d", "e", "f");
//        new Category("phone", null);
//        new Category("home", null);
//        new Category("apple", Category.getCategoryByName("phone"));
//        new Category("samsung", Category.getCategoryByName("phone"));
//        new Category("galaxy", Category.getCategoryByName("samsung"));
//        new Product(Category.getCategoryByName("galaxy"), "galaxy s10", "samsung", 140, "very good");
//        new Product(Category.getCategoryByName("home"), "carpet", "iran", 1500, "high");
//        new Product(Category.getCategoryByName("galaxy"), "galaxy s9", "samsung", 150, "medium");
//        new Manager("q", "w", "e", "r", "t", "y");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        Pane root = FXMLLoader.load(getClass().getResource("/graphicView/mainMenu/mainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //    public static void updateJson(String path, ) {
//
//    }
    private static void setCategoryParent(ArrayList<Category> all) {
        ArrayList<Category> allSub;
        if (all.size() > 0) {
            for (Category allCategory : all) {
                if (allCategory.getSubCategories().size() > 0) {
                    allSub = allCategory.getSubCategories();
                    for (Category category1 : allSub) {
                        Category.getCategoryByName(category1.getName()).setParent(allCategory);
                    }
                    setCategoryParent(allSub);
                }
            }
        }
    }

    private static void setProductCategory() {
        ArrayList<Product> allSub;
        for (Category allCategory : Category.getAllCategories()) {
            if (allCategory.getAllSubProducts().size() > 0) {
                allSub = allCategory.getAllSubProducts();
                for (Product product : allSub) {
                    Product.getProductByID(product.getProductID()).setCategory(allCategory);
                }
            }
        }
    }

//    private static void reload() {
//        Category.setAllCategories(new Json<Category>().getAllJson("src/main/resources/Categories", "category"));
//        Product.setAllProducts(new Json<Product>().getAllJson("src/main/resources/Products", "product"));
//        Manager.setAllManagers(new Json<Manager>().getAllJson("src/main/resources/Accounts/Managers", "manager"));
//        Seller.setAllSeller(new Json<Seller>().getAllJson("src/main/resources/Accounts/Sellers", "seller"));
//        Purchaser.setAllPurchaser(new Json<Purchaser>().getAllJson("src/main/resources/Accounts/Purchasers", "purchaser"));
//        Account.getAllAccounts().addAll(Manager.getAllManagers());
//        Account.getAllAccounts().addAll(Seller.getAllSeller());
//        Account.getAllAccounts().addAll(Purchaser.getAllPurchaser());
//        setCategoryParent(Category.getAllCategories());
//        setProductCategory();
//    }
}