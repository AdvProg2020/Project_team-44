package main;

import controller.LoginPageController;
import exception.UsernameNotExistsException;
import exception.WrongPasswordException;
import graphicView.userRegion.loginPanel.LoginPanelController;
import graphicView.userRegion.userAccount.ManagerAccountPage;
import graphicView.userRegion.userAccount.SellerAccountPage;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Category;
import model.account.Manager;
import model.account.Purchaser;
import model.account.Seller;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {

    static ArrayList<Category> all = new ArrayList<>();
    public static Stage window;
    private static MediaPlayer mediaPlayer;

    public static void setMediaPlayer(String songName) {
        Main.mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/media/sound/" + songName).toURI().toString()));
        Main.mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
    }

    public static void main(String[] args) {
//        reload();
//        new Manager("a", "b", "c", "d", "e", "f");
//        new Category("phone", null, "tom.jpg");
//        new Category("home", null, "tom.jpg");
//        new Category("apple", Category.getCategoryByName("phone"), "tom.jpg");
//        new Category("samsung", Category.getCategoryByName("phone"), "tom.jpg");
//        new Product(Category.getCategoryByName("samsung"), "galaxy s10", "samsung", 10, "very good", "tom.jpg");
//        new Product(Category.getCategoryByName("home"), "carpet", "iran", 1500, "high", "tom.jpg");
//        new Product(Category.getCategoryByName("apple"), "iphone 11", "apple", 11, "excellent", "tom.jpg");
//        new Product(Category.getCategoryByName("apple"), "iphone 7plus", "apple", 7, "excellent", "tom.jpg");
//        new Product(Category.getCategoryByName("apple"), "iphone x", "apple", 10, "beautiful", "tom.jpg");
//        new Product(Category.getCategoryByName("apple"), "iphone 12", "apple", 12, "beautiful", "tom.jpg");
//        new Product(Category.getCategoryByName("samsung"), "galaxy s9", "samsung", 9, "medium", "tom.jpg");
//        new Manager("q", "w", "e", "r", "t", "y");
//        new Purchaser("a", "", "", "", "", "", "");
        Purchaser purchaser = new Purchaser("a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g");
        Manager manager = new Manager("a",
                "b",
                "c",
                "d",
                "e",
                "f");
        Seller seller = new Seller("a", "b", "c", "d", "d", "jh", "a", "s", "w");
        LoginPanelController.setLoggedInAccount(manager);
        LoginPageController.setLoggedInAccount(manager);
        if (LoginPanelController.getLoggedInAccount() instanceof Manager) {
            System.out.println("Yeeeee");
        }
//        ArrayList<Product> list2 = new ArrayList<>();
//        Product p1 = new Product(null, "boob", null, 10, null, null);
//        Product p2 = new Product(null, "kos", null, 33, null, null);
////        list2.add(p1);
//        list2.add(p2);
//
////
////        list.add((new SellLog(new Date(), 2, 3, list2, "mmd", "amin")));
////        list.add((new SellLog(new Date(), 5, 91, list2, "ghli", "bb")));
////        list.add((new SellLog(new Date(), 32, 329, list2, "ss", "ks")));
////        list.add((new SellLog(new Date(), 211, 311, list2, "sak", "mme")));
////        new SellLog(new Date(), 2181811, 311, list2, "aaaa", "mme");
////        LoginPanelController.getLoggedInAccount().setSellLogListHistory(list);
////        for (String sellLogId : ((Seller) LoginPanelController.getLoggedInAccount()).getAllSellLogIds()) {
//////            sellLogs.add(new SellLogPageController.SellLogIds(sellLogId));
////            System.out.println(sellLogId + ":" + SellLog.getSellLogById(sellLogId).getLogID());
//////            System.out.println(SellLog.getAllSellLogs().size());
////        }
////        for (SellLog sellLog : SellLog.getAllSellLogs()) {
//////            sellLogs.add(new SellLogPageController.SellLogIds(sellLogId));
////            System.out.println(sellLog.getLogID());
////        }
//        BuyLog b = new BuyLog(null, 0, 0, null, null);
//        ArrayList<BuyLog> list = new ArrayList<>();
//        HashMap<Product, Integer> hashMap = new HashMap<>();
//        hashMap.put(p1, 1);
//        hashMap.put(p2, 1);
//        LoginPanelController.getLoggedInAccount().setBuyLogListHistory(list);
//        for (String buyLogId : ((Purchaser) LoginPanelController.getLoggedInAccount()).getAllBuyLogIds()) {
////            sellLogs.add(new SellLogPageController.SellLogIds(sellLogId));
//            System.out.println(buyLogId + ":" + BuyLog.getBuyLogById(buyLogId).getLogID());
////            System.out.println(SellLog.getAllSellLogs().size());
//        }
//        for (BuyLog log : BuyLog.getAllBuyLogs()) {
//////            sellLogs.add(new SellLogPageController.SellLogIds(sellLogId));
//            System.out.println(log.getLogID());
//////        }
////        }
//
//            LoginPanelController.getLoggedInAccount().setBalance(100);
//////        ((Purchaser) LoginPanelController.getLoggedInAccount()).setCart(hashMap);
////        System.out.println(((Purchaser) LoginPanelController.getLoggedInAccount()).getCartMoneyToPay());
//            try {
////            LoginPageController.processCreateAccount("purchaser",
////                    purchaser.getUserName(),
////                    purchaser.getPassword(),
////                    purchaser.getFirstName(),
////                    purchaser.getLastName(),
////                    purchaser.getEMail(),
////                    purchaser.getTelephoneNumber(),
////                    null,
////                    purchaser.getAddress(),
////                    null
////            );
//                LoginPageController.processLogin("a", "f");
//            } catch (UsernameNotExistsException | WrongPasswordException e) {
//                e.printStackTrace();
//            }
        setMediaPlayer("The Swimmer.mp3");

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        InfoSetPanel.display();
//        LoginPanel.display();
//        SellLogPage.display();
//        BuyLogPage.display();
//        CartPage.display();
//        DiscountCodesPage.display();
//        PurchasePage.display();
//        MainMenu.display();
        ManagerAccountPage.display();
//        PurchaserAccountPage.display();
//        SellerAccountPage.display();
    }


    //
//        public static void updateJson(String path, ) {
//
//    }
//    private static void setCategoryParent(ArrayList<Category> all) {
//        ArrayList<Category> allSub;
//        if (all.size() > 0) {
//            for (Category allCategory : all) {
//                if (allCategory.getSubCategories().size() > 0) {
//                    allSub = allCategory.getSubCategories();
//                    for (Category category1 : allSub) {
//                        Category.getCategoryByName(category1.getName()).setParent(allCategory);
//                    }
//                    setCategoryParent(allSub);
//                }
//            }
//        }
//    }
//
//    private static void setProductCategory() {
//        ArrayList<Product> allSub;
//        for (Category allCategory : Category.getAllCategories()) {
//            if (allCategory.getAllSubProducts().size() > 0) {
//                allSub = allCategory.getAllSubProducts();
//                for (Product product : allSub) {
//                    Product.getProductByID(product.getProductID()).setCategory(allCategory);
//                }
//            }
//        }
//    }

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