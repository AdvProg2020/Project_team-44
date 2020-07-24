package server.model.account;

import java.util.ArrayList;

public class Supporter extends Account {
    private static ArrayList<Supporter> allSupporters = new ArrayList<>();

    public Supporter(String userName, String firstName, String lastName, String eMail, String telephoneNumber, String password) {
        super(userName, firstName, lastName, eMail, telephoneNumber, password);
        allSupporters.add(this);
    }

    public static ArrayList<Supporter> getAllSupporters() {
        return allSupporters;
    }
}