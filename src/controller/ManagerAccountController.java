package controller;

import exception.CodedDiscountNotExistsException;
import exception.ProductIdNotExistsException;
import exception.UsernameNotExistsException;
import model.CodedDiscount;
import model.account.Account;
import model.account.Manager;
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
        return Manager.showAllUsers();
    }

    public static void processViewUserInfoEach(String username) throws UsernameNotExistsException {
        checkUsernameExistence(username);
        /*Todo: ask TA how to.*/
    }

    public static void processDeleteUserEach(String username) throws UsernameNotExistsException {
        checkUsernameExistence(username);
        Account.getAllAccounts().remove(Account.getAccountByUsername(username));
    }

    public static void processCreateManagerProfileEach() {
//       checkUsernameExistence(username);
        /*Todo: ask TA how to.*/
    }

    public static void processManageAllProducts() {
        /*Todo: ask TA how to.*/
    }

    public static void processRemoveProductEach(String productId) throws ProductIdNotExistsException {
        checkProductExistence(productId);
        Product.getAllProducts().remove(Product.getProductByID(productId));
    }

    public static void processCreateDiscountCode() {
        Manager.createCodedDiscount();
    }

    public static ArrayList<String> processViewDiscountCodes() {
        ArrayList<String> discountCodes = new ArrayList<>();
        for (CodedDiscount codedDiscount : CodedDiscount.getAllCodedDiscounts()) {
            discountCodes.add(codedDiscount.getDiscountCode());
        }
        return discountCodes;
    }

    public static void processViewDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        checkCodedDiscountExistence(code);

    }

    public static void processEditDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        checkCodedDiscountExistence(code);
        /*Todo: how to edit fields*/
    }

    public static void processRemoveDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        checkCodedDiscountExistence(code);
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

    public static void checkCodedDiscountExistence(String discountCode) throws CodedDiscountNotExistsException {
        for (CodedDiscount codedDiscount : CodedDiscount.getAllCodedDiscounts()) {
            if (discountCode.equals(codedDiscount.getDiscountCode())) {
                return;
            }
        }
        throw new CodedDiscountNotExistsException("No user exists with this username.");

    }

    public static void checkUsernameExistence(String username) throws UsernameNotExistsException {
        for (Account account : Account.getAllAccounts()) {
            if (username.equals(account.getUserName())) {
                return;
            }
        }
        throw new UsernameNotExistsException("No user exists with this username.");
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
