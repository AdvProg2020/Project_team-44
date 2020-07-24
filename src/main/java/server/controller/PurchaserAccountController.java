package server.controller;

import server.exception.*;
import server.model.CodedDiscount;
import server.model.account.Purchaser;
import server.model.buyLog.BuyLog;
import server.model.product.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public abstract class PurchaserAccountController {
    static String discountCode;

    public static ArrayList<String> processViewPersonalInfo() {
        return LoginPageController.loggedInAccount.getInfo();
    }

    public static void processEditFieldEach(String field, String newValue) throws PurchaserFieldsNotExistException {
        ValidationController.checkPurchaserFieldExistence(field);
        LoginPageController.loggedInAccount.editInfo(field, newValue);
    }

    public static ArrayList<String> processShowProductsEach() {
        return ((Purchaser) LoginPageController.loggedInAccount).getCartProductNames();
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

    public static void receiveInfo(String address, String telephoneNumber) {
        LoginPageController.loggedInAccount.setTelephoneNumber(telephoneNumber);
        ((Purchaser) LoginPageController.loggedInAccount).setAddress(address);
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

    public static void getCodedDiscount(String discountCode) throws PurchaserNotOwnsCodedDiscountException, CodedDiscountExpiresException {
        ValidationController.checkPurchaserOwnsCodedDiscount((Purchaser) LoginPageController.loggedInAccount
                , CodedDiscount.getCodedDiscountByCode(discountCode));
        ValidationController.checkCodedDiscountTime(CodedDiscount.getCodedDiscountByCode(discountCode), new Date());

    }

    public static void processPayment() throws NotEnoughMoneyToPayException {
        ValidationController.checkEnoughMoneyToPay(((Purchaser) LoginPageController.loggedInAccount)
                , ((Purchaser) LoginPageController.loggedInAccount).getCartMoneyToPay());
        ((Purchaser) LoginPageController.loggedInAccount).purchase(PurchaserAccountController.discountCode);
    }

    //    only called when user click the delete button, the case that quantity went 0 is handled in decreaseItemInCart
    public static void deleteItemInCart(String productName) {
        ((Purchaser) LoginPageController.getLoggedInAccount()).getCart().remove(Product.getProductByName(productName));
    }

    public static ArrayList<Product> getCartProducts() {
        return ((Purchaser) LoginPageController.getLoggedInAccount()).getCartProducts();
    }

    //    increase means add one to products quantity, not adding server.main.Main new product; increase has no limits
    public static void increaseItemInCart(String productName) {
        //  pre-actions to avoid hard coding
        Purchaser currentPurchaser = ((Purchaser) LoginPageController.getLoggedInAccount());
        HashMap<Product, Integer> currentPurchaserCart = currentPurchaser.getCart();
        int currentProductQuantity = ((Purchaser) LoginPageController.getLoggedInAccount()).getCart().get(Product.getProductByName(productName));
        Product increasedProduct = Product.getProductByName(productName);
        //  increase by 1
        currentPurchaserCart.replace(increasedProduct,
                currentProductQuantity,
                currentProductQuantity + 1);
        // set temporary cart to the original one
        ((Purchaser) LoginPageController.getLoggedInAccount()).setCart(currentPurchaserCart);
    }

    //  if quantity of product went to 0, the product would be deleted from the cart
    public static void decreaseItemInCart(String productName) {
        //  pre-actions to avoid hard coding
        Purchaser currentPurchaser = ((Purchaser) LoginPageController.getLoggedInAccount());
        HashMap<Product, Integer> currentPurchaserCart = currentPurchaser.getCart();
        int currentProductQuantity = ((Purchaser) LoginPageController.getLoggedInAccount()).getCart().get(Product.getProductByName(productName));
        Product decreasedProduct = Product.getProductByName(productName);
        // remove if there was only 1 product left
        if (currentProductQuantity == 1) {
            currentPurchaserCart.remove(decreasedProduct);
        } else { // decrease by 1
            currentPurchaserCart.replace(decreasedProduct, currentProductQuantity, currentProductQuantity - 1);
        }
        //  set temporary cart to original one
        ((Purchaser) LoginPageController.getLoggedInAccount()).setCart(currentPurchaserCart);
    }
}
