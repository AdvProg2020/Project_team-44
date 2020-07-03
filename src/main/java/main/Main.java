package main;

import controller.LoginPageController;
import graphicView.userRegion.userAccount.managerRequestions.editOff.EditOffRequest;
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
import model.offer.Offer;
import model.product.Product;
import model.requests.RequestForEditOff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

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
//        reload();
//        setMediaPlayer("The Swimmer.mp3");
        Seller seller = new Seller("a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a",
                "a");
        ArrayList<Product> allProducts = new ArrayList<>();
        Category category = new Category("fruit", null, null);
        Product p1 = new Product(category,
                "apple",
                "digikala",
                2000,
                "",
                "");
        Product p2 = new Product(category,
                "orange",
                "amazon",
                18181,
                "",
                "");
        Product p3 = new Product(category,
                "pineapple",
                "gajmarket",
                222222,
                "",
                "");

        allProducts.add(p1);
        allProducts.add(p2);
        allProducts.add(p3);
        Offer offer = new Offer(allProducts,
                new Date(),
                new Date(),
                20);

        allProducts.remove(p1);
        for (int i = 0; i < 1; i++) {
//            new RequestForRemoveProduct(seller, product);
            new RequestForEditOff(seller,
                    offer,
                    allProducts,
                    new Date(),
                    new Date(),
                    40);
        }
        Manager manager = new Manager("a",
                "a",
                "a",
                "a",
                "a",
                "a");
        LoginPageController.setLoggedInAccount(manager);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        stage.setWidth(1275);
//        stage.setHeight(720);
//        MainMenu.display(stage);
        EditOffRequest.display();
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
        for (Category allCategory : Category.getAllCategories()) {
            for (Category subCategory : allCategory.getSubCategories()) {
                for (Product allSubProduct : subCategory.getAllSubProducts()) {
                    for (Product allProduct : Product.getAllProducts()) {
                        if (allSubProduct.getName().equals(allProduct.getName())) {
                            allProduct.setCategory(subCategory);
                            allSubProduct.setCategory(subCategory);
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void doComment(Comment comment) {
        for (Product allProduct : Product.getAllProducts()) {
            for (Comment allComment : allProduct.getAllComments()) {
                if (allComment.getCommentId().equals(comment.getCommentId())) {
                    comment.setProduct(allProduct);
                    break;
                }
            }
        }
    }

    public static void setCommentEachProduct(ArrayList<Comment> all) {
        for (Comment comment : all) {
            doComment(comment);
        }
    }

    private static void reload() {
        Category.setAllCategories(new Json<Category>().getAllJson("src/main/resources/Categories", "category"));
        Product.setAllProducts(new Json<Product>().getAllJson("src/main/resources/Products", "product"));
        Manager.setAllManagers(new Json<Manager>().getAllJson("src/main/resources/Accounts/Managers", "manager"));
        Seller.setAllSeller(new Json<Seller>().getAllJson("src/main/resources/Accounts/Sellers", "seller"));
        Purchaser.setAllPurchaser(new Json<Purchaser>().getAllJson("src/main/resources/Accounts/Purchasers", "purchaser"));
        Comment.setAllComments(new Json<Comment>().getAllJson("src/main/resources/Comments", "comment"));
        setCommentEachProduct(Comment.allComments);
        Account.getAllAccounts().addAll(Manager.getAllManagers());
        Account.getAllAccounts().addAll(Seller.getAllSeller());
        Account.getAllAccounts().addAll(Purchaser.getAllPurchaser());
        setCategoryParent(Category.getAllCategories());
        setProductCategory();
    }
}