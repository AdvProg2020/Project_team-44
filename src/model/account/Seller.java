package model.account;

import model.offer.Offer;
import model.product.Product;
import model.requests.*;

import java.util.ArrayList;

public class Seller extends Account {
    private String companyName;
    private ArrayList<Product> productsToSell;
    private ArrayList<Offer> offersList;

    public Seller(String username, String firstName, String secondName, String email, String telephoneNumber, String password, String companyName) {
        super(username, firstName, secondName, email, telephoneNumber, password);
        this.companyName = companyName;
        this.productsToSell = new ArrayList<>();
    }

    public void getInfo() {

    }

    public void editProduct() {

    }

    public void deleteProductRequest() {

    }

    public void addProductRequest() {

    }

    public void editInfo() {

    }

    public void editOffers() {

    }

    public void addOfferRequest() {

    }
}
