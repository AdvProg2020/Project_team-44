package model.requests;


import model.account.Seller;
import model.offer.Offer;

import java.util.ArrayList;

public class RequestForEditOff {
    private Seller seller;
    private Offer pastOff;
    private Offer newOff;
    private RequestStatus status;
    private static ArrayList<RequestForEditOff> allRequestsForEditOff = new ArrayList<>();

    public RequestForEditOff(Seller seller, Offer pastOff, Offer newOff) {
        this.seller = seller;
        this.pastOff = pastOff;
        this.newOff = newOff;
        this.status = RequestStatus.IN_PROGRESS;
        allRequestsForEditOff.add(this);
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
