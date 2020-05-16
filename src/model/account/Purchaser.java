package model.account;

import model.buyLog.BuyLog;
import model.product.Product;

import java.util.ArrayList;

public class Purchaser extends Account {
    private ArrayList<Product> cart;

    public Purchaser(String userName, String firstName, String lastName, String eMail, String telephoneNumber, String password) {
        super(userName, firstName, lastName, eMail, telephoneNumber, password);
        this.cart = new ArrayList<>();
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

    }


    public void purchase(Product product) {

    }

    public void compareTwoProducts(String firstProductId, String secondProductId) {

    }

    public void watchProducts() {

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
}
