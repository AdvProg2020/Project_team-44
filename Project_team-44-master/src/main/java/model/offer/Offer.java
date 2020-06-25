package model.offer;

import model.product.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Offer {
    private String offerID;
    private ArrayList<Product> productList;
    private OfferStatus status;
    private Date initialDate;
    private Date finalDate;
    private int discountPercentage;
    private static ArrayList<Offer> allOffers = new ArrayList<>();

    public Offer(ArrayList<Product> productList, Date initialDate, Date finalDate, int discountPercentage) {
        this.offerID = produceOfferId();
        this.productList = productList;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.discountPercentage = discountPercentage;
        allOffers.add(this);
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

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public static ArrayList<String> getAllOffersId() {
        ArrayList<String> offersId = new ArrayList<>();
        for (Offer allOffer : allOffers) {
            offersId.add(allOffer.getOfferID());
        }
        return offersId;
    }

    public static Offer getOfferById(String id) {
        for (Offer allOffer : allOffers) {
            if (allOffer.getOfferID().equals(id))
                return allOffer;
        }
        return null;
    }

    public ArrayList<String> getOfferInfo() {
        ArrayList<String> info = new ArrayList<>();
        info.add(this.getOfferID());
        info.add(String.valueOf(this.getDiscountPercentage()));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(this.getInitialDate());
        info.add(strDate);
        strDate = dateFormat.format(this.getFinalDate());
        info.add(strDate);
        return info;
    }
    public String produceOfferId() {
        String logId = "Offer";
        Random random = new Random();
        int min = 4;
        int max = 100000000;
        int range = max - min;
        int rand = random.nextInt(range) + min;
        logId += rand;
        return logId;
    }
}
