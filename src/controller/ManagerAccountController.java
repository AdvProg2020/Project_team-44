package controller;

import exception.*;
import model.Category;
import model.CodedDiscount;
import model.account.Account;
import model.account.Manager;
import model.product.Product;
import model.requests.Request;

import java.text.ParseException;
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
        ValidationController.checkUsernameExistence(username);
        return Account.getAccountByUsername(username).getInfo();
    }

    public static void processDeleteUserEach(String username) throws UsernameNotExistsException {
        ValidationController.checkUsernameExistence(username);
        Account.getAllAccounts().remove(Account.getAccountByUsername(username));
    }

    public static void processCreateManagerProfileEach() {
        /*Todo: ask TA how to.*/
    }


    public static void processRemoveProductEach(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        Product.getAllProducts().remove(Product.getProductByID(productId));
    }

    public static void processCreateDiscountCode() {
        Manager.createCodedDiscount();
    }

    public static ArrayList<String> processViewDiscountCodes() {
        return CodedDiscount.getAllCodedDiscounts();
    }

    public static ArrayList<String> processViewDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        ValidationController.checkCodedDiscountExistence(code);
        return CodedDiscount.getCodedDiscountByCode(code).getInfo();
    }

    public static void processEditDiscountCodeEach(String code, String field, String newValue) throws CodedDiscountNotExistsException, ParseException {
        ValidationController.checkCodedDiscountExistence(code);
        Manager.editCodedDiscount(code, field, newValue);
    }

    public static void processRemoveDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        ValidationController.checkCodedDiscountExistence(code);
        CodedDiscount.getAllCodedDiscounts().remove(CodedDiscount.getCodedDiscountByCode(code));
    }

    public static ArrayList<String> processManageRequests() {
        return Request.getAllRequests();
    }

    public static ArrayList<String> processShowRequestDetailsEach(int requestId) throws RequestNotExistsException {
        ValidationController.checkRequestExistence(requestId);
        return Request.getRequestById(requestId).getInfo();
    }

    public static void processAcceptRequestEach(int requestId) throws RequestNotExistsException {
        ValidationController.checkRequestExistence(requestId);
        /*TODO*/
    }

    public static void processDeclineRequestEach(int requestId) throws RequestNotExistsException {
        ValidationController.checkRequestExistence(requestId);
        /*TODO*/
    }

    public static void processManageCategories() {

    }

    public static void processEditCategoryEach(String category) throws CategoryNotExistsException {
        ValidationController.checkCategoryExistence(category);
        /*TODO*/
    }

    public static void processAddCategoryEach(String category) throws CategoryNotExistsException {
        ValidationController.checkCategoryExistence(category);
        /*TODO*/
    }

    public static void processRemoveCategoryEach(String category) throws CategoryNotExistsException {
        ValidationController.checkCategoryExistence(category);
        /*TODO*/
    }


}
