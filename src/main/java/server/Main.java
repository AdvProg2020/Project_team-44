package server;

import client.graphicView.cart.CartPage;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.application.Application;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import server.controller.LoginPageController;
import server.graphicViewServer.cart.CartPageServer;
import server.graphicViewServer.userRegion.loginPanel.LoginPanelServer;
import server.graphicViewServer.userRegion.loginPanel.ManagerInfoSetPanelServer;
import server.graphicViewServer.userRegion.loginPanel.PurchaserInfoSetPanelServer;
import server.graphicViewServer.userRegion.loginPanel.SellerInfoSetPanelServer;
import server.main.Json;
import server.model.Category;
import server.model.CodedDiscount;
import server.model.ShopBankAccount;
import server.model.account.Account;
import server.model.account.Manager;
import server.model.account.Purchaser;
import server.model.account.Seller;
import server.model.comment.Comment;
import server.model.product.Auction;
import server.model.product.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Main extends Application {
    public static Stage window;
    public static Product good;
    public static Stage accountRegionStage;
    static MediaPlayer mediaPlayer;

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(String songName) {
//        server.Main.mediaPlayer = new MediaPlayer(new Media(new File("src/server.main/resources/media/sound/" + songName).toURI().toString()));
//        server.Main.mediaPlayer.setAutoPlay(true);
//        server.Main.mediaPlayer.setOnEndOfMedia(new Runnable() {
//            public void run() {
//                mediaPlayer.seek(Duration.ZERO);
//            }
//        });
    }

    public static void setAccountRegionStage(Stage accountRegionStage) {
        Main.accountRegionStage = accountRegionStage;
    }

    public static void main(String[] args) throws IOException {
        reload();
        Category category = new Category("hi", null, "");
        Purchaser purchaser = new Purchaser("a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g");
        Product p1 = new Product(category,
                "apple",
                "digikala",
                2000,
                "",
                "");
        Product p2 = new Product(category,
                "orange",
                "amazon",
                3000,
                "",
                "");
        Product p3 = new Product(category,
                "pineapple",
                "gajmarket",
                4000,
                "",
                "");
        HashMap<Product, Integer> cart = new HashMap<>();
        cart.put(p1, 1);
        cart.put(p2, 11);
        cart.put(p3, 22);
        purchaser.setCart(cart);
        ArrayList<CodedDiscount> allCodedDiscount = new ArrayList<>();
        allCodedDiscount.add(new CodedDiscount(new Date(), new Date(), 20, 100000));
        purchaser.setAllDiscountCodes(allCodedDiscount);
        LoginPageController.setLoggedInAccount(purchaser);
        LoginPanelController.setLoggedInAccount(purchaser);
        purchaser.setBalance(1000000);
        new LoginPanelServer();
        new ManagerInfoSetPanelServer();
        new PurchaserInfoSetPanelServer();
        new SellerInfoSetPanelServer();
        new CartPageServer();
        launch(args);

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

    public static void setCategoryAuction() {
        for (Product allAuction : Auction.getAllAuctions()) {
            for (Product allProduct : Product.getAllProducts()) {
                if (allAuction.getProductID().equals(allProduct.getProductID())) {
                    allAuction.setCategory(allProduct.getCategory());
                    break;
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
        for (Purchaser purchaser : Purchaser.getAllPurchaser()) {
            purchaser.newCartAndSelectedSellerForProducts(purchaser);
        }
//        Comment.setAllComments(new Json<Comment>().getAllJson("src/main/resources/Comments", "comment"));
        setCommentEachProduct(Comment.allComments);
        Account.getAllAccounts().addAll(Manager.getAllManagers());
        Account.getAllAccounts().addAll(Seller.getAllSeller());
        Account.getAllAccounts().addAll(Purchaser.getAllPurchaser());
        setCategoryParent(Category.getAllCategories());
        setProductCategory();
        ShopBankAccount.setAllShopBankAccount(new Json<ShopBankAccount>().getAllJson("src/main/resources/Bank Account", "bankAccount"));
//        Auction.setAllAuctions(new Json<Auction>().getAllJson("src/main/resources/Auction", "auction"));
        setCategoryAuction();
    }

    @Override
    public void start(Stage stage) throws Exception {
//        stage.setWidth(1275);
//        stage.setHeight(720);
//        MainMenu.display(stage);
        CartPage.display();
//        LoginPanel.display();
    }
}