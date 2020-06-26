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
    protected static ArrayList<Account> allAccounts = new ArrayList<>();
    protected boolean isLoggedIn;

    public Account(String userName, String firstName, String lastName,
                   String eMail, String telephoneNumber, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.telephoneNumber = telephoneNumber;
        this.password = password;
        this.balance = 0;
        this.allDiscountCodes = new ArrayList<>();
        this.sellLogListHistory = new ArrayList<>();
        this.buyLogListHistory = new ArrayList<>();
        allAccounts.add(this);
    }
    // just for test
    public void setSellLogListHistory(ArrayList<SellLog> sellLogListHistory) {
        this.sellLogListHistory = sellLogListHistory;
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

    public double getBalance() {
        return balance;
    }

    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAllDiscountCodes(ArrayList<CodedDiscount> allDiscountCodes) {
        this.allDiscountCodes = allDiscountCodes;
    }

    public void setBuyLogListHistory(ArrayList<BuyLog> buyLogListHistory) {
        this.buyLogListHistory = buyLogListHistory;
    }

    public static void setAllAccounts(ArrayList<Account> allAccounts) {
        Account.allAccounts = allAccounts;
    }

    public static Account getAccountByUsername(String username) {
        for (Account account : allAccounts) {
            if (account.getUserName().equals(username)) {
                return account;
            }
        }
        return null;
    }

    public ArrayList<String> getInfo() {
        ArrayList<String> info = new ArrayList<>();
        info.add(this.getUserName());
        info.add(this.getFirstName());
        info.add(this.getLastName());
        info.add(this.getEMail());
        info.add(this.getTelephoneNumber());
        return info;
    }

    public void editInfo(String field, String newValue) {
        if (field.equalsIgnoreCase("firstName")) {
            this.setFirstName(newValue);
        } else if (field.equalsIgnoreCase("lastName")) {
            this.setLastName(newValue);
        } else if (field.equalsIgnoreCase("EMail")) {
            this.setEMail(newValue);
        } else if (field.equalsIgnoreCase("telephoneNumber")) {
            this.setTelephoneNumber(newValue);
        } else if (field.equalsIgnoreCase("password")) {
            this.setPassword(newValue);
        }
    }
}
