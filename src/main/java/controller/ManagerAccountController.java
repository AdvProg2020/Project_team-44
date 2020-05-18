package controller;

import exception.*;
import model.Category;
import model.CodedDiscount;
import model.account.Account;
import model.account.Manager;
import model.product.Product;
import model.requests.Request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class ManagerAccountController {
    public static ArrayList<String> processViewPersonalInfo() {
        return LoginPageController.loggedInAccount.getInfo();
        /**DONE**/
    }

    public static void processEditFieldEach(String field, String newValue) {
        LoginPageController.loggedInAccount.editInfo(field, newValue);
        /**DONE**/
    }

    public static ArrayList<String> processManageUsers() {
        return Manager.showAllUsers();
        /**DONE**/
    }

    public static ArrayList<String> processViewUserInfoEach(String username) throws UsernameNotExistsException {
        ValidationController.checkUsernameExistence(username);
        return Account.getAccountByUsername(username).getInfo();
        /**DONE**/
    }

    public static void processDeleteUserEach(String username) throws UsernameNotExistsException {
        ValidationController.checkUsernameExistence(username);
        Account.getAllAccounts().remove(Account.getAccountByUsername(username));
        /**DONE**/
    }

    public static void processCreateManagerProfileEach(String username, String password, String firstName, String lastName, String email
            , String telephoneNumber) throws UsernameNotExistsException {
        ValidationController.checkUsernameExistence(username);
        Manager manager = new Manager(username, firstName, lastName, email, telephoneNumber, password);
        /**DONE**/
    }


    public static void processRemoveProductEach(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        Product.getAllProducts().remove(Product.getProductByID(productId));
        /**DONE**/
    }

    public static void processCreateDiscountCode(String initialDate, String finalDate, int discountPercentage, int maxAuthorizedPrice) throws ParseException {
//        Date initial = new SimpleDateFormat("dd/MM/yyyy").parse(initialDate);
        Manager.createCodedDiscount(new SimpleDateFormat("dd/MM/yyyy").parse(initialDate),
                new SimpleDateFormat("dd/MM/yyyy").parse(finalDate), discountPercentage, maxAuthorizedPrice);
    }

    public static ArrayList<String> processViewDiscountCodes() {
        return CodedDiscount.getAllDiscountCodes();
    }

    public static ArrayList<String> processViewDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        ValidationController.checkCodedDiscountExistence(code);
        return CodedDiscount.getCodedDiscountByCode(code).getCodedDiscountInfo();
    }

    public static void processEditDiscountCodeEach(String code, String field, String newValue) throws CodedDiscountNotExistsException, ParseException {
        ValidationController.checkCodedDiscountExistence(code);
        Manager.editCodedDiscount(code, field, newValue);
        /**DONE**/
    }

    public static void processRemoveDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        ValidationController.checkCodedDiscountExistence(code);
        CodedDiscount.getAllCodedDiscounts().remove(CodedDiscount.getCodedDiscountByCode(code));
        /**DONE**/
    }

    public static ArrayList<String> processManageRequests() {
        return Request.getAllRequestsId();
    }

    public static ArrayList<String> processShowRequestDetailsEach(String requestId) throws RequestNotExistsException {
        ValidationController.checkRequestExistence(requestId);
        return Request.getRequestById(requestId).getRequestDetails();
    }

    public static void processAcceptRequestEach(String requestId) throws RequestNotExistsException {
        ValidationController.checkRequestExistence(requestId);
        ((Manager) LoginPageController.loggedInAccount).accept(requestId);
    }

    public static void processDeclineRequestEach(String requestId) throws RequestNotExistsException {
        ValidationController.checkRequestExistence(requestId);
        ((Manager) LoginPageController.loggedInAccount).decline(requestId);
    }

    public static ArrayList<String> processManageCategories() {
        return Category.getAllCategoryNames();
    }

    public static void processEditCategoryEach(String category) throws CategoryNotExistsException {
        ValidationController.checkCategoryExistence(category);
        /*TODO*/
    }

    public static void processAddCategoryEach(String category) throws CategoryNotExistsException {
        ValidationController.checkCategoryExistence(category);
        ((Manager) LoginPageController.loggedInAccount).addCategory(category);
//        Manager.addCategory(category);
    }

    public static void processRemoveCategoryEach(String category) throws CategoryNotExistsException {
        ValidationController.checkCategoryExistence(category);
        Category.getAllCategories().remove(Category.getCategoryByName(category));
    }


}
