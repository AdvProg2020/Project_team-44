package controller;

import exception.ProductAlreadyExistsInCartException;
import exception.ProductIdNotExistsException;
import exception.SellerUserNameNotExists;
import model.account.Account;
import model.account.Manager;
import model.account.Purchaser;
import model.account.Seller;
import model.comment.Comment;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ProductPageController {
    public static Seller selectedSeller;

    public static ArrayList<String> processShowDigest() {
        return ProductsPageController.selectedProduct.getProductInfo();
    }

    public static void processAddProductToCartEach() throws ProductAlreadyExistsInCartException {
        if (LoginPageController.loggedInAccount == null) {
            System.out.println("null");
        }
        if (LoginPageController.loggedInAccount instanceof Purchaser) {
            System.out.println("Purchaser");
        }
        if (LoginPageController.loggedInAccount instanceof Seller) {
            System.out.println("seller");
        }
        if (LoginPageController.loggedInAccount instanceof Manager) {
            System.out.println("manager");
        }else {
            System.out.println(LoginPageController.loggedInAccount);
        }
        ValidationController.checkProductExistsInCart(LoginPageController.loggedInAccount, ProductsPageController.selectedProduct);
        ((Purchaser) LoginPageController.loggedInAccount).getCart().put(ProductsPageController.selectedProduct, 1);
        ((Purchaser) LoginPageController.loggedInAccount).getSellerSelectedForEachProduct()
                .put(ProductsPageController.selectedProduct, selectedSeller);
    }

    public static void processSelectSellerEach(String sellerUsername) throws SellerUserNameNotExists {
        ValidationController.checkSellerOwnsProduct(Seller.getSellerByUsername(sellerUsername)
                , ProductsPageController.selectedProduct);
        selectedSeller = Seller.getSellerByUsername(sellerUsername);
    }

    public static HashMap<String, String> processShowAttributes() {
        return ProductsPageController.selectedProduct.getCategoryAttributes();
    }

    public static ArrayList<String> processCompare(String productID) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productID);
        return ((Purchaser) LoginPageController.loggedInAccount).compareTwoProducts(ProductsPageController.selectedProduct.getProductID(), productID);
    }

    public static void processAddComment(String title, String content) {
        new Comment(LoginPageController.loggedInAccount, ProductsPageController.selectedProduct, content, title);
    }
}
