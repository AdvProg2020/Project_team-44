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
    private static ArrayList<Offer> allOffers = new ArrayList<>();

    public Offer(String offerID, ArrayList<Product> productList, Date initialDate, Date finalDate, int discountPercentage) {
        this.offerID = offerID;
        this.productList = productList;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.discountPercentage = discountPercentage;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public static ArrayList<Offer> getAllOffers() {
        return allOffers;
    }

    public String getOfferID() {
        return offerID;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public static ArrayList<String> getAllOffersId(){
        ArrayList<String> offersId = new ArrayList<>();
        for (Offer allOffer : allOffers) {
            offersId.add(allOffer.getOfferID());
        }
        return offersId;
    }
}
