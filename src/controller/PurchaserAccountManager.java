package controller;

import exception.OrderNotExistsException;
import exception.ProductIdNotExistsException;
import model.CodedDiscount;
import model.account.Purchaser;
import model.buyLog.BuyLog;
import model.product.Product;

import java.util.ArrayList;

public abstract class PurchaserAccountManager {
    public static ArrayList<String> processViewPersonalInfo() {
        return LoginPageController.loggedInAccount.getInfo();
    }

    public static void processEditFieldEach(String field, String newValue) {
        LoginPageController.loggedInAccount.editInfo(field, newValue);
    }

    public static void processViewCart() {

    }

    public static void processShowProductsEach() {

    }

    public static void processViewProductsEach(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        /*TODO*/
    }

    public static void processIncreaseProductEach(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        /*TODO*/
    }

    public static void processDecreaseProductEach(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        /*TODO*/
    }

    public static void processShowTotalPriceEach() {

    }

    public static void processPurchaseProductEach() {

    }

    public static void processPurchase() {

    }

    public static void processViewOrders() {

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
