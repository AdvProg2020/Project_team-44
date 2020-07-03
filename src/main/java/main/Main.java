package main;

import graphicView.userRegion.userAccount.managerRequestions.addOff.AddOffRequest;
import graphicView.userRegion.userAccount.managerRequestions.addSeller.AddSellerRequest;
import graphicView.userRegion.userAccount.managerRequestions.removeProduct.RemoveProductRequest;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Category;
import model.account.Seller;
import model.offer.Offer;
import model.product.Product;
import model.requests.RequestForAddOff;
import model.requests.RequestForEditOff;
import model.requests.RequestForRemoveProduct;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {
    static ArrayList<Category> all = new ArrayList<>();
    public static Stage window;
    public static Product good;

    static MediaPlayer mediaPlayer;

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(String songName) {
        Main.mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/media/sound/" + songName).toURI().toString()));
        Main.mediaPlayer.setAutoPlay(true);
        Main.mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
    }


    public static void main(String[] args) throws FileNotFoundException {
        Seller seller = new Seller("a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a");
        Product p1 = new Product(null,
                "apple",
                "digikala",
                2000,
                "",
                "");
        Product p2 = new Product(null,
                "orange",
                "amazon",
                3000,
                "",
                "");
        Product p3 = new Product(null,
                "pineapple",
                "gajmarket",
                4000,
                "",
                "");
        ArrayList<Product> allProducts = new ArrayList<>();
        allProducts.add(p1);
        allProducts.add(p2);
        allProducts.add(p3);
        for (int i = 0; i < 5; i++) {
            new RequestForAddOff(seller,
                    allProducts,
                    new Date(),
                    new Date(),
                    25);
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AddOffRequest.display();
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