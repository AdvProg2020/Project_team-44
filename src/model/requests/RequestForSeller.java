package model.requests;

import model.account.Purchaser;

import java.util.ArrayList;

public class RequestForSeller extends Request {
    private String companyName;
    private String username;
    private String firstName;
    private String secondName;
    private String email;
    private String telephoneNumber;
    private String password;

    private ArrayList<RequestForSeller> allRequestsForSeller = new ArrayList<>();

    public RequestForSeller(String companyName, String username, String firstName
            , String secondName, String email, String telephoneNumber, String password, int requestId) {
        super(requestId);
        this.companyName = companyName;
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.password = password;
        allRequestsForSeller.add(this);
    }
}
