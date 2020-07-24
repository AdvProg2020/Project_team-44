package controller;

import exception.*;
import model.Category;
import model.CodedDiscount;
import model.account.Account;
import model.account.Manager;
import model.account.Seller;
import model.offer.Offer;
import model.product.Product;
import model.requests.Request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class ManagerAccountController {
    public static ArrayList<String> processViewPersonalInfo() {
        return LoginPageController.loggedInAccount.getInfo();
    }

    public static void processEditFieldEach(String field, String newValue) throws ManagerFieldsNotExistException {
        ValidationController.checkManagerFieldExistence(field);
        LoginPageController.loggedInAccount.editInfo(field, newValue);
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

    public static void processCreateManagerProfileEach(String username, String password, String firstName, String lastName, String email
            , String telephoneNumber) throws UsernameNotExistsException {
        ValidationController.checkUsernameExistence(username);
        Manager manager = new Manager(username, firstName, lastName, email, telephoneNumber, password);
    }


    public static void processRemoveProductEach(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        Product.getAllProducts().remove(Product.getProductByID(productId));
    }

    public static void processCreateDiscountCode(String initialDate, String finalDate, int discountPercentage, int maxAuthorizedPrice) throws ParseException {
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

    public static void processEditDiscountCodeEach(String code, String field, String newValue)
            throws CodedDiscountNotExistsException, ParseException, CodedDiscountFieldsNotExistException {
        ValidationController.checkCodedDiscountExistence(code);
        ValidationController.checkCodedDiscountFieldExistence(field);
        Manager.editCodedDiscount(code, field, newValue);
    }

    public static void processRemoveDiscountCodeEach(String code) throws CodedDiscountNotExistsException {
        ValidationController.checkCodedDiscountExistence(code);
        CodedDiscount.getAllCodedDiscounts().remove(CodedDiscount.getCodedDiscountByCode(code));
    }

    public static ArrayList<String> processManageRequests() {
        return Request.getAllRequestsId();
    }

    public static ArrayList<String> processShowRequestDetailsEach(String requestId) throws RequestNotExistsException {
        ValidationController.checkRequestExistence(requestId);
        return Request.getRequestById(requestId).getRequestDetails();
    }

    public static void processAcceptRequestEach(String requestId) throws RequestNotExistsException, ParseException {
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

    public static void processEditCategoryEachForName(String category, String newName) throws CategoryNotExistsException, CategoryAlreadyExistsException {
        ValidationController.checkCategoryExistence(category);
        ValidationController.checkCategoryAlreadyExists(Category.getCategoryByName(newName));//null
        Category.getCategoryByName(category).setName(newName);
    }

    public static void processEditCategoryEachForAttributes(
            String category, ArrayList<HashMap<String, ArrayList<String>>> allProductsNewInfo) {

    }

    public static void processAddCategoryEach(String category, String parentCategory) throws CategoryNotExistsException, CategoryAlreadyExistsException {
        ValidationController.checkThisCategoryAlreadyExits(category);
        ValidationController.checkCategoryExistence(parentCategory);//if parentCategoryNull
        ((Manager) LoginPageController.loggedInAccount).addCategory(category, Category.getCategoryByName(parentCategory));
    }

    public static ArrayList<String> getCategoryProducts(String categoryName) throws CategoryNotExistsException {
        ValidationController.checkCategoryExistence(categoryName);
        ArrayList<String> allProducts = new ArrayList<>();
        for (Category category : Category.getCategoryByName(categoryName).getSubCategories()) {
            allProducts.addAll(getCategoryProducts(category.getName()));
        }
        allProducts.addAll(Category.getCategoryByName(categoryName).getAllSubProductsName());
        return allProducts;
    }

    public static void processRemoveCategoryEach(String categoryName) throws CategoryNotExistsException {
        ValidationController.checkCategoryExistence(categoryName);
        for (Category category : Category.getCategoryByName(categoryName).getSubCategories()) {
            processRemoveCategoryEach(category.getName());
        }
        for (Product product : Category.getCategoryByName(categoryName).getAllSubProducts()) {
            Product.getAllProducts().remove(product);
            for (Offer offer : Offer.getAllOffers()) {
                offer.getProductList().remove(product);
            }
            for (Seller seller : product.getAllSellers()) {
                seller.getProductsToSell().remove(product);
            }
        }
        Category.getAllCategories().remove(Category.getCategoryByName(categoryName));
    }


}
