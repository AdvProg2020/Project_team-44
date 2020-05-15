package controller;

import exception.ProductIdNotExistsException;
import model.offer.Offer;
import model.product.Product;

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

    public static void processViewProductEach(String productId) throws ProductIdNotExistsException {
        checkProductExistence(productId);
    }

    public static void processViewBuyersEach(String productId) throws ProductIdNotExistsException {
        checkProductExistence(productId);
    }

    public static void processEditProduct(String productId) throws ProductIdNotExistsException {
        checkProductExistence(productId);
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
        checkOfferExistence(offId);
    }

    public static void processEditOffEach(String offId) throws ProductIdNotExistsException {
        checkOfferExistence(offId);
    }

    public static void processAddOffEach() {

    }

    public static void processViewBalance() {

    }
    public static void checkProductExistence(String productId) throws ProductIdNotExistsException {
        for (Product product : Product.getAllProducts()) {
            if (productId.equals(product.getProductID())) {
                return;
            }
        }
        throw new ProductIdNotExistsException("No product exists with this Id.");
    }
    public static void checkOfferExistence(String offerId) throws ProductIdNotExistsException {
        for (Offer offer : Offer.getAllOffs()) {
            if (offerId.equals(offer.getOffID())) {
                return;
            }
        }
        throw new ProductIdNotExistsException("No offer exists with this Id.");
    }
}
