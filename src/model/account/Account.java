package model.account;

import model.buyLog.BuyLog;
import model.CodedDiscount;
import model.sellLog.SellLog;

import java.util.ArrayList;

public abstract class Account {
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String eMail;
    protected String telephoneNumber;
    protected String password;
    protected double balance;
    protected ArrayList<CodedDiscount> allDiscountCodes;
    protected ArrayList<SellLog> sellLogListHistory;
    protected ArrayList<BuyLog> buyLogListHistory;
    private static ArrayList<Account> allAccounts = new ArrayList<>();
    private boolean isLoggedIn;


    public String getUserName() {
        return userName;
    }

    public Account(String username, String firstName, String secondName,
                   String email, String telephoneNumber, String password) {
        this.userName = username;
        this.firstName = firstName;
        this.lastName = secondName;
        this.eMail = email;
        this.telephoneNumber = telephoneNumber;
        this.password = password;
        this.balance = 0;
        this.allDiscountCodes = new ArrayList<>();
        this.sellLogListHistory = new ArrayList<>();
        this.buyLogListHistory = new ArrayList<>();
        allAccounts.add(this);
    }

    public Account getAccountByUsername(String username) {
        for (Account account : allAccounts) {
            if (account.getUserName().equals(username)) {
                return account;
            }
        }
        return null;
    }

    public String getInfo() {
        return "username: " + this.userName + "\n" + "firstName: " + this.firstName + "\n" +
                "lastName: " + this.lastName + "\n" + "eMail: " + this.eMail + "\n" +
                "telephoneNumber: " + this.telephoneNumber;
    }

    public void editInfo() {

    }
}