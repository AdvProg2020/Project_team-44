package controller;

import exception.*;
import model.Category;
import model.CodedDiscount;
import model.Sort.Sort;
import model.account.Account;
import model.buyLog.BuyLog;
import model.offer.Offer;
import model.product.Product;
import model.requests.Request;

public class ValidationController {
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

    static void checkRequestExistence(int Id) throws RequestNotExistsException {
        for (Request request : Request.getAllRequests()) {
            if (Id == request.getRequestId()) {
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
        for (String filter : allFilters()) {
            if (availableFilter.equals(filter)) {
                return;
            }
        }
        throw new FilterNotExistsException("Wrong filter.");
    }

    void checkOrderExistence(String orderId) throws OrderNotExistsException {
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

}
