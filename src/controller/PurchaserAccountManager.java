package controller;

import java.util.ArrayList;

public abstract class PurchaserAccountManager {
    public static ArrayList<String> processViewPersonalInfo() {
        return LoginPageController.getLoggedInAccount().getInfo();
    }

    public static void processEditFieldEach(String field, String newValue) {
        LoginPageController.getLoggedInAccount().editInfo(field, newValue);
    }

    public static void processViewCart() {

    }

    public static void processShowProductsEach() {

    }

    public static void processViewProductsEach(String productId) {

    }

    public static void processIncreaseProductEach(String productId) {

    }

    public static void processDecreaseProductEach(String productId) {

    }

    public static void processShowTotalPriceEach() {

    }

    public static void processPurchaseProductEach() {

    }

    public static void processPurchase() {

    }

    public static void processViewOrders() {

    }

    public static void processShowOrderEach(String orderId) {

    }

    public static void processRateEach(String productId, int rating) {

    }

    public static void processViewBalance() {

    }

    public static void processViewDiscountCodes() {

    }

}
