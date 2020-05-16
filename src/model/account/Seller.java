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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public ArrayList<String> getInfo() {
        ArrayList<String> info = new ArrayList<>();
        info = super.getInfo();
        info.add(this.getCompanyName());
        return info;
    }

    @Override
    public void editInfo(String field, String newValue) {
        super.editInfo(field, newValue);
        if (field.equalsIgnoreCase("companyName")){
            this.setCompanyName(newValue);
        }
    }

    public void editProduct() {

    }

    public void deleteProductRequest(String productId) {
        Product requestedProduct = null;
        for (Product product : productsToSell) {
            if (product.getProductID().equalsIgnoreCase(productId))
                requestedProduct = product;
        }
        productsToSell.remove(requestedProduct);
    }

    public void addProductRequest() {

    }


    public void editOffers() {

    }

    public void addOfferRequest() {

    }
}
