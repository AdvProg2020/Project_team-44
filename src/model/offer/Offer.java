package model.offer;

import model.product.Product;

import java.util.ArrayList;
import java.util.Date;

public class Offer {
    private String offerID;
    private ArrayList<Product> productList;
    private OfferStatus status;
    private Date initialDate;
    private Date finalDate;
    private int discountPercentage;
    private ArrayList<Offer> allOffers = new ArrayList<>();

    public Offer(String offerID, ArrayList<Product> productList, Date initialDate, Date finalDate, int discountPercentage) {
        this.offerID = offerID;
        this.productList = productList;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.discountPercentage = discountPercentage;
    }
}
