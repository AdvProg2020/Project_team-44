package controller;

import exception.ProductAlreadyExistsInCartException;
import exception.SellerUserNameNotExists;
import model.account.Account;
import model.account.Purchaser;
import model.account.Seller;
import model.comment.Comment;

import java.util.ArrayList;

public abstract class ProductPageController {
    public static ArrayList<String> processShowDigest() {
        return ProductsPageController.selectedProduct.getProductInfo();
    }

    public static void processAddProductToCartEach() throws ProductAlreadyExistsInCartException {
        ValidationController.checkProductExistsInCart(LoginPageController.loggedInAccount, ProductsPageController.selectedProduct);
        ((Purchaser) LoginPageController.loggedInAccount).getCart().put(ProductsPageController.selectedProduct, 1);
    }

    public static void processSelectSellerEach(String sellerUsername) throws SellerUserNameNotExists {
        ValidationController.checkSellerOwnsProduct((Seller) Account.getAccountByUsername(sellerUsername)
                , ProductsPageController.selectedProduct);
        /*TODO*/
    }

    public static void processShowAttributes() {

    }

    public static ArrayList<String> processCompare(String productID) {
        return ((Purchaser) LoginPageController.loggedInAccount).compareTwoProducts(ProductsPageController.selectedProduct.getProductID(), productID);
    }

    public static void processAddComment(String title, String content) {
        Comment comment = new Comment(LoginPageController.loggedInAccount, ProductsPageController.selectedProduct, content, title);
    }
}
