package server.model.buyLog;

import server.model.account.Seller;
import server.model.product.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class BuyLog {
    private String logID;
    private Date date;
    private double moneyPaid;
    private double discountCodeAmountUsed;
    private ArrayList<Product> allPurchasedProducts;
    private HashMap<Product, Seller> sellerForEachProduct;
    private BuyLogStatus status = BuyLogStatus.IN_PROGRESS;
    private static ArrayList<BuyLog> allBuyLogs = new ArrayList<>();
    private String purchaserAddress;

    public String getPurchaserAddress() {
        return purchaserAddress;
    }

    public void setPurchaserAddress(String purchaserAddress) {
        this.purchaserAddress = purchaserAddress;
    }

    public BuyLog(Date date, double moneyPaid, double discountCodeAmountUsed, ArrayList<Product> allPurchasedProducts, HashMap<Product, Seller> sellerForEachProduct) {
        this.logID = produceBuyLogId();
        this.date = date;
        this.moneyPaid = moneyPaid;
        this.discountCodeAmountUsed = discountCodeAmountUsed;
        this.allPurchasedProducts = allPurchasedProducts;
        this.sellerForEachProduct = sellerForEachProduct;
        allBuyLogs.add(this);
    }

    public ArrayList<Product> getAllPurchasedProducts() {
        return allPurchasedProducts;
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

    public HashMap<Product, Seller> getSellerForEachProduct() {
        return sellerForEachProduct;
    }

    public BuyLogStatus getStatus() {
        return status;
    }

    public void setStatus(BuyLogStatus status) {
        this.status = status;
    }

    public static ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }

    public static BuyLog getBuyLogById(String logId) {
        for (BuyLog allBuyLog : allBuyLogs) {
            if (allBuyLog.getLogID().equals(logId)) {
                return allBuyLog;
            }
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
        String productToSeller = "";
        for (Product product : this.getSellerForEachProduct().keySet()) {
            productToSeller += product.getName() + "  :  " + this.getSellerForEachProduct().get(product).getFirstName() + "  " + this.getSellerForEachProduct().get(product).getLastName();
        }
        info.add(this.getStatus().toString());
        return info;
    }

    public String produceBuyLogId() {
        String logId = "BuyLog_";
        Random random = new Random();
        int min = 2;
        int max = 100000000;
        int range = max - min;
        int rand = random.nextInt(range) + min;
        logId += rand;
        return logId;
    }
}
