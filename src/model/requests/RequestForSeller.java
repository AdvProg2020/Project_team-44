package model.requests;

import java.util.ArrayList;

public class RequestForSeller extends Request {
    private String companyName;
    private String userName;
    private String firstName;
    private String lastName;
    private String eMail;
    private String telephoneNumber;
    private String password;

    private ArrayList<RequestForSeller> allRequestsForSeller = new ArrayList<>();

    public RequestForSeller(String companyName, String userName, String firstName
            , String lastName, String eMail, String telephoneNumber, String password) {
        this.companyName = companyName;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.telephoneNumber = telephoneNumber;
        this.password = password;
        allRequestsForSeller.add(this);
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEMail() {
        return eMail;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public ArrayList<String> getRequestDetails() {
        ArrayList<String> details = super.getRequestDetails();
        details.add(this.getCompanyName());
        details.add(this.getUserName());
        details.add(this.getFirstName());
        details.add(this.getLastName());
        details.add(this.getEMail());
        details.add(this.getTelephoneNumber());
        details.add(this.getPassword());
        return details;
    }
}
