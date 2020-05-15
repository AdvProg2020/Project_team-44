package controller;

import exception.CodedDiscountNotExistsException;
import exception.ProductIdNotExistsException;
import exception.UsernameNotExistsException;
import model.CodedDiscount;
import model.account.Account;
import model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ManagerAccountController {
    public static ArrayList<String> processViewPersonalInfo() {
        return LoginPageController.getLoggedInAccount().getInfo();
    }

    public static void processEditFieldEach(String field, String newValue) {
        LoginPageController.getLoggedInAccount().editInfo(field, newValue);
    }

    public static HashMap<String, String> processManageUsers() {
        HashMap<String, String> userInfo = new HashMap<>();
        for (Account account : Account.getAllAccounts()) {
            userInfo.put(account.getType(), account.getUserName());
        }
        return userInfo;
    }

    public static void processViewUserInfoEach(String username) throws UsernameNotExistsException {
        checkUsernameForDelete(username);
        /*Todo: ask TA how to.*/
    }

    public static void processDeleteUserEach(String username) throws UsernameNotExistsException {
        checkUsernameForDelete(username);
        Account.getAllAccounts().remove(Account.getAccountByUsername(username));
    }

    public static void processCreateManagerProfileEach() {
//        checkUsernameForDelete(username);
        /*Todo: ask TA how to.*/
    }

    public static void processManageAllProducts() {
        /*Todo: ask TA how to.*/
    }

    public static void processRemoveProductEach(String productId) throws ProductIdNotExistsException {
        checkProductForDelete(productId);
        Product.getAllProducts().remove(Product.getProductByID(productId));
    }

    public static void processCreateDiscountCode() {

    }

    public static ArrayList<String> processViewDiscountCodes() {
        ArrayList<String> discountCodes = new ArrayList<>();
        for (CodedDiscount codedDiscount : CodedDiscount.getAllCodedDiscounts()) {
            discountCodes.add(codedDiscount.getDiscountCode());
        }
        return discountCodes;
    }

    public static void processViewDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        checkCodedDiscountForDelete(code);

    }

    public static void processEditDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        checkCodedDiscountForDelete(code);
        /*Todo: how to edit fields*/
    }

    public static void processRemoveDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        checkCodedDiscountForDelete(code);
        CodedDiscount.getAllCodedDiscounts().remove(CodedDiscount.getCodedDiscountByCode(code));
    }

    public static void processManageRequests() {

    }

    public static void processShowRequestDetailsEach(String requestId) {

    }

    public static void processAcceptRequestEach(String requestId) {

    }

    public static void processDeclineRequestEach(String requestId) {

    }

    public static void processManageCategories() {

    }

    public static void processEditCategoryEach(String category) {

    }

    public static void processAddCategoryEach(String category) {

    }

    public static void processRemoveCategoryEach(String category) {

    }

    public static void checkCodedDiscountForDelete(String discountCode) throws CodedDiscountNotExistsException {
        for (CodedDiscount codedDiscount : CodedDiscount.getAllCodedDiscounts()) {
            if (discountCode.equals(codedDiscount.getDiscountCode())) {
                return;
            }
        }
        throw new CodedDiscountNotExistsException("No user exists with this username.");

    }

    public static void checkUsernameForDelete(String username) throws UsernameNotExistsException {
        for (Account account : Account.getAllAccounts()) {
            if (username.equals(account.getUserName())) {
                return;
            }
        }
        throw new UsernameNotExistsException("No user exists with this username.");
    }

    public static void checkProductForDelete(String productId) throws ProductIdNotExistsException {
        for (Product product : Product.getAllProducts()) {
            if (productId.equals(product.getProductID())) {
                return;
            }
        }
        throw new ProductIdNotExistsException("No product exists with this Id.");
    }
}
