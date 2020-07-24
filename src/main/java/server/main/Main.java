package server.main;

import server.controller.LoginPageController;
import client.graphicView.mainMenu.MainMenu;
import javafx.application.Application;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import server.model.Category;
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

public class Main extends Application {
//    static ArrayList<Categ> all = new ArrayList<>();
    public static Product good;
    public static Stage accountRegionStage;
    static MediaPlayer mediaPlayer;

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(String songName) {
//        server.main.Main.mediaPlayer = new MediaPlayer(new Media(new File("src/server.main/resources/media/sound/" + songName).toURI().toString()));
//        server.main.Main.mediaPlayer.setAutoPlay(true);
//        server.main.Main.mediaPlayer.setOnEndOfMedia(new Runnable() {
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
//        setMediaPlayer("The Swimmer.mp3");


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
//        attributes.add("American Good");
//        attributes.add("fast and powerful");
//        category.setAttributes(attributes);
//        Seller seller = new Seller("dariush_amiri", "Dariush", "amiri", "dariush_amiri@co.co", "66255252", "1234", "apple", "sattarkhan", "3478546355");
//        Seller seller1 = new Seller("finn_colin", "finn", "colin", "finn_colin@co.co", "6625525222", "123456", "apple", "sattarkhan2", "34785463552222");
//        Seller seller2 = new Seller("Berlin", "professor", "professorzadeh", "berlin_B@co.co", "09129990000", "12345", "apple", "california", "001234567");
//        new Category("samsung", Category.getCategoryByName("phone"), "tom.jpg");
//        new Category("lg", Category.getCategoryByName("phone"), "tom.jpg");
//        new Category("siemens", Category.getCategoryByName("vacuum cleaner"), "tom.jpg");
//        new Category("bosch", Category.getCategoryByName("vacuum cleaner"), "tom.jpg");
//        new Category("dell", Category.getCategoryByName("laptop"), "tom.jpg");
//        new Category("asus", Category.getCategoryByName("laptop"), "tom.jpg");
//        new Category("sony", Category.getCategoryByName("laptop"), "tom.jpg");
////
//        new Product(Category.getCategoryByName("apple"), "iphone x", "apple", 10, "beautiful", "tom.jpg");
//        Product product = new Product(Category.getCategoryByName("apple"), "iphone 12", "apple", 12, "beautiful", "tom.jpg");
//        ArrayList<Seller> allSeller = new ArrayList<>();
//        allSeller.add(seller);
//        allSeller.add(seller1);
//        allSeller.add(seller2);
//        product.setAllSellers(allSeller);
//        Purchaser account = new Purchaser("darius-amr", "dariush", "amiri", "dar_am@u.co", "394843984", "1234", "sattarkhan ave");
//        Purchaser account1 = new Purchaser("darius-amr", "dariush", "amiri", "dar_am@u.co", "394843984", "1234", "sattarkhan ave");
//        Purchaser account2 = new Purchaser("darius-amr", "dariush", "amiri", "dar_am@u.co", "394843984", "1234", "sattarkhan ave");
//
//        new Comment(account, product, "low", "quality");
//        new Comment(account1, product, "medium", "quality");
//        new Comment(account2, product, "high", "quality");
//        new Comment(account2, product, "ful", "power");
//        new Comment(account1, product, "mid", "power");
//        new Comment(account, product, "weak", "power");
//        new Comment(account1, product, "good", "support");
//        new Comment(account, product, "excellent", "support");
//        new Comment(account2, product, "low", "support");
//
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
//        new Manager("steve_jobs", "steve", "jobs", "steve@co.co", "001982625373", "1111");
//        server.main.BankAPI bankAPI = new server.main.BankAPI();
//        for (ShopBankAccount bankAccount : ShopBankAccount.allShopBankAccount) {
//            bankAPI.SendMessage("get_token " + bankAccount.getUserName() + " " + bankAccount.getPassword());
//            bankAPI.SendMessage("create_receipt " + bankAPI.getInputStream() + " deposit " + 1000 + " " + (-1) + " " + bankAccount.getAccountId() + " " + "deposit");
//            bankAPI.SendMessage("pay " + Integer.parseInt(bankAPI.getInputStream()));
//            bankAPI.getInputStream();
//        }

        Product product = new Product(Category.getCategoryByName("apple"), "iphone15", "apple", 15, "tak tir", "tom.jpg");

//        for (Category subCategory : Category.getCategoryByName("digital").getSubCategories()) {
//            for (Category category : subCategory.getSubCategories()) {
//                for (Product allSubProduct : category.getAllSubProducts()) {
//                    System.out.println("line 135 server.main.Main   " + "digital" + "   " + subCategory.getName() + "   " + category.getName() + "   " + allSubProduct.getName());
//
//                }
//            }
//        }
        Date fin = new Date(120, 6, 23, 15, 40, 45);
        new Auction(product.getCategory(), product.getName(), product.getCompanyName(), product.getPrice(), product.getExplanationText(), product.getImageName(), fin);
        LoginPageController.setLoggedInAccount(Account.getAccountByUsername("purchaserpurchaser"));
        Manager.getAllManagers().get(0).setMinAmount(5);
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
        Comment.setAllComments(new Json<Comment>().getAllJson("src/main/resources/Comments", "comment"));
        setCommentEachProduct(Comment.allComments);
        Account.getAllAccounts().addAll(Manager.getAllManagers());
        Account.getAllAccounts().addAll(Seller.getAllSeller());
        Account.getAllAccounts().addAll(Purchaser.getAllPurchaser());
        setCategoryParent(Category.getAllCategories());
        setProductCategory();
        ShopBankAccount.setAllShopBankAccount(new Json<ShopBankAccount>().getAllJson("src/main/resources/Bank Account", "bankAccount"));
        Auction.setAllAuctions(new Json<Auction>().getAllJson("src/main/resources/Auction", "auction"));
        setCategoryAuction();
    }
}