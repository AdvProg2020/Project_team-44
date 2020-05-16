package model.requests;

import java.util.ArrayList;
import java.util.Random;

public class Request {
    private int requestId;
    private RequestStatus status;
    private static ArrayList<Request> allRequests = new ArrayList<>();

    public Request(int requestId) {
        this.requestId = requestId;
        this.status = RequestStatus.IN_PROGRESS;
        allRequests.add(this);
    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    private int produceRequestId() {
        Random random = new Random();
        int min = 0;
        int max = 100000000;
        int range = max - min;
        int rand = random.nextInt(range) + min;
        return rand;
    }

    private Request getRequestById(int requestId) {
        for (Request allRequest : allRequests) {
            if (allRequest.getRequestId() == requestId)
                return allRequest;
        }
        return null;
    }
}
