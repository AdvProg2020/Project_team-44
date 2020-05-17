package model.account;

import model.Rating;
import model.buyLog.BuyLog;
import model.product.Product;
import model.sellLog.SellLog;

import java.util.ArrayList;
import java.util.HashMap;

public class Purchaser extends Account {
    private HashMap<Product, Integer> cart;

    public Purchaser(String userName, String firstName, String lastName, String eMail, String telephoneNumber, String password) {
        super(userName, firstName, lastName, eMail, telephoneNumber, password);
        this.cart = new HashMap<>();
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
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

    public void purchase(Product product) {

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

    public ArrayList<String> returnBuyLogIds() {
        ArrayList<String> buyLogId = new ArrayList<>();
        for (BuyLog buyLog : buyLogListHistory) {
            buyLogId.add(buyLog.getLogID());
        }
        return buyLogId;
    }

    public ArrayList<String> getAllBuyLogIds() {
        ArrayList<String> buyLogIds = new ArrayList<>();
        for (SellLog allSellLog : SellLog.getAllSellLogs()) {
            if (allSellLog.getBuyerFirstName().equalsIgnoreCase(this.getFirstName()) && allSellLog.getBuyerLastName().equalsIgnoreCase(this.getLastName())) {
                buyLogIds.add(allSellLog.getLogID());
            }
        }
        return buyLogIds;
    }

    public ArrayList<String> getCartProducts() {
        ArrayList<String> productNames = new ArrayList<>();
        for (Product product : this.getCart().keySet()) {
            productNames.add(product.getName());
        }
        return productNames;
    }
}
