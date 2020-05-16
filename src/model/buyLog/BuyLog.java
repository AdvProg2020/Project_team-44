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
    private String sellerName;
    private BuyLogStatus status;
    private static ArrayList<BuyLog> allBuyLogs;

    public BuyLog(String logID, Date date, int moneyPaid, int discountCodeAmountUsed, ArrayList<Product> allPurchasedProducts, String sellerName) {
        this.logID = logID;
        this.date = date;
        this.moneyPaid = moneyPaid;
        this.discountCodeAmountUsed = discountCodeAmountUsed;
        this.allPurchasedProducts = allPurchasedProducts;
        this.sellerName = sellerName;
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

    public String getSellerName() {
        return sellerName;
    }

    public BuyLogStatus getStatus() {
        return status;
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
        info.add(this.getSellerName());
        info.add(this.getStatus().toString());
        return info;
    }
}
