package model.requests;

import model.account.Purchaser;

import java.util.ArrayList;

public class RequestForSeller {
    private Purchaser purchaser;
    private RequestStatus status;
    private ArrayList<RequestForSeller> allRequestsForSeller = new ArrayList<>();

    public RequestForSeller(Purchaser purchaser, RequestStatus status) {
        this.purchaser = purchaser;
        this.status = status;
        allRequestsForSeller.add(this);
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
