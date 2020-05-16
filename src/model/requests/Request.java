package model.requests;

import java.util.ArrayList;
import java.util.Random;

public class Request {
    protected int requestId;
    protected RequestStatus status;
    protected static ArrayList<Request> allRequests = new ArrayList<>();

    public Request() {
        this.requestId = produceRequestId();
        this.status = RequestStatus.IN_PROGRESS;
        allRequests.add(this);
    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public int getRequestId() {
        return requestId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int produceRequestId() {
        Random random = new Random();
        int min = 0;
        int max = 100000000;
        int range = max - min;
        int rand = random.nextInt(range) + min;
        return rand;
    }

    public static Request getRequestById(int requestId) {
        for (Request allRequest : allRequests) {
            if (allRequest.getRequestId() == requestId)
                return allRequest;
        }
        return null;
    }
    public ArrayList<String> getRequestDetails(){
        ArrayList<String> details = new ArrayList<>();
        details.add(this.getStatus().toString());
        return details;
    }

}
