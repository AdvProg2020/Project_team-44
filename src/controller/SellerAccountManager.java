package controller;

import java.util.ArrayList;

public abstract class SellerAccountManager {
    public static ArrayList<String> processViewPersonalInfo() {
        return LoginPageController.getLoggedInAccount().getInfo();
    }

    public static void processEditFieldEach(String field, String newValue) {
        LoginPageController.getLoggedInAccount().editInfo(field, newValue);
    }

    public static void processViewCompanyInfo() {

    }

    public static void processViewSalesHistory() {

    }

    public static void processManageProducts() {

    }

    public static void processViewProductEach(String productId) {

    }

    public static void processViewBuyersEach(String productId) {

    }

    public static void processEditProduct(String productId) {

    }

    public static void processAddProduct() {

    }

    public static void processRemoveProduct() {

    }

    public static void processShowCategory() {

    }

    public static void processViewOffs() {

    }

    public static void processViewOffEach(String offId) {

    }

    public static void processEditOffEach(String offId) {

    }

    public static void processAddOffEach() {

    }

    public static void processViewBalance() {

    }
}
