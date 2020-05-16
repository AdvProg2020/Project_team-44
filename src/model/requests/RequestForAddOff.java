package model.requests;

import model.account.Seller;
import model.offer.Offer;
import model.product.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RequestForAddOff extends Request {
    private Seller seller;
    private String offerID;
    private ArrayList<Product> productList;
    private Date initialDate;
    private Date finalDate;
    private int discountPercentage;
    private static ArrayList<RequestForAddOff> allRequestsForAddOff = new ArrayList<>();

    public RequestForAddOff(Seller seller, String offerID, ArrayList<Product> productList, Date initialDate, Date finalDate, int discountPercentage, int requestId) {
        super(requestId);
        this.seller = seller;
        this.offerID = offerID;
        this.productList = productList;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.discountPercentage = discountPercentage;
        allRequestsForAddOff.add(this);
    }

    public String getOfferID() {
        return offerID;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    @Override
    public ArrayList<String> getRequestDetails(int requestId) {
        ArrayList<String> details = new ArrayList<>();
        details = super.getRequestDetails(requestId);
        details.add(seller.getFirstName());
        details.add(seller.getLastName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(this.getInitialDate());
        details.add(this.getOfferID());
        details.add(strDate);
        strDate = dateFormat.format(this.getFinalDate());
        details.add(strDate);
        details.add(String.valueOf(this.getDiscountPercentage()));
        return details;
    }
}