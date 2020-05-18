package model.requests;

import java.util.ArrayList;
import java.util.Random;

public class Request {
    protected String requestId;
    protected RequestStatus status = RequestStatus.IN_PROGRESS;
    protected static ArrayList<Request> allRequests = new ArrayList<>();

    public Request() {
        this.requestId = produceRequestId();
        allRequests.add(this);
    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public String getRequestId() {
        return requestId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String produceRequestId() {
        Random random = new Random();
        int min = 0;
        int max = 100000000;
        int range = max - min;
        int rand = random.nextInt(range) + min;
        return String.valueOf(rand);
    }

    public static Request getRequestById(String requestId) {
        for (Request allRequest : allRequests) {
            if (allRequest.getRequestId().equals(requestId))
                return allRequest;
        }
        return null;
    }

    public ArrayList<String> getRequestDetails() {
        ArrayList<String> details = new ArrayList<>();
        details.add(this.getStatus().toString());
        return details;
    }

    public static ArrayList<String> getAllRequestsId(){
        ArrayList<String> requestsId = new ArrayList<>();
        for (Request allRequest : allRequests) {
            requestsId.add(allRequest.getRequestId());
        }
        return requestsId;
    }
}
