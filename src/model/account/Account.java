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
    protected static ArrayList<Account> allAccounts = new ArrayList<>();
    protected boolean isLoggedIn;
    protected String type;

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
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

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<CodedDiscount> getAllDiscountCodes() {
        return allDiscountCodes;
    }

    public ArrayList<SellLog> getSellLogListHistory() {
        return sellLogListHistory;
    }

    public ArrayList<BuyLog> getBuyLogListHistory() {
        return buyLogListHistory;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public static Account getAccountByUsername(String username) {
        for (Account account : allAccounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }
    public ArrayList<String> getInfo() {
        return null;
    }

    public void editInfo(String field, String newValue) {

    }
}
