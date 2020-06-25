package model.requests;


import model.account.Seller;
import model.offer.Offer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RequestForEditOff extends Request {
    private Seller seller;
    private Offer offer;
    private String field;
    private ArrayList<String> newValue;
    private static ArrayList<RequestForEditOff> allRequestsForEditOff = new ArrayList<>();

    public RequestForEditOff(Seller seller, Offer offer, String field, ArrayList<String> newValue) {
        this.seller = seller;
        this.offer = offer;
        this.field = field;
        this.newValue = newValue;
        allRequestsForEditOff.add(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public Offer getOffer() {
        return offer;
    }

    public String getField() {
        return field;
    }

    public ArrayList<String> getNewValue() {
        return newValue;
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
        String strDate = dateFormat.format(this.getOffer().getInitialDate());
        details.add(this.getOffer().getOfferID());
        details.add(strDate);
        strDate = dateFormat.format(this.getOffer().getFinalDate());
        details.add(strDate);
        details.add(this.getField());
        details.addAll(this.getNewValue());
        return details;
    }
}
