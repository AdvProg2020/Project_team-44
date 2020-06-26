package model.account;

import com.google.gson.Gson;
import model.Category;
import model.offer.Offer;
import model.product.Product;
import model.requests.RequestForAddOff;
import model.requests.RequestForAddProduct;
import model.requests.RequestForEditOff;
import model.requests.RequestForEditProduct;
import model.sellLog.SellLog;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Seller extends Account {
    private String companyName;
    private String companyAddress;
    private String companyTelephone;
    private HashMap<Product, Integer> productsToSell;
    private ArrayList<Offer> offersList;
    private static ArrayList<Seller> allSeller = new ArrayList<>();

    public Seller(String userName, String firstName, String lastName, String eMail, String telephoneNumber, String password, String companyName, String companyAddress, String companyTelephone) {
        super(userName, firstName, lastName, eMail, telephoneNumber, password);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyTelephone = companyTelephone;
        this.productsToSell = new HashMap<>();
        allSeller.add(this);
//        createAndUpdateJson();
    }

    public static ArrayList<Seller> getAllSeller() {
        return allSeller;
    }

    public static void setAllSeller(ArrayList<Seller> allSeller) {
        Seller.allSeller = allSeller;
    }

    public void createAndUpdateJson() {
        try {
            Writer writer = new FileWriter("src/main/resources/Accounts/Sellers/" + this.getUserName() + ".json");
            new Gson().toJson(this, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public HashMap<Product, Integer> getProductsToSell() {
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
        for (Product product : productsToSell.keySet()) {
            if (product.getProductID().equals(productId))
                requestedProduct = product;
        }
        productsToSell.remove(requestedProduct);
    }

    public void addProductRequest(Category category, String name, int price, String explanationText) {
        new RequestForAddProduct(this, category, name, price, explanationText);
    }


    public void editProductRequest(Product product, String field, String newValue) {
        new RequestForEditProduct(this, product, field, newValue);
    }

    public void editOffersRequest(Offer offer, String field, ArrayList<String> newValue) {
        new RequestForEditOff(this, offer, field, newValue);
    }

    public void addOfferRequest(ArrayList<Product> productList, Date initialDate, Date finalDate, int discountPercentage) {
        new RequestForAddOff(this, productList, initialDate, finalDate, discountPercentage);
    }

    public ArrayList<String> getCompanyInfo() {
        ArrayList<String> companyInfo = new ArrayList<>();
        companyInfo.add(this.getCompanyName());
        companyInfo.add(this.getCompanyAddress());
        companyInfo.add(this.getCompanyTelephone());
        return companyInfo;
    }

    public void editCompanyInfo(String field, String newValue) {
        if (field.equalsIgnoreCase("companyName")) {
            this.setCompanyName(newValue);
        } else if (field.equalsIgnoreCase("companyAddress")) {
            this.setCompanyAddress(newValue);
        } else if (field.equalsIgnoreCase("companyTelephone")) {
            this.setCompanyTelephone(newValue);
        }
    }

    public ArrayList<String> getAllSellLogIds() {
        ArrayList<String> sellLogIds = new ArrayList<>();
        for (SellLog sellLog : sellLogListHistory) {
            sellLogIds.add(sellLog.getLogID());
        }
        return sellLogIds;
    }

    public ArrayList<String> getProductsToSellIds() {
        ArrayList<String> productIds = new ArrayList<>();
        for (Product product : this.getProductsToSell().keySet()) {
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
