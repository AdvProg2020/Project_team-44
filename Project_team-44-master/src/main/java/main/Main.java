package main;

import graphicView.mainMenu.MainMenu;
import graphicView.purchasePage.PurchasePage;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Category;
import model.account.Manager;
import model.product.Product;

import java.util.ArrayList;

public class Main extends Application {
    static ArrayList<Category> all = new ArrayList<>();
    public static Stage window;

    public static void main(String[] args) {
//        reload();
        new Manager("a", "b", "c", "d", "e", "f");
        new Category("phone", null, "tom.jpg");
        new Category("home", null, "tom.jpg");
        new Category("apple", Category.getCategoryByName("phone"), "tom.jpg");
        new Category("samsung", Category.getCategoryByName("phone"), "tom.jpg");
        new Product(Category.getCategoryByName("samsung"), "galaxy s10", "samsung", 10, "very good", "tom.jpg");
        new Product(Category.getCategoryByName("home"), "carpet", "iran", 1500, "high", "tom.jpg");
        new Product(Category.getCategoryByName("apple"), "iphone 11", "apple", 11, "excellent", "tom.jpg");
        new Product(Category.getCategoryByName("apple"), "iphone 7plus", "apple", 7, "excellent", "tom.jpg");
        new Product(Category.getCategoryByName("apple"), "iphone x", "apple", 10, "beautiful", "tom.jpg");
        new Product(Category.getCategoryByName("apple"), "iphone 12", "apple", 12, "beautiful", "tom.jpg");
        new Product(Category.getCategoryByName("samsung"), "galaxy s9", "samsung", 9, "medium", "tom.jpg");
        new Manager("q", "w", "e", "r", "t", "y");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        InfoSetPanel.display();
//        LoginPanel.display();
//        CartPage.display();
//        PurchasePage.display();
        MainMenu.display();
    }

    //
//        public static void updateJson(String path, ) {
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