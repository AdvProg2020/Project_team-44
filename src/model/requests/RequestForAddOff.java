package model.requests;

import model.account.Seller;
import model.offer.Offer;

import java.util.ArrayList;

public class RequestForAddOff extends Request {
    private Seller seller;
    private Offer addedOff;
    private static ArrayList<RequestForAddOff> allRequestsForAddOff = new ArrayList<>();

    public RequestForAddOff(Seller seller, Offer addedOff, int requestId) {
        super(requestId);
        this.seller = seller;
        this.addedOff = addedOff;
        allRequestsForAddOff.add(this);
    }

}