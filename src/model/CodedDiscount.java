package model;

import model.account.Account;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setMaxAuthorizedPrice(double maxAuthorizedPrice) {
        this.maxAuthorizedPrice = maxAuthorizedPrice;
    }

    public static CodedDiscount getCodedDiscountByCode(String discountCode) {
        for (CodedDiscount codedDiscount : allCodedDiscounts) {
            if (codedDiscount.getDiscountCode().equals(discountCode)) {
                return codedDiscount;
            }
        }
        return null;
    }

    public ArrayList<String> getCodedDiscountInfo() {
        ArrayList<String> info = new ArrayList<>();
        Date date = this.getInitialDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        info.add(this.getDiscountCode());
        info.add(strDate);
        date = this.getFinalDate();
        strDate = dateFormat.format(date);
        info.add(strDate);
        info.add(String.valueOf(this.getDiscountPercentage()));
        info.add(String.valueOf(this.getMaxAuthorizedPrice()));
        return info;
    }
    public static ArrayList<String> getAllDiscountCodes(){
        ArrayList<String> allDiscountCode = new ArrayList<>();
        for (CodedDiscount allCodedDiscount : allCodedDiscounts) {
            allDiscountCode.add(allCodedDiscount.getDiscountCode());
        }
        return allDiscountCode;
    }
}
