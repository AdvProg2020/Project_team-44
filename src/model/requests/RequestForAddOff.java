package model.requests;

import model.account.Seller;
import model.offer.Offer;

import java.util.ArrayList;

public class RequestForAddOff {
    private Seller seller;
    private Offer addedOff;
    private RequestStatus status;
    private static ArrayList<RequestForAddOff> allRequestsForAddOff = new ArrayList<>();

    public RequestForAddOff(Seller seller, Offer addedOff) {
        this.seller = seller;
        this.addedOff = addedOff;
        this.status = RequestStatus.IN_PROGRESS;
        allRequestsForAddOff.add(this);
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
