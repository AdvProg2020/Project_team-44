package controller;

import exception.ProductIdNotExistsException;
import model.Category;
import model.account.Purchaser;
import model.account.Seller;
import model.offer.Offer;
import model.product.Product;
import model.requests.RequestForEditOff;
import model.requests.RequestForEditProduct;

import java.util.ArrayList;

public abstract class SellerAccountController {
    public static ArrayList<String> processViewPersonalInfo() {
        return LoginPageController.loggedInAccount.getInfo();
    }

    public static void processEditFieldEach(String field, String newValue) {
        LoginPageController.loggedInAccount.editInfo(field, newValue);
    }

    public static ArrayList<String> processViewCompanyInfo() {
        return ((Seller)LoginPageController.loggedInAccount).getCompanyInfo();
    }

    public static ArrayList<String> processViewSalesHistory() {
        return ((Seller) LoginPageController.loggedInAccount).getAllSellLogIds();
    }

    public static ArrayList<String> processManageProducts() {
        return ((Seller) LoginPageController.loggedInAccount).getProductsToSellIds();
    }

    public static ArrayList<String> processViewProductEach(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        return ProductsPageController.selectedProduct.getProductInfo();
    }

    public static ArrayList<String> processViewBuyersEach(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        ArrayList<String> allInfo = new ArrayList<>();
        for (Purchaser purchaser :Product.getProductByID(productId).getAllBuyers()) {
            allInfo.add(purchaser.getInfo().toString());
        }
        return allInfo;
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

    public static ArrayList<String> processShowCategory() {
        return Category.getAllCategoryNames();
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
