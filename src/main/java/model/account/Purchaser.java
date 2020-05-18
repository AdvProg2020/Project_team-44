package model.account;

import model.Rating;
import model.buyLog.BuyLog;
import model.product.Product;
import model.sellLog.SellLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Purchaser extends Account {
    private HashMap<Product, Integer> cart;
    private String address;
    public Purchaser(String userName, String firstName, String lastName, String eMail, String telephoneNumber, String password, String address) {
        super(userName, firstName, lastName, eMail, telephoneNumber, password);
        this.cart = new HashMap<>();
        this.address = address;
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public ArrayList<String> getInfo() {
        return super.getInfo();
    }

    @Override
    public void editInfo(String field, String newValue) {
        super.editInfo(field, newValue);
    }

    public void rateProduct(int rating, Product product) {
        new Rating(product, this, rating);
    }

    public void purchase(Date date, Seller seller) {
        for (Product product : this.getCart().keySet()) {
            product.getAllPurchaser().add(this);
        }

        new BuyLog(date, this.getCartMoneyToPay(), , this.getCart().keySet(), )

    }

    public ArrayList<String> compareTwoProducts(String firstProductId, String secondProductId) {
        ArrayList<String> info = new ArrayList<>();
        info.add("name:    " + Product.getProductByID(firstProductId).getName() + "    " + Product.getProductByID(secondProductId).getName());
        info.add("companyName:    " + Product.getProductByID(firstProductId).getCompanyName() + "    " + Product.getProductByID(secondProductId).getCompanyName());
        info.add("price:    " + Product.getProductByID(firstProductId).getPrice() + "    " + Product.getProductByID(secondProductId).getPrice());
        info.add("explanationText:    " + Product.getProductByID(firstProductId).getExplanationText() + "    " + Product.getProductByID(secondProductId).getExplanationText());
        info.add("discountPercentage:    " + Product.getProductByID(firstProductId).getOffer().getDiscountPercentage() + "    " + Product.getProductByID(secondProductId).getOffer().getDiscountPercentage());
        info.add("rating:    " + Product.getProductByID(firstProductId).getAverageRating() + "    " + Product.getProductByID(secondProductId).getAverageRating());
        String isAvailableFirst = "No", isAvailableSecond = "No";
        if (Product.getProductByID(firstProductId).isAvailable())
            isAvailableFirst = "Yes";
        if (Product.getProductByID(secondProductId).isAvailable())
            isAvailableSecond = "Yes";
        info.add("isAvailable:    " + isAvailableFirst + "    " + isAvailableSecond);
        info.add("availableNumber:    " + Product.getProductByID(firstProductId).getAvailableNumber() + "    " + Product.getProductByID(secondProductId).getAvailableNumber());
        return info;
    }

    public void watchProducts() {
//
    }


    public void filterProducts() {

    }

    public void searchProducts() {

    }

    public ArrayList<String> getAllBuyLogIds() {
        ArrayList<String> buyLogIds = new ArrayList<>();
        for (BuyLog buyLog : buyLogListHistory) {
            buyLogIds.add(buyLog.getLogID());
        }
        return buyLogIds;
    }

    public ArrayList<String> getCartProductNames() {
        ArrayList<String> productNames = new ArrayList<>();
        for (Product product : this.getCart().keySet()) {
            productNames.add(product.getName());
        }
        return productNames;
    }

    public double getCartMoneyToPay() {
        double money = 0;
        for (Product product : this.getCart().keySet()) {
            money += product.getPrice() * this.getCart().get(product);
        }
        return money;
    }
}
