package server.main;

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
import server.viewServer.mainMenu.MainMenuServer;
import server.viewServer.userRegion.loginPanel.LoginPanelServer;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static Product good;
    public static Stage accountRegionStage;
    static MediaPlayer mediaPlayer;

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }


    public static void setAccountRegionStage(Stage accountRegionStage) {
        Main.accountRegionStage = accountRegionStage;
    }

    public static void main(String[] args) throws IOException {
        reload();
//        new MainMenuServer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new LoginPanelServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
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