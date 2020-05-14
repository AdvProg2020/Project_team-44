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
    private static ArrayList<CodedDiscount> allCodedDiscounts = new ArrayList<>();

    public String getDiscountCode() {
        return discountCode;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getMaxAuthorizedPrice() {
        return maxAuthorizedPrice;
    }

    public HashMap<Account, Integer> getRepetitionNumberForAccountEach() {
        return repetitionNumberForAccountEach;
    }

    public ArrayList<Account> getAllAuthorizedUsers() {
        return allAuthorizedUsers;
    }

    public static ArrayList<CodedDiscount> getAllCodedDiscounts() {
        return allCodedDiscounts;
    }

    public CodedDiscount(String discountCode, Date initialDate, Date finalDate, int discountPercentage, int maxAuthorizedPrice) {
        this.discountCode = discountCode;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.discountPercentage = discountPercentage;
        this.maxAuthorizedPrice = maxAuthorizedPrice;
        allCodedDiscounts.add(this);
    }

    public static CodedDiscount getCodedDiscountByCode(String discountCode) {
        for (CodedDiscount codedDiscount : allCodedDiscounts) {
            if (codedDiscount.getDiscountCode().equals(discountCode)) {
                return codedDiscount;
            }
        }
        return null;
    }
}
