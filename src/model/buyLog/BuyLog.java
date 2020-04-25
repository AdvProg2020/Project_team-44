package model.buyLog;

import model.product.Product;

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
    private static ArrayList<BuyLog> allLogs;

    public BuyLog(String logID, Date date, int moneyPaid, int discountCodeAmountUsed, ArrayList<Product> allPurchasedProducts, String sellerName) {
        this.logID = logID;
        this.date = date;
        this.moneyPaid = moneyPaid;
        this.discountCodeAmountUsed = discountCodeAmountUsed;
        this.allPurchasedProducts = allPurchasedProducts;
        this.sellerName = sellerName;
        allLogs.add(this);
    }

}
