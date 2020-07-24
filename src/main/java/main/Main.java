package main;

import controller.LoginPageController;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.userAccount.managerAccount.CategoryPageController;
import graphicView.userRegion.userAccount.managerRequestions.addOff.AddOffRequest;
import graphicView.userRegion.userAccount.managerRequestions.addProduct.AddProductRequest;
import graphicView.userRegion.userAccount.managerRequestions.addSeller.AddSellerRequest;
import graphicView.userRegion.userAccount.managerRequestions.removeProduct.RemoveProductRequest;
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
import model.requests.RequestForAddProduct;
import model.requests.RequestForRemoveProduct;
import model.requests.RequestForSeller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
//        Main.mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/media/sound/" + songName).toURI().toString()));
//        Main.mediaPlayer.setAutoPlay(true);
//        Main.mediaPlayer.setOnEndOfMedia(new Runnable() {
//            public void run() {
//                mediaPlayer.seek(Duration.ZERO);
//            }
//        });
    }

    public static void setAccountRegionStage(Stage accountRegionStage) {
        Main.accountRegionStage = accountRegionStage;
    }

    public static void main(String[] args) throws FileNotFoundException {
        reload();
////        setMediaPlayer("The Swimmer.mp3");
//        Seller seller = new Seller("a",
//                "a",
//                "a",
//                "a",
//                "a",
//                "a",
//                "a",
//                "a",
//                "a");
//        Product product = new Product(new Category("fruit", null, null),
//                "apple",
//                "digikala",
//                1000,
//                "Extraordinary",
//                null);
//        for (int i = 0; i < 5; i++) {
//            new RequestForRemoveProduct(seller, product);
//        }
//        Manager manager = new Manager("a",
//                "a",
//                "a",
//                "a",
//                "a",
//                "a");
//        LoginPageController.setLoggedInAccount(manager);
        Seller seller1 = new Seller("amiramir", "Alex", "dd", "ya@yahoo.com",
                "2441", "amiramir", "izo", "street5", "6777");
         Category category1 = new Category("car", null, "ali");
         Category category2 = new Category("kala", null, "aff");
         ArrayList<String> attributes = new ArrayList<>();
         attributes.add("good");
         attributes.add("new");
         category2.setAttributes(attributes);
        Product product1 = new Product(category1, "peraid", "saipa", 12000,
                "good Product!", "12");
        Product product2 = new Product(category2, "bmw", "bmw", 10000, "good", "32");
        Product product3 = new Product(category2, "peris", "aipr", 80000, "nice", "ado");
        HashMap<Product,Integer> productIntegerHashMap = new HashMap<>();
        ArrayList<Product> subProducts = new ArrayList<>();
        subProducts.add(product1);
        subProducts.add(product2);
        category2.setAllSubProducts(subProducts);
        productIntegerHashMap.put(product1,1);
        productIntegerHashMap.put(product3,2);
        productIntegerHashMap.put(product2,3);
        Purchaser purchaser = new Purchaser("amirabbas","amirabbas","mohammadi","" +
                "amir@gmail.com","09306304297","amirabbas","aas");
        CodedDiscount codedDiscount = new CodedDiscount(new Date(190,10,25),new Date(190,11,20)
        ,50,5000);
        ArrayList<CodedDiscount> allCodedDiscount = new ArrayList<>();
        allCodedDiscount.add(codedDiscount);
        purchaser.setAllDiscountCodes(allCodedDiscount);
        seller1.setProductsToSell(productIntegerHashMap);
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("  ");
        stage.setWidth(1275);
        stage.setHeight(720);
        MainMenu.display(stage);
//        AddOffRequest.display();
//        AddProductRequest.display();
//        AddSellerRequest.display();
//        RemoveProductRequest.display();
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