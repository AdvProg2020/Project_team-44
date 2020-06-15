package controller;

import controller.fields.CodedDiscountFields;
import controller.fields.OfferFields;
import controller.fields.ProductFields;
import controller.fields.accountFields.ManagerFields;
import controller.fields.accountFields.PurchaserFields;
import controller.fields.accountFields.SellerFields;
import exception.*;
import model.Category;
import model.CodedDiscount;
import model.Sort.Sort;
import model.account.Account;
import model.account.Purchaser;
import model.account.Seller;
import model.buyLog.BuyLog;
import model.offer.Offer;
import model.product.Product;
import model.requests.Request;

import java.util.Date;

public abstract class ValidationController {
    static void checkPasswordForLogin(String username, String password) throws WrongPasswordException {
        if (!Account.getAccountByUsername(username).getPassword().equals(password)) {
            throw new WrongPasswordException("Password is incorrect.");

        }
    }

    static void checkUsernameForLogin(String username) throws UsernameNotExistsException {
        for (Account account : Account.getAllAccounts()) {
            if (username.equals(account.getUserName())) {
                return;

            }
        }
        throw new UsernameNotExistsException("No user exists with this username.");
    }

    static void checkUsernameForRegistration(String username) throws UsernameExistsException {
        for (Account account : Account.getAllAccounts()) {
            if (username.equals(account.getUserName())) {
                throw new UsernameExistsException("User exists with this username");

            }
        }
    }

    static void checkCategoryExistence(String selectedCategory) throws CategoryNotExistsException {
        for (Category category : Category.getAllCategories()) {
            if (selectedCategory.equals(category.getName())) {
                return;

            }
        }
        throw new CategoryNotExistsException("Wrong category.");

    }

    static void checkRequestExistence(String Id) throws RequestNotExistsException {
        for (Request request : Request.getAllRequests()) {
            if (Id.equals(request.getRequestId())) {
                return;

            }
        }
        throw new RequestNotExistsException("No Request exists with this Id.");
    }

    static void checkCodedDiscountExistence(String discountCode) throws CodedDiscountNotExistsException {
        for (CodedDiscount codedDiscount : CodedDiscount.getAllCodedDiscounts()) {
            if (discountCode.equals(codedDiscount.getDiscountCode())) {
                return;

            }
        }
        throw new CodedDiscountNotExistsException("No CodedDiscount exists with this code.");

    }

    static void checkUsernameExistence(String username) throws UsernameNotExistsException {
        for (Account account : Account.getAllAccounts()) {
            if (username.equals(account.getUserName())) {
                return;

            }
        }
        throw new UsernameNotExistsException("No user exists with this username.");
    }

    static void checkProductExistence(String productId) throws ProductIdNotExistsException {
        for (Product product : Product.getAllProducts()) {
            if (productId.equals(product.getProductID())) {
                return;

            }
        }
        throw new ProductIdNotExistsException("No product exists with this Id.");
    }

    static void checkSortExistence(String availableSort) throws SortNotExistsException {
        for (Sort sort : Sort.values()) {
            if (sort.toString().equals(availableSort)) {
                return;

            }
        }
        throw new SortNotExistsException("Wrong sort.");
    }

    static void checkFilterExistence(String availableFilter) throws FilterNotExistsException {
        for (String filter : ProductsPageController.allFilters) {
            if (availableFilter.equals(filter)) {
                return;
            }
        }
        throw new FilterNotExistsException("Wrong filter.");
    }

    static void checkOrderExistence(String orderId) throws OrderNotExistsException {
        for (BuyLog order : LoginPageController.loggedInAccount.getBuyLogListHistory()) {
            if (orderId.equals(order.getLogID())) {
                return;

            }
        }
        throw new OrderNotExistsException("No order exists with this Id.");
    }

    static void checkOfferExistence(String offerId) throws ProductIdNotExistsException {
        for (Offer offer : Offer.getAllOffers()) {
            if (offerId.equals(offer.getOfferID())) {
                return;

            }
        }
        throw new ProductIdNotExistsException("No offer exists with this Id.");
    }

    public static void checkProductNotExistsInCart(Account loggedInAccount, Product product)
            throws ProductNotExistsInCartException {
        if (!((Purchaser) loggedInAccount).getCart().containsKey(product)) {
            throw new ProductNotExistsInCartException("Product is not in cart.");

        }
    }

