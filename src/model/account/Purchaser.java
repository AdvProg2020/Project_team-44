package model.account;

import model.product.Product;

import java.util.ArrayList;

public class Purchaser extends Account {
    private ArrayList<Product> cart;

    public Purchaser(String username, String firstName, String secondName, String email, String telephoneNumber, String password) {
        super(username, firstName, secondName, email, telephoneNumber, password);
        this.cart = new ArrayList<>();
    }

    public void rateProduct(int rating, Product product) {

    }

    public void getInfo() {

    }

    public void purchase(Product product) {

    }

    public void compareTwoProducts(Product firstProduct, Product secondProduct) {

    }

    public void watchProducts() {

    }

    public void editInfo() {

    }

    public void filterProducts() {

    }

    public void searchProducts() {

    }
}
