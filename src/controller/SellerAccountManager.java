package controller;

import exception.ProductIdNotExistsException;
import model.account.Seller;
import model.offer.Offer;
import model.product.Product;
import model.requests.RequestForAddOff;
import model.requests.RequestForAddProduct;
import model.requests.RequestForEditOff;
import model.requests.RequestForEditProduct;

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

    public static ArrayList<String> processViewCompanyInfo() {
        return LoginPageController.loggedInAccount.getCompanyInfo();
    }

    public static ArrayList<String> processViewSalesHistory() {
        return ((Seller) LoginPageController.loggedInAccount).getAllSellLogIds();
    }

    public static ArrayList<String> processManageProducts() {
        return ((Seller) LoginPageController.loggedInAccount).getProductsToSellIds();
    }

    public static void processViewProductEach(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);

    }

    public static void processViewBuyersEach(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
//        Product.getProductByID(productId)
    }

    public static void processEditProduct(String productId, String field, String newValue, String oldValue) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        RequestForEditProduct requestForEditProduct = new RequestForEditProduct((Seller) LoginPageController.loggedInAccount
                , Product.getProductByID(productId), field, oldValue, newValue);
    }

    public static void processAddProduct() {
//        RequestForAddProduct requestForAddProduct = new RequestForAddProduct()
    }

    public static void processRemoveProduct(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        ((Seller) LoginPageController.loggedInAccount).getProductsToSell().remove(Product.getProductByID(productId));
    }

    public static void processShowCategory() {

    }

    public static ArrayList<String> processViewOffs() {
        return ((Seller) LoginPageController.loggedInAccount).getOffersListIds();
    }

    public static ArrayList<String> processViewOffEach(String offId) throws ProductIdNotExistsException {
        ValidationController.checkOfferExistence(offId);
        return Offer.getOfferById(offId).getOfferInfo();
    }

    public static void processEditOffEach(String offId, String field, String oldValue, String newValue) throws ProductIdNotExistsException {
        ValidationController.checkOfferExistence(offId);
        RequestForEditOff requestForEditOff = new RequestForEditOff((Seller) LoginPageController.loggedInAccount
                , Offer.getOfferById(offId), field, oldValue, newValue);
    }

    public static void processAddOffEach() {
//        RequestForAddOff requestForAddOff = new RequestForAddOff()
    }

    public static double processViewBalance() {
        return LoginPageController.loggedInAccount.getBalance();
    }

}
