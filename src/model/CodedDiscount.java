package model;

import model.account.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CodedDiscount {

    private String discountCode;
    private Date initialDate;
    private Date finalDate;
    private double discountPercentage;
    private double maxAuthorizedPrice;
    private HashMap<Account, Integer> repetitionNumberForAccountEach = new HashMap<>();
    private ArrayList<Account> allAuthorizedUsers = new ArrayList<>();

    public CodedDiscount(String discountCode, Date initialDate, Date finalDate, int discountPercentage, int maxAuthorizedPrice) {
        this.discountCode = discountCode;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.discountPercentage = discountPercentage;
        this.maxAuthorizedPrice = maxAuthorizedPrice;
    }
}
