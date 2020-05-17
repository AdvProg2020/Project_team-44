package controller;

import exception.OrderNotExistsException;
import exception.ProductNotExistsInCartException;
import exception.ProductIdNotExistsException;
import model.CodedDiscount;
import model.account.Purchaser;
import model.buyLog.BuyLog;
import model.product.Product;

import java.util.ArrayList;

public abstract class PurchaserAccountController {
    public static ArrayList<String> processViewPersonalInfo() {
        return LoginPageController.loggedInAccount.getInfo();
    }

    public static void processEditFieldEach(String field, String newValue) {
        LoginPageController.loggedInAccount.editInfo(field, newValue);
    }

    public static ArrayList<String> processShowProductsEach() {
        return ((Purchaser) LoginPageController.loggedInAccount).getCartProducts();
    }


    public static void processViewProductsEach(String productId) throws ProductNotExistsInCartException {
        ValidationController.checkProductNotExistsInCart(LoginPageController.loggedInAccount, Product.getProductByID(productId));
        ProductsPageController.selectedProduct = Product.getProductByID(productId);
    }

    public static void processIncreaseProductEach(String productId) throws ProductNotExistsInCartException {
        ValidationController.checkProductNotExistsInCart(LoginPageController.loggedInAccount, Product.getProductByID(productId));
        ((Purchaser) LoginPageController.loggedInAccount).getCart()
                .replace(Product.getProductByID(productId)
                        , ((Purchaser) LoginPageController.loggedInAccount).getCart().get(Product.getProductByID(productId)) + 1);
    }

    public static void processDecreaseProductEach(String productId) throws ProductNotExistsInCartException {
        ValidationController.checkProductNotExistsInCart(LoginPageController.loggedInAccount, Product.getProductByID(productId));
        if (((Purchaser) LoginPageController.loggedInAccount).getCart().get(Product.getProductByID(productId)) == 1) {
            ((Purchaser) LoginPageController.loggedInAccount).getCart().remove(Product.getProductByID(productId));
        }
        ((Purchaser) LoginPageController.loggedInAccount).getCart()
                .replace(Product.getProductByID(productId)
                        , ((Purchaser) LoginPageController.loggedInAccount).getCart().get(Product.getProductByID(productId)) + 1);

    }

    public static double processShowTotalPriceEach() {
        return ((Purchaser) LoginPageController.loggedInAccount).getCartMoneyToPay();
    }

    public static void processPurchaseProductEach() {
        /*TODO*/
    }


    public static ArrayList<String> processViewOrders() {
        return ((Purchaser) LoginPageController.loggedInAccount).getAllBuyLogIds();
    }

    public static ArrayList<String> processShowOrderEach(String orderId) throws OrderNotExistsException {
        ValidationController.checkOrderExistence(orderId);
        return BuyLog.getBuyLogById(orderId).getInfo();
    }

    public static void processRateEach(String productId, int rating) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        ((Purchaser) LoginPageController.loggedInAccount).rateProduct(rating, Product.getProductByID(productId));
    }

    public static double processViewBalance() {
        return LoginPageController.loggedInAccount.getBalance();
    }

    public static ArrayList<String> processViewDiscountCodes() {
        ArrayList<String> allDiscounts = new ArrayList<>();
        for (CodedDiscount codedDiscount : LoginPageController.loggedInAccount.getAllDiscountCodes()) {
            allDiscounts.add(codedDiscount.getDiscountCode());
        }
        return allDiscounts;
    }


}
