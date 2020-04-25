package model.account;

import model.buyLog.BuyLog;
import model.CodedDiscount;
import model.sellLog.SellLog;

import java.util.ArrayList;

public abstract class Account {
    protected String username;
    protected String firstName;
    protected String secondName;
    protected String email;
    protected String telephoneNumber;
    protected String password;
    protected double balance;
    protected ArrayList<CodedDiscount> allDiscountCodes;
    protected ArrayList<SellLog> sellLogListHistory;
    protected ArrayList<BuyLog> buyLogListHistory;
    private static ArrayList<Account> allAccounts = new ArrayList<>();
    private boolean isLoggedIn;

    public String getUsername() {
        return username;
    }

    public Account(String username, String firstName, String secondName,
                   String email, String telephoneNumber, String password) {
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
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
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }
}
