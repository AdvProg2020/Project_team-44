package controller;

import exception.SellerUserNameNotExists;
import model.account.Purchaser;
import model.comment.Comment;

import java.util.ArrayList;

public abstract class ProductPageController {
    public static ArrayList<String> processShowDigest() {
        ProductsPageController.selectedProduct.getProductInfo();
    }

    public static void processAddProductToCartEach() {
        LoginPageController.loggedInAccount.getCart().add(ProductsPageController.selectedProduct);
    }

    public static void processSelectSellerEach(String sellerUsername) throws SellerUserNameNotExists {

    }

    public static void processShowAttributes() {
        return;
    }

    public static ArrayList<String> processCompare(String productID) {
       return  ((Purchaser) LoginPageController.loggedInAccount).compareTwoProducts(ProductsPageController.selectedProduct.getProductID(), productID);
    }

    public static void processAddComment(String title, String content) {
        Comment comment = new Comment(LoginPageController.loggedInAccount, ProductsPageController.selectedProduct, content, title);
    }
}
