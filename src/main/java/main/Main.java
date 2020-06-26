package main;

import graphicView.mainMenu.MainMenu;
import graphicView.purchasePage.PurchasePage;
import graphicView.userRegion.loginPanel.LoginPanel;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Category;
import model.account.Account;
import model.account.Manager;
import model.account.Purchaser;
import model.account.Seller;
import model.comment.Comment;
import model.product.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main extends Application {
    static ArrayList<Category> all = new ArrayList<>();
    public static Stage window;
    public static Product good;
    public static Stage accountRegionStage;
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

    public static void setAccountRegionStage(Stage accountRegionStage) {
        Main.accountRegionStage = accountRegionStage;
    }

    public static void main(String[] args) throws FileNotFoundException {
        reload();
        setMediaPlayer("The Swimmer.mp3");
//        new Manager("a", "b", "c", "d", "e", "f");
//        new Category("digital", null, "tom.jpg");
//        new Category("home", null, "tom.jpg");
//        new Category("laptop", Category.getCategoryByName("digital"), "tom.jpg");
//        new Category("phone", Category.getCategoryByName("digital"), "tom.jpg");
//        new Category("vacuum cleaner", Category.getCategoryByName("home"), "tom.jpg");
//        Category category = new Category("apple", Category.getCategoryByName("phone"), "tom.jpg");
//        ArrayList<String> attributes = new ArrayList<>();
//        attributes.add("Strong");
//        attributes.add("created by steve jobs");
//        attributes.add("designed by california");
//        category.setAttributes(attributes);
//        Seller seller = new Seller("d_amiri", "dariush", "amiri", "dariush_a@co.co", "66255252", "1234", "benz", "sattarkhan", "3478546355");
//        Seller seller1 = new Seller("a_amiri", "dariush2", "amiri2", "dariush_a22@co.co", "6625525222", "123456", "bmw", "sattarkhan2", "34785463552222");
//        new Category("samsung", Category.getCategoryByName("phone"), "tom.jpg");
//        new Category("lg", Category.getCategoryByName("phone"), "tom.jpg");
//        new Category("siemens", Category.getCategoryByName("vacuum cleaner"), "tom.jpg");
//        new Category("bosch", Category.getCategoryByName("vacuum cleaner"), "tom.jpg");
//        new Category("dell", Category.getCategoryByName("laptop"), "tom.jpg");
//        new Category("asus", Category.getCategoryByName("laptop"), "tom.jpg");
//        new Category("sony", Category.getCategoryByName("laptop"), "tom.jpg");
//        new Product(Category.getCategoryByName("apple"), "iphone x", "apple", 10, "beautiful", "tom.jpg");
//        Product product = new Product(Category.getCategoryByName("apple"), "iphone 12", "apple", 12, "beautiful", "tom.jpg");
//        ArrayList<Seller> allSeller = new ArrayList<>();
//        allSeller.add(seller);
//        allSeller.add(seller1);
//        product.setAllSellers(allSeller);
//        Purchaser account = new Purchaser("da-ami", "dariush", "amiri", "dar_am@u.co", "394843984", "1234", "sattt ave");
//
//        new Comment(account, product, "big", "wowww");
//        new Comment(account, product, "bigdsf", "wowww");
//        new Comment(account, product, "bigdsfsdf", "wowww");
//        new Comment(account, product, "bigdsfsdf", "wowww");
//        new Comment(account, product, "bidfsfsdfg", "wowww");
//        new Comment(account, product, "bifgghrythtyg", "wowww");
//        new Comment(account, product, "bigryhtj543345", "wowww");
//        new Comment(account, product, "big4564536435635463456", "wowww");
//        new Comment(account, product, "bi45365463g", "wowww");
//        new Product(Category.getCategoryByName("apple"), "iphone 7plus", "apple", 7, "excellent", "tom.jpg");
//        new Product(Category.getCategoryByName("samsung"), "galaxy s10", "samsung", 1500, "high", "tom.jpg");
//        new Product(Category.getCategoryByName("samsung"), "galaxy s9", "samsung", 9, "medium", "tom.jpg");
//        new Product(Category.getCategoryByName("lg"), "G2", "lg", 9, "medium", "tom.jpg");
//        new Product(Category.getCategoryByName("lg"), "G3", "lg", 9, "medium", "tom.jpg");
//        new Product(Category.getCategoryByName("siemens"), "s1", "siemens", 10, "very good", "tom.jpg");
//        new Product(Category.getCategoryByName("Bosch"), "b2", "bosch", 10, "very good", "tom.jpg");
//        new Product(Category.getCategoryByName("asus"), "zenBook 13", "asus", 10, "very good", "tom.jpg");
//        new Product(Category.getCategoryByName("asus"), "zenBook 15", "asus", 10, "very good", "tom.jpg");
//        new Product(Category.getCategoryByName("dell"), "d12", "dell", 10, "very good", "tom.jpg");
//        new Product(Category.getCategoryByName("dell"), "d15", "dell", 10, "very good", "tom.jpg");
//        new Product(Category.getCategoryByName("sony"), "sonyPro3", "sony", 10, "very good", "tom.jpg");
//        new Product(Category.getCategoryByName("sony"), "sonyPro5", "sony", 10, "very good", "tom.jpg");
//        new Manager("q", "w", "e", "r", "t", "y");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(1275);
        stage.setHeight(720);
        MainMenu.display(stage);
    }

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

    private static void reload() {
        Category.setAllCategories(new Json<Category>().getAllJson("src/main/resources/Categories", "category"));
        Product.setAllProducts(new Json<Product>().getAllJson("src/main/resources/Products", "product"));
        Manager.setAllManagers(new Json<Manager>().getAllJson("src/main/resources/Accounts/Managers", "manager"));
        Seller.setAllSeller(new Json<Seller>().getAllJson("src/main/resources/Accounts/Sellers", "seller"));
        Purchaser.setAllPurchaser(new Json<Purchaser>().getAllJson("src/main/resources/Accounts/Purchasers", "purchaser"));
        Comment.setAllComments(new Json<Comment>().getAllJson("src/main/resources/Comments", "comment"));
        Account.getAllAccounts().addAll(Manager.getAllManagers());
        Account.getAllAccounts().addAll(Seller.getAllSeller());
        Account.getAllAccounts().addAll(Purchaser.getAllPurchaser());
        setCategoryParent(Category.getAllCategories());
        setProductCategory();

    }
}