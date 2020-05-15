package model.account;

import model.product.Product;

import java.util.ArrayList;

public class Purchaser extends Account {
    private ArrayList<Product> cart;

    public Purchaser(String username, String firstName, String secondName, String email, String telephoneNumber, String password) {
        super(username, firstName, secondName, email, telephoneNumber, password);
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


    public String purchase(Product product , String input) {
        return null;
    }

    public void compareTwoProducts(Product firstProduct, Product secondProduct) {

    }

    public void watchProducts() {

    }


    public void filterProducts() {

    }

    public void searchProducts() {

    }
}
