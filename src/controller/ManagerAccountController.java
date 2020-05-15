package controller;

import exception.CodedDiscountNotExistsException;
import exception.ProductIdNotExistsException;
import exception.RequestNotExistsException;
import exception.UsernameNotExistsException;
import model.CodedDiscount;
import model.account.Account;
import model.account.Manager;
import model.product.Product;

import java.util.ArrayList;

public abstract class ManagerAccountController {
    public static ArrayList<String> processViewPersonalInfo() {
        return LoginPageController.getLoggedInAccount().getInfo();
    }

    public static void processEditFieldEach(String field, String newValue) {
        LoginPageController.getLoggedInAccount().editInfo(field, newValue);
    }

    public static ArrayList<String> processManageUsers() {
        return Manager.showAllUsers();
    }

    public static ArrayList<String> processViewUserInfoEach(String username) throws UsernameNotExistsException {
        checkUsernameExistence(username);
        return Account.getAccountByUsername(username).getInfo();
    }

    public static void processDeleteUserEach(String username) throws UsernameNotExistsException {
        checkUsernameExistence(username);
        Account.getAllAccounts().remove(Account.getAccountByUsername(username));
    }

    public static void processCreateManagerProfileEach() {
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
        return Manager.showAllCodedDiscounts();
    }

    public static ArrayList<String> processViewDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        checkCodedDiscountExistence(code);
        return CodedDiscount.getCodedDiscountByCode(code).getInfo();
    }

    public static void processEditDiscountCodeEach(String code, String field, String newValue) throws CodedDiscountNotExistsException {
        checkCodedDiscountExistence(code);
        Manager.editDiscountCode(code, field, newValue);
    }

    public static void processRemoveDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        checkCodedDiscountExistence(code);
        CodedDiscount.getAllCodedDiscounts().remove(CodedDiscount.getCodedDiscountByCode(code));
    }

    public static ArrayList<String> processManageRequests() {
        return Request.showAllRequests();
    }

    public static ArrayList<String> processShowRequestDetailsEach(String requestId) throws RequestNotExistsException {
        checkRequestExistence(requestId);
        return Request.getRequestById(requestId).getInfo();
    }

    public static void processAcceptRequestEach(String requestId) throws RequestNotExistsException {
        checkRequestExistence(requestId);

    }

    public static void processDeclineRequestEach(String requestId) throws RequestNotExistsException {
        checkRequestExistence(requestId);

    }

    public static void processManageCategories() {

    }

    public static void processEditCategoryEach(String category) {

    }

    public static void processAddCategoryEach(String category) {

    }

    public static void processRemoveCategoryEach(String category) {

    }

    public static void checkRequestExistence(String Id) throws RequestNotExistsException {
        for (Request request : Requset.getAllRequests()) {
            if (Id.equals(request.getId())) {
                return;
            }
        }
        throw new RequestNotExistsException("No Request exists with this Id.");
    }

    public static void checkCodedDiscountExistence(String discountCode) throws CodedDiscountNotExistsException {
        for (CodedDiscount codedDiscount : CodedDiscount.getAllCodedDiscounts()) {
            if (discountCode.equals(codedDiscount.getDiscountCode())) {
                return;
            }
        }
        throw new CodedDiscountNotExistsException("No CodedDiscount exists with this code.");

    }

    public static void checkUsernameExistence(String username) throws UsernameNotExistsException {
        for (Account account : Account.getAllAccounts()) {
            if (username.equals(account.getUsername())) {
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
