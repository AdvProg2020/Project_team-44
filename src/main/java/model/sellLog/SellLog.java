package model.sellLog;

import model.product.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class SellLog {
    private String logID;
    private Date date;
    private double moneyGained;
    private double offerLossMoney;//if offer exists
    private ArrayList<Product> allSellProducts;
    private String buyerFirstName;
    private String buyerLastName;
    private SellLogStatus status = SellLogStatus.IN_PROGRESS;
    private static ArrayList<SellLog> allSellLogs = new ArrayList<>();

    public SellLog(Date date, double moneyGained, double offerLossMoney, ArrayList<Product> allSellProducts, String buyerFirstName, String buyerLastName) {
        this.logID = produceSellLogId();
        this.date = date;
        this.moneyGained = moneyGained;
        this.offerLossMoney = offerLossMoney;
        this.allSellProducts = allSellProducts;
        this.buyerFirstName = buyerFirstName;
        this.buyerLastName = buyerLastName;
        allSellLogs.add(this);
    }

    public String getLogID() {
        return logID;
    }

    public Date getDate() {
        return date;
    }

    public double getMoneyGained() {
        return moneyGained;
    }

    public double getOfferLossMoney() {
        return offerLossMoney;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public SellLogStatus getStatus() {
        return status;
    }

    public static ArrayList<SellLog> getAllSellLogs() {
        return allSellLogs;
    }

    public ArrayList<String> getInfo() {
        ArrayList<String> sellLogInfo = new ArrayList<>();
        sellLogInfo.add(this.getLogID());
        Date date = this.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        sellLogInfo.add(strDate);
        sellLogInfo.add(String.valueOf(this.getMoneyGained()));
        sellLogInfo.add(String.valueOf(this.getOfferLossMoney()));
        sellLogInfo.add(this.getBuyerFirstName());
        sellLogInfo.add(this.getBuyerLastName());
        sellLogInfo.add(this.getStatus().toString());
        return sellLogInfo;
    }
    public String produceSellLogId() {
        String logId = "SellLog_";
        Random random = new Random();
        int min = 1;
        int max = 100000000;
        int range = max - min;
        int rand = random.nextInt(range) + min;
        logId += rand;
        return logId;
    }
}
