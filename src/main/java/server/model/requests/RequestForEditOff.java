package server.model.requests;


import server.model.account.Seller;
import server.model.offer.Offer;
import server.model.product.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RequestForEditOff extends Request {
    private Seller seller;
    private Offer offer;
    private ArrayList<Product> productList;
    private Date initialDate;
    private Date finalDate;
    private int discountPercentage;
    private static ArrayList<RequestForEditOff> allRequestsForEditOff = new ArrayList<>();

    public RequestForEditOff(Seller seller, Offer offer, ArrayList<Product> productList, Date initialDate, Date finalDate, int discountPercentage) {
        this.seller = seller;
        this.offer = offer;
        this.productList = productList;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.discountPercentage = discountPercentage;
        allRequestsForEditOff.add(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public Offer getOffer() {
        return offer;
    }

    public ArrayList<Product> getProductList() {
        return productList;
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

    public static ArrayList<RequestForEditOff> getAllRequestsForEditOff() {
        return allRequestsForEditOff;
    }

    @Override
    public String produceRequestId() {
        String id = "RequestForEditOff_" + super.produceRequestId();
        return id;
    }

    @Override
    public ArrayList<String> getRequestDetails() {
        ArrayList<String> details = super.getRequestDetails();
        details.add(this.getSeller().getFirstName());
        details.add(this.getSeller().getLastName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(this.getInitialDate());
        details.add(this.getOffer().getOfferID());
        details.add(strDate);
        strDate = dateFormat.format(this.getFinalDate());
        details.add(strDate);
        details.add(String.valueOf(this.getDiscountPercentage()));
        return details;
    }
}
