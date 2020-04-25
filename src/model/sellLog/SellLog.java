package model.sellLog;

import model.product.Product;

import java.util.ArrayList;
import java.util.Date;

public class SellLog {
    private String logID;
    private Date date;
    private double moneyGained;
    private double offerLossMoney;//if offer exists
    private ArrayList<Product> allSellProducts;
    private String buyerName;
    private SellLogStatus status;
    private static ArrayList<SellLog> allLogs;

    public SellLog(String logID, Date date, int moneyGained, int offerLossMoney, ArrayList<Product> allSellProducts, String buyerName) {
        this.logID = logID;
        this.date = date;
        this.moneyGained = moneyGained;
        this.offerLossMoney = offerLossMoney;
        this.allSellProducts = allSellProducts;
        this.buyerName = buyerName;
        allLogs.add(this);
    }
}