    public static void checkProductExistsInCart(Account loggedInAccount, Product product)
            throws ProductAlreadyExistsInCartException {
        if (((Purchaser) loggedInAccount).getCart().containsKey(product)) {
            throw new ProductAlreadyExistsInCartException("Product is in cart.");

        }
    }

    public static void checkSellerOwnsProduct(Seller seller, Product product) throws SellerUserNameNotExists {
        if (!product.getAllSellers().contains(seller)) {
            throw new SellerUserNameNotExists("This Seller Doesnt sell this product");

        }
    }

    public static void checkPurchaserOwnsCodedDiscount(Purchaser purchaser, CodedDiscount codedDiscount) throws PurchaserNotOwnsCodedDiscountException {
        if (!purchaser.getAllDiscountCodes().contains(codedDiscount)) {
            throw new PurchaserNotOwnsCodedDiscountException("Purchaser doesnt have this code");
        }
    }

    public static void checkCodedDiscountTime(CodedDiscount codedDiscount, Date date) throws CodedDiscountExpiresException {
        if (date.after(codedDiscount.getFinalDate())) {
            throw new CodedDiscountExpiresException("Times over");
        }
    }

    public static void checkEnoughMoneyToPay(Purchaser purchaser, double moneyToPay) throws NotEnoughMoneyToPayException {
        if (purchaser.getBalance() < purchaser.getCartMoneyToPay()) {
            throw new NotEnoughMoneyToPayException("Not Enough money");
        }
    }

    public static void checkProductBelongToSeller(Seller seller, Product product) throws SellerNotOwnsProductException {
        if (!seller.getProductsToSell().containsKey(product)) {
            throw new SellerNotOwnsProductException("This Seller Doesnt sell this product.");
        }
    }

    public static void checkOfferTime(Offer offer, Date date) throws OfferExpiresException {
        if (date.after(offer.getFinalDate())) {
            throw new OfferExpiresException("Times over");
        }
    }

    public static void checkTime(Date date) throws TimeExpiresException {
        if (date.after(new Date())) {
            throw new TimeExpiresException("Times over");
        }
    }

    public static void checkProductExistenceInCategory(Category category, Product product) throws ProductExistsINCategoryException {
        if (category.getAllSubProducts().contains(product)) {
            throw new ProductExistsINCategoryException("We have this product already");
        }
    }

    public static void checkCategoryAlreadyExists(Category category) throws CategoryAlreadyExistsException {
        if (Category.getAllCategories().contains(category)) {
            throw new CategoryAlreadyExistsException("Category already exists");
        }
    }

    public static void checkOfferFieldExistence(String field) throws OfferFieldNotExistsException {
        for (OfferFields offerField : OfferFields.values()) {
            if (offerField.toString().equals(field)) {
                return;
            }
        }
        throw new OfferFieldNotExistsException("Wrong field.");
    }

    public static void checkProductFieldExistence(String field) throws ProductFieldsNotException {
        for (ProductFields productFields : ProductFields.values()) {
            if (productFields.toString().equals(field)) {
                return;
            }
        }
        throw new ProductFieldsNotException("Wrong field.");
    }

    public static void checkPurchaserFieldExistence(String field) throws PurchaserFieldsNotExistException {
        for (PurchaserFields purchaserFields : PurchaserFields.values()) {
            if (purchaserFields.toString().equals(field)) {
                return;
            }
        }
        throw new PurchaserFieldsNotExistException("Wrong field.");
    }

    public static void checkSellerFieldExistence(String field) throws SellerFieldsNotExistException {
        for (SellerFields sellerFields : SellerFields.values()) {
            if (sellerFields.toString().equals(field)) {
                return;
            }
        }
        throw new SellerFieldsNotExistException("Wrong field.");
    }

    public static void checkManagerFieldExistence(String field) throws ManagerFieldsNotExistException {
        for (ManagerFields managerFields : ManagerFields.values()) {
            if (managerFields.toString().equals(field)) {
                return;
            }
        }
        throw new ManagerFieldsNotExistException("Wrong field.");
    }

    public static void checkCodedDiscountFieldExistence(String field) throws CodedDiscountFieldsNotExistException {
        for (CodedDiscountFields codedDiscountFields : CodedDiscountFields.values()) {
            if (codedDiscountFields.toString().equals(field)) {
                return;
            }
        }
        throw new CodedDiscountFieldsNotExistException("Wrong field.");
    }


}
