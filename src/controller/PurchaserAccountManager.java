package controller;

import exception.OrderNotExistsException;
import exception.ProductIdNotExistsException;
import model.CodedDiscount;
import model.account.Purchaser;
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
        checkProductExistence(productId);
        /*TODO*/
    }

    public static void processIncreaseProductEach(String productId) throws ProductIdNotExistsException {
        checkProductExistence(productId);
        /*TODO*/
    }

    public static void processDecreaseProductEach(String productId) throws ProductIdNotExistsException {
        checkProductExistence(productId);
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
        checkOrderExistence(orderId);
        return Order.getOrderById(orderId).showInfo();
    }

    public static void processRateEach(String productId, int rating) throws ProductIdNotExistsException {
        checkProductExistence(productId);
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

    public static void checkOrderExistence(String orderId) throws OrderNotExistsException {
        for (Order order : Order.getAllOrders()) {
            if (orderId.equals(order.getOrderId())) {
                return;
            }
        }
        throw new OrderNotExistsException("No order exists with this Id.");
    }

    public static void checkProductExistence(String productId) throws ProductIdNotExistsException {
        for (Product product : Product.getAllProducts()) {
            if (productId.equals(product.getProductID())) {
                return;
            }
        }
        throw new ProductIdNotExistsException("No product exists with this Id.");
    }

}
