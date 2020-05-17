package model.account;

import model.Category;
import model.buyLog.BuyLog;
import model.offer.Offer;
import model.product.Product;
import model.requests.RequestForAddOff;
import model.requests.RequestForAddProduct;
import model.requests.RequestForEditOff;
import model.requests.RequestForEditProduct;

import java.util.ArrayList;
import java.util.Date;

public class Seller extends Account {
    private String companyName;
    private String companyAddress;
    private String companyTelephone;
    private ArrayList<Product> productsToSell;
    private ArrayList<Offer> offersList;

    public Seller(String userName, String firstName, String lastName, String eMail, String telephoneNumber, String password, String companyName, String companyAddress, String companyTelephone) {
        super(userName, firstName, lastName, eMail, telephoneNumber, password);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyTelephone = companyTelephone;
        this.productsToSell = new ArrayList<>();
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getCompanyTelephone() {
        return companyTelephone;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    public ArrayList<Product> getProductsToSell() {
        return productsToSell;
    }

    public ArrayList<Offer> getOffersList() {
        return offersList;
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
        if (field.equalsIgnoreCase("companyName")) {
            this.setCompanyName(newValue);
        }
    }

    public void deleteProductRequest(String productId) {
        Product requestedProduct = null;
        for (Product product : productsToSell) {
            if (product.getProductID().equalsIgnoreCase(productId))
                requestedProduct = product;
        }
        productsToSell.remove(requestedProduct);
    }

    public void addProductRequest(Seller seller, Category category, String name, String companyName, int price, String explanationText) {
        new RequestForAddProduct(seller, category, name, companyName, price, explanationText);
    }


    public void editProductRequest(Seller seller, Product product, String field, String oldValue, String newValue) {
        new RequestForEditProduct(seller, product, field, oldValue, newValue);
    }

    public void editOffersRequest(Seller seller, Offer offer, String field, String oldValue, String newValue) {
        new RequestForEditOff(seller, offer, field, oldValue, newValue);
    }

    public void addOfferRequest(Seller seller, ArrayList<Product> productList, Date initialDate, Date finalDate, int discountPercentage) {
        new RequestForAddOff(seller, productList, initialDate, finalDate, discountPercentage);
    }

    public ArrayList<String> getCompanyInfo() {
        ArrayList<String> companyInfo = new ArrayList<>();
        companyInfo.add(this.getCompanyName());
        companyInfo.add(this.getCompanyAddress());
        companyInfo.add(this.getCompanyTelephone());
        return companyInfo;
    }

    public void editCompanyInfo(String field, String newValue){
        if (field.equalsIgnoreCase("companyName")){
            this.setCompanyName(newValue);
        }else if (field.equalsIgnoreCase("companyAddress")){
            this.setCompanyAddress(newValue);
        }else if (field.equalsIgnoreCase("companyTelephone")){
            this.setCompanyTelephone(newValue);
        }
    }

    public ArrayList<String> getAllSellLogIds() {
        ArrayList<String> sellLogIds = new ArrayList<>();
        for (BuyLog allBuyLog : BuyLog.getAllBuyLogs()) {
            if (allBuyLog.getSellerFirstName().equalsIgnoreCase(this.getFirstName()) && allBuyLog.getSellerLastName().equalsIgnoreCase(this.getLastName())) {
                sellLogIds.add(allBuyLog.getLogID());
            }
        }
        return sellLogIds;
    }

    public ArrayList<String> getProductsToSellIds() {
        ArrayList<String> productIds = new ArrayList<>();
        for (Product product : this.getProductsToSell()) {
            productIds.add(product.getProductID());
        }
        return productIds;
    }

    public ArrayList<String> getOfferListIds() {
        ArrayList<String> offerIds = new ArrayList<>();
        for (Offer offer : this.getOffersList()) {
            offerIds.add(offer.getOfferID());
        }
        return offerIds;
    }
}
