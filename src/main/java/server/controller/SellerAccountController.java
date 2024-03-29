package server.controller;

import server.exception.*;
import server.model.Category;
import server.model.account.Purchaser;
import server.model.account.Seller;
import server.model.offer.Offer;
import server.model.product.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class SellerAccountController {
    public static ArrayList<String> processViewPersonalInfo() {
        return LoginPageController.loggedInAccount.getInfo();
    }

    public static void processEditFieldEach(String field, String newValue) throws SellerFieldsNotExistException {
        ValidationController.checkSellerFieldExistence(field);
        LoginPageController.loggedInAccount.editInfo(field, newValue);
    }

    public static ArrayList<String> processViewCompanyInfo() {
        return ((Seller) LoginPageController.loggedInAccount).getCompanyInfo();

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
        for (Purchaser purchaser : Product.getProductByID(productId).getAllPurchaser()) {
            allInfo.add(purchaser.getInfo().toString());
        }
        return allInfo;
    }

    public static void processEditProduct(Product product, String name, String companyName, double price, String explanationText, String imageName)
            throws ProductIdNotExistsException, ProductFieldsNotException {
//        ValidationController.checkProductExistence(productId);
//        ValidationController.checkProductFieldExistence(field);
        ((Seller) LoginPageController.loggedInAccount).editProductRequest(product, name, companyName, price, explanationText, imageName);
    }

    public static void processAddProduct(String category, String productName, int price, String explanationText) throws CategoryNotExistsException {
        ValidationController.checkCategoryExistence(category);
        ((Seller) LoginPageController.loggedInAccount).addProductRequest(Category.getCategoryByName(category), productName, price, explanationText);
    }

    public static void processRemoveProduct(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
       // ((Seller) LoginPageController.loggedInAccount).getProductsToSell().remove(Product.getProductByID(productId));
        ((Seller) LoginPageController.loggedInAccount).deleteProductRequest(productId);
    }

    public static ArrayList<String> processShowCategory() {
        return Category.getAllCategoryNames();
    }

    public static ArrayList<String> processViewOffs() {
        return ((Seller) LoginPageController.loggedInAccount).getOfferListIds();
    }

    public static ArrayList<String> processViewOffEach(String offId) throws ProductIdNotExistsException {
        ValidationController.checkOfferExistence(offId);
        return Offer.getOfferById(offId).getOfferInfo();
    }

    public static void processEditOffEach(Offer offer, ArrayList<Product> productList, Date initialDate, Date finalDate, int discountPercentage)
            throws ProductIdNotExistsException, OfferFieldNotExistsException {
//        ValidationController.checkOfferExistence(offId);
//        ValidationController.checkOfferFieldExistence(field);
        ((Seller) LoginPageController.loggedInAccount).editOffersRequest(offer, productList, initialDate, finalDate, discountPercentage);
    }

    public static void processAddOffEach(ArrayList<String> productListIds, String initialDate, String finalDate,
                                         int discountPercentage) throws SellerNotOwnsProductException, ParseException, TimeExpiresException {
        for (String productId : productListIds) {
            ValidationController.checkProductBelongToSeller((Seller) LoginPageController.loggedInAccount,
                    Product.getProductByID(productId));
        }
        ValidationController.checkTime(new SimpleDateFormat("dd/MM/yyyy").parse(initialDate));
        ValidationController.checkTime(new SimpleDateFormat("dd/MM/yyyy").parse(finalDate));
        ArrayList<Product> allProducts = new ArrayList<>();
        for (String productId : productListIds) {
            allProducts.add(Product.getProductByID(productId));
        }
        ((Seller) LoginPageController.loggedInAccount).addOfferRequest(allProducts, new SimpleDateFormat("dd/MM/yyyy").parse(initialDate), new SimpleDateFormat("dd/MM/yyyy").parse(finalDate), discountPercentage);
    }

    public static double processViewBalance() {
        return LoginPageController.loggedInAccount.getBalance();
    }

}
