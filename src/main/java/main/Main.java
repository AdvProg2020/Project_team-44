package main;

import controller.LoginPageController;
import graphicView.discountCodes.DiscountCodesPage;
import graphicView.mainMenu.MainMenu;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Category;
import model.CodedDiscount;
import model.account.Account;
import model.account.Manager;
import model.account.Purchaser;
import model.account.Seller;
import model.comment.Comment;
import model.product.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {
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
//        Category category = new Category("fruit", null, null);
//        Purchaser purchaser = new Purchaser("a",
//                "b",
//                "c",
//                "d",
//                "e",
//                "f",
//                "g");
//        ArrayList<CodedDiscount> allCodedDiscounts = new ArrayList<>();
//        allCodedDiscounts.add(new CodedDiscount(new Date(),
//                new Date(),
//                15,
//                100000));
//        allCodedDiscounts.add(new CodedDiscount(new Date(),
//                new Date(),
//                70,
//                118181));
//        allCodedDiscounts.add(new CodedDiscount(new Date(),
//                new Date(),
//                40,
//                1000000000));
//        purchaser.setAllDiscountCodes(allCodedDiscounts);
//        LoginPageController.setLoggedInAccount(purchaser);
//        SellLogPage.display();
//BuyLogPage.display();
//    CartPage.display();
//    DiscountCodesPage.display();

//BuyLogPage.display();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        System.out.println("  ");
        stage.setWidth(1275);
        stage.setHeight(720);
        MainMenu.display(stage);
//        AddOffRequest.display();
//        AddProductRequest.display();
//        AddSellerRequest.display();
//        RemoveProductRequest.display();
//        BuyLogPage.display();
//        SellLogPage.display();
//        CartPage.display();
//        DiscountCodesPage.display();

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