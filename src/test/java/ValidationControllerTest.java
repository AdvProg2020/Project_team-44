import controller.LoginPageController;
import controller.ProductsPageController;
import controller.ValidationController;
import exception.*;
import model.*;
import model.account.Purchaser;
import model.account.Seller;
import model.buyLog.BuyLog;
import model.offer.Offer;
import model.product.Product;
import model.requests.Request;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ValidationControllerTest {
    @Test
    public void checkPasswordForLoginTest() {
        Purchaser purchaserAccount = DataBaseForTest.purchaser1;
        String username = "Am 4";
        String password = "12aa";
        try {
            ValidationController.checkPasswordForLogin(username, password);
            Assert.assertEquals(10, 10);
        } catch (WrongPasswordException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkPasswordForLogin("Am 4", "32");
            Assert.assertEquals(10, 9);
        } catch (WrongPasswordException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkUserNameForLoginTest() {
        Purchaser purchaser = DataBaseForTest.purchaser2;
        try {
            ValidationController.checkUsernameForLogin("G@3");
            Assert.assertEquals(10, 10);
        } catch (UsernameNotExistsException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkUsernameForLogin("sdf");
            Assert.assertEquals(10, 9);
        } catch (UsernameNotExistsException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkUsernameForRegistrationTest() {
        Purchaser purchaser = DataBaseForTest.purchaser2;
        try {
            ValidationController.checkUsernameForRegistration("abc");
            Assert.assertEquals(10, 10);
        } catch (UsernameExistsException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkUsernameForRegistration("G@3");
            Assert.assertEquals(10, 9);
        } catch (UsernameExistsException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkCategoryExistenceTest() {
        Category category = DataBaseForTest.category1;
        String categoryName = "color";
        try {
            ValidationController.checkCategoryExistence(category.getName());
            Assert.assertEquals(10, 10);
        } catch (CategoryNotExistsException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkCategoryExistence(categoryName);
            Assert.assertEquals(10, 9);
        } catch (CategoryNotExistsException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkRequestExistenceTest() {
        Request request = DataBaseForTest.request1;
        try {
            ValidationController.checkRequestExistence(request.getRequestId());
            Assert.assertEquals(10, 10);
        } catch (RequestNotExistsException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkRequestExistence("12");
            Assert.assertEquals(10, 9);
        } catch (RequestNotExistsException e) {
            Assert.assertEquals(10, 10);
        }

    }

    @Test
    public void checkCodedDiscountExistenceTest() {
        CodedDiscount codedDiscount = DataBaseForTest.codedDiscount1;
        try {
            ValidationController.checkCodedDiscountExistence(codedDiscount.getDiscountCode());
            Assert.assertEquals(10, 10);
        } catch (CodedDiscountNotExistsException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkCodedDiscountExistence("hello");
            Assert.assertEquals(10, 9);
        } catch (CodedDiscountNotExistsException e) {
            Assert.assertEquals(10, 10);
        }

    }

    @Test
    public void checkUsernameExistenceTest() {
        Purchaser purchaser = DataBaseForTest.purchaser1;
        Seller seller = DataBaseForTest.seller2;
        try {
            ValidationController.checkUsernameExistence(purchaser.getUserName());
            ValidationController.checkUsernameExistence(seller.getUserName());
            Assert.assertEquals(10,10);
        } catch (UsernameNotExistsException e) {
            Assert.assertEquals(10,9);
        }
        try {
            ValidationController.checkUsernameExistence("hello");
            Assert.assertEquals(10,9);
        } catch (UsernameNotExistsException e) {
            Assert.assertEquals(10,10);
        }

    }

    @Test
    public void checkProductExistenceTest() {
        Product product = DataBaseForTest.product1;
        try {
            ValidationController.checkProductExistence(product.getProductID());
            Assert.assertEquals(10, 10);
        } catch (ProductIdNotExistsException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkProductExistence("145");
            Assert.assertEquals(10, 9);
        } catch (ProductIdNotExistsException e) {
            Assert.assertEquals(10, 10);
        }

    }

    @Test
    public void checkSortExistenceTest() {
        try {
            ValidationController.checkSortExistence("PRICE");
            Assert.assertEquals(10, 10);
        } catch (SortNotExistsException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkSortExistence("color");
            Assert.assertEquals(10, 9);
        } catch (SortNotExistsException e) {
            Assert.assertEquals(10, 10);
        }

    }

    @Test
    public void checkFilterExistenceTest() {
        ProductsPageController.allFilters.add("color");
        ProductsPageController.allFilters.add("size");
        try {
            ValidationController.checkFilterExistence("size");
            ValidationController.checkFilterExistence("color");
            Assert.assertEquals(10, 10);

        } catch (FilterNotExistsException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkFilterExistence("length");
            Assert.assertEquals(10, 9);
        } catch (FilterNotExistsException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkOfferExistenceTest() {
        Offer offer = DataBaseForTest.offer1;
        try {
            ValidationController.checkOfferExistence(offer.getOfferID());
            Assert.assertEquals(10, 10);
        } catch (ProductIdNotExistsException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkOfferExistence("hy");
            Assert.assertEquals(10, 9);
        } catch (ProductIdNotExistsException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkProductNotExistsInCartTest() {
        Purchaser purchaser = DataBaseForTest.purchaser1;
        Product product = DataBaseForTest.product2;
        HashMap<Product, Integer> allProductsInCart = new HashMap<>();
        allProductsInCart.put(product, 1);
        purchaser.setCart(allProductsInCart);
        try {
            ValidationController.checkProductNotExistsInCart(purchaser, product);
            Assert.assertEquals(10, 10);
        } catch (ProductNotExistsInCartException e) {
            Assert.assertEquals(9, 10);
        }
        try {
            ValidationController.checkProductNotExistsInCart(purchaser, DataBaseForTest.product1);
            Assert.assertEquals(10, 9);
        } catch (ProductNotExistsInCartException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkProductExistsInCart() {
        Purchaser purchaser = DataBaseForTest.purchaser1;
        Product product = DataBaseForTest.product2;
        HashMap<Product, Integer> allProductsInCart = new HashMap<>();
        allProductsInCart.put(product, 1);
        purchaser.setCart(allProductsInCart);
        try {
            ValidationController.checkProductExistsInCart(purchaser, DataBaseForTest.product1);
            Assert.assertEquals(10, 10);
        } catch (ProductAlreadyExistsInCartException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkProductExistsInCart(purchaser, product);
            Assert.assertEquals(10, 9);
        } catch (ProductAlreadyExistsInCartException e) {
            Assert.assertEquals(10, 10);
        }

    }

    @Test
    public void checkSellerOwnsProductTest() {
        Product product = DataBaseForTest.product1;
        Seller seller = DataBaseForTest.seller2;
        ArrayList<Seller> allSellersForProduct = new ArrayList<>();
        allSellersForProduct.add(seller);
        product.setAllSellers(allSellersForProduct);
        try {
            ValidationController.checkSellerOwnsProduct(seller, product);
            Assert.assertEquals(10, 10);
        } catch (SellerUserNameNotExists sellerUserNameNotExists) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkSellerOwnsProduct(DataBaseForTest.seller1, product);
            Assert.assertEquals(10, 9);
        } catch (SellerUserNameNotExists sellerUserNameNotExists) {
            Assert.assertEquals(10, 10);
        }

    }

    @Test
    public void checkPurchaserOwnsCodedDiscountTest() {
        Purchaser purchaser = DataBaseForTest.purchaser1;
        CodedDiscount codedDiscount = DataBaseForTest.codedDiscount1;
        ArrayList<CodedDiscount> allCodedDiscountsForPurchaser = new ArrayList<>();

        allCodedDiscountsForPurchaser.add(codedDiscount);
        purchaser.setAllDiscountCodes(allCodedDiscountsForPurchaser);
        try {
            ValidationController.checkPurchaserOwnsCodedDiscount(purchaser, codedDiscount);
            Assert.assertEquals(10, 10);
        } catch (PurchaserNotOwnsCodedDiscountException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkPurchaserOwnsCodedDiscount(purchaser, DataBaseForTest.codedDiscount2);
            Assert.assertEquals(10, 9);
        } catch (PurchaserNotOwnsCodedDiscountException e) {
            Assert.assertEquals(10, 10);
        }

    }

    @Test
    public void checkOrderExistenceTest() {
        BuyLog buyLog = DataBaseForTest.buyLog1;
        Purchaser purchaser = DataBaseForTest.purchaser1;
        LoginPageController.loggedInAccount = purchaser;
        ArrayList<BuyLog> buyLogHistory = new ArrayList<>();
        buyLogHistory.add(buyLog);
        purchaser.setBuyLogListHistory(buyLogHistory);
        try {
            ValidationController.checkOrderExistence(buyLog.getLogID());
            Assert.assertEquals(10, 10);
        } catch (OrderNotExistsException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkOrderExistence("break");
            Assert.assertEquals(10, 9);
        } catch (OrderNotExistsException e) {
            Assert.assertEquals(10, 10);
        }

    }

    @Test
    public void checkCodedDiscountTimeTest() {
        CodedDiscount codedDiscount = DataBaseForTest.codedDiscount1;
        Date date1 = new Date(124);
        Date date2 = new Date(100);
        try {
            ValidationController.checkCodedDiscountTime(codedDiscount, date2);
            Assert.assertEquals(10, 10);
        } catch (CodedDiscountExpiresException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkCodedDiscountTime(codedDiscount, date1);
            Assert.assertEquals(10, 9);
        } catch (CodedDiscountExpiresException e) {
            Assert.assertEquals(10, 10);
        }

    }

    @Test
    public void checkEnoughMoneyToPayTest() {
        Purchaser purchaser = DataBaseForTest.purchaser2;
        purchaser.setBalance(12.5);
        try {
            ValidationController.checkEnoughMoneyToPay(purchaser, 12.4);
            Assert.assertEquals(10, 10);
        } catch (NotEnoughMoneyToPayException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkEnoughMoneyToPay(purchaser, 50.3);
            Assert.assertEquals(10, 9);
        } catch (NotEnoughMoneyToPayException e) {
            Assert.assertEquals(10, 10);
        }

    }


    @Test
    public void checkOfferTimeTest() {
        Offer offer = DataBaseForTest.offer1;
        Date date1 = new Date(700);
        Date date2 = new Date(1293);
        try {
            ValidationController.checkOfferTime(offer, date1);
            Assert.assertEquals(10, 10);
        } catch (OfferExpiresException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkOfferTime(offer, date2);
            Assert.assertEquals(10, 9);
        } catch (OfferExpiresException e) {
            Assert.assertEquals(10, 10);
        }

    }



   /* @Test
    public void checkTimeTest() {
        Date date = new Date(1000);
        Date secondDate = new Date(2000);
        try {
            ValidationController.checkTime(date);
            Assert.assertEquals(10,10);
        } catch (TimeExpiresException e) {
            Assert.assertEquals(10,9);
        }
        try {
            ValidationController.checkTime(secondDate);
            Assert.assertEquals(10,9);
        } catch (TimeExpiresException e) {
            Assert.assertEquals(10,10);
        }

    }*/

    @Test
    public void checkProductExistenceInCategoryTest() {
        Product product = DataBaseForTest.product1;
        Product secondProduct = DataBaseForTest.product2;
        Category category = DataBaseForTest.category1;
        try {
            ValidationController.checkProductExistenceInCategory(category, secondProduct);
            Assert.assertEquals(10, 10);
        } catch (ProductExistsINCategoryException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkProductExistenceInCategory(category, product);
            Assert.assertEquals(10, 9);
        } catch (ProductExistsINCategoryException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkCategoryAlreadyExistsTest() {
        Category category = DataBaseForTest.category2;
        try {
            ValidationController.checkCategoryAlreadyExists(category);
            Assert.assertEquals(10, 9);
        } catch (CategoryAlreadyExistsException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkOfferFieldExistenceTest() {
        try {
            ValidationController.checkOfferFieldExistence("PERCENTAGE");
            Assert.assertEquals(10, 10);
        } catch (OfferFieldNotExistsException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkOfferFieldExistence("hello");
            Assert.assertEquals(10, 9);
        } catch (OfferFieldNotExistsException e) {
            Assert.assertEquals(10, 10);
        }

    }

    @Test
    public void checkProductFieldExistenceTest() {
        try {
            ValidationController.checkProductFieldExistence("PRICE");
            Assert.assertEquals(10, 10);
        } catch (ProductFieldsNotException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkProductFieldExistence("color");
            Assert.assertEquals(10, 9);

        } catch (ProductFieldsNotException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkPurchaserFieldExistenceTest() {
        try {
            ValidationController.checkPurchaserFieldExistence("FIRST_NAME");
            Assert.assertEquals(10, 10);
        } catch (PurchaserFieldsNotExistException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkPurchaserFieldExistence("Name");
            Assert.assertEquals(10, 9);
        } catch (PurchaserFieldsNotExistException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkSellerFieldExistenceTest() {
        try {
            ValidationController.checkSellerFieldExistence("PASSWORD");
            Assert.assertEquals(10, 10);
        } catch (SellerFieldsNotExistException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkSellerFieldExistence("NAMe");
            Assert.assertEquals(10, 9);
        } catch (SellerFieldsNotExistException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkManagerFieldExistenceTest() {
        try {
            ValidationController.checkManagerFieldExistence("EMAIL");
            Assert.assertEquals(10, 10);
        } catch (ManagerFieldsNotExistException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkManagerFieldExistence("Pass");
            Assert.assertEquals(10, 9);
        } catch (ManagerFieldsNotExistException e) {
            Assert.assertEquals(10, 10);
        }
    }

    @Test
    public void checkCodedDiscountFieldExistenceTest() {
        try {
            ValidationController.checkCodedDiscountFieldExistence("INITIAL_DATE");
            Assert.assertEquals(10, 10);
        } catch (CodedDiscountFieldsNotExistException e) {
            Assert.assertEquals(10, 9);
        }
        try {
            ValidationController.checkCodedDiscountFieldExistence("DATE");
            Assert.assertEquals(10, 9);
        } catch (CodedDiscountFieldsNotExistException e) {
            Assert.assertEquals(10, 10);
        }
    }
}