package model.buyLog;

import model.product.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BuyLog {
    private String logID;
    private Date date;
    private double moneyPaid;
    private double discountCodeAmountUsed;
    private ArrayList<Product> allPurchasedProducts;
    private String sellerFirstName;
    private String sellerLastName;
    private BuyLogStatus status;
    private static ArrayList<BuyLog> allBuyLogs;

    public BuyLog(String logID, Date date, int moneyPaid, int discountCodeAmountUsed, ArrayList<Product> allPurchasedProducts, String sellerFirstName, String sellerLastName) {
        this.logID = logID;
        this.date = date;
        this.moneyPaid = moneyPaid;
        this.discountCodeAmountUsed = discountCodeAmountUsed;
        this.allPurchasedProducts = allPurchasedProducts;
        this.sellerFirstName = sellerFirstName;
        this.sellerLastName = sellerLastName;
        allBuyLogs.add(this);
    }

    public String getLogID() {
        return logID;
    }

    public Date getDate() {
        return date;
    }

    public double getMoneyPaid() {
        return moneyPaid;
    }

    public double getDiscountCodeAmountUsed() {
        return discountCodeAmountUsed;
    }

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public BuyLogStatus getStatus() {
        return status;
    }

    public static ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }

    public static BuyLog getBuyLogById(String logId) {
        for (BuyLog allBuyLog : allBuyLogs) {
            if (allBuyLog.getLogID().equals(logId)) ;
            return allBuyLog;
        }
        return null;
    }

    public ArrayList<String> getInfo() {
        ArrayList<String> info = new ArrayList<>();
        info.add(this.getLogID());
        Date date = this.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        info.add(strDate);
        info.add(String.valueOf(this.getMoneyPaid()));
        info.add(String.valueOf(this.getDiscountCodeAmountUsed()));
        info.add(this.getSellerFirstName());
        info.add(this.getSellerLastName());
        info.add(this.getStatus().toString());
        return info;
    }
}
