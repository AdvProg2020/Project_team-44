package model.account;

import model.offer.Offer;
import model.product.Product;
import model.requests.*;

import java.util.ArrayList;

public class Seller extends Account {
    private String companyName;
    private ArrayList<Product> productsToSell;
    private ArrayList<Offer> offersList;

    public Seller(String username, String firstName, String lastName, String email, String telephoneNumber, String password, String companyName) {
        super(username, firstName, lastName, email, telephoneNumber, password);
        this.companyName = companyName;
        this.productsToSell = new ArrayList<>();
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" + "companyName: " + this.companyName;
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
