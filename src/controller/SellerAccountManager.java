package controller;

import exception.ProductIdNotExistsException;
import model.offer.Offer;
import model.product.Product;

import java.util.ArrayList;

public abstract class SellerAccountManager {
    public static ArrayList<String> processViewPersonalInfo() {
        return LoginPageController.loggedInAccount.getInfo();
        /**DONE**/

    }

    public static void processEditFieldEach(String field, String newValue) {
        LoginPageController.loggedInAccount.editInfo(field, newValue);
        /**DONE**/

    }

    public static void processViewCompanyInfo() {

    }

    public static void processViewSalesHistory() {

    }

    public static void processManageProducts() {

    }

    public static void processViewProductEach(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
    }

    public static void processViewBuyersEach(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
    }

    public static void processEditProduct(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
    }

    public static void processAddProduct() {

    }

    public static void processRemoveProduct() {

    }

    public static void processShowCategory() {

    }

    public static void processViewOffs() {

    }

    public static void processViewOffEach(String offId) throws ProductIdNotExistsException {
        ValidationController.checkOfferExistence(offId);
    }

    public static void processEditOffEach(String offId) throws ProductIdNotExistsException {
        ValidationController.checkOfferExistence(offId);
    }

    public static void processAddOffEach() {

    }

    public static void processViewBalance() {

    }

}
