package model.account;

import model.offer.Offer;
import model.product.Product;

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
    public ArrayList<String> getInfo() {
        return super.getInfo();
    }

    @Override
    public void editInfo(String field, String newValue) {
        super.editInfo(field, newValue);
    }

    public void editProduct() {

    }

    public void deleteProductRequest() {

    }

    public void addProductRequest() {

    }


    public void editOffers() {

    }

    public void addOfferRequest() {

    }
}
