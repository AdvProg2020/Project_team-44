package server.controller;

import server.exception.ProductAlreadyExistsInCartException;
import server.exception.ProductIdNotExistsException;
import server.exception.SellerUserNameNotExists;
import server.model.account.Purchaser;
import server.model.account.Seller;
import server.model.comment.Comment;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ProductPageController {
    public static Seller selectedSeller;

    public static ArrayList<String> processShowDigest() {
        return ProductsPageController.selectedProduct.getProductInfo();
    }

    public static void processAddProductToCartEach() throws ProductAlreadyExistsInCartException {
        ValidationController.checkProductExistsInCart(LoginPageController.getLoggedInAccount(), ProductsPageController.selectedProduct);
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
