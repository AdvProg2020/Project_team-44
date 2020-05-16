package model.requests;


import model.account.Seller;
import model.offer.Offer;

import java.util.ArrayList;

public class RequestForEditOff extends Request {
    private Seller seller;
    private Offer pastOff;
    private Offer newOff;
    private static ArrayList<RequestForEditOff> allRequestsForEditOff = new ArrayList<>();

    public RequestForEditOff(Seller seller, Offer pastOff, Offer newOff, int requestId) {
        super(requestId);
        this.seller = seller;
        this.pastOff = pastOff;
        this.newOff = newOff;
        allRequestsForEditOff.add(this);
    }
}
