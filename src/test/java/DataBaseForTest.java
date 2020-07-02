import model.*;
import model.account.Manager;
import model.account.Purchaser;
import model.account.Seller;
import model.buyLog.BuyLog;
import model.offer.Offer;
import model.product.Product;
import model.requests.Request;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DataBaseForTest {
    public static Purchaser purchaser1 = new Purchaser("Am 4", "Amir", "Mohammadi",
            "amir160@gmail.com", "212121", "12aa", "1a");
    public static Purchaser purchaser2 = new Purchaser("G@3", "David", "James", "jgi@email.com",
            "091212", "12mm", "4re");
    public static Purchaser purchaser3 = new Purchaser("Lm10", "Lionel", "Messi",
            "messi@", "767", "lll", "lan");
    public static Manager manager = new Manager("Qmm", "Dariush", "Amiri", "432",
            "901", "sd");
    public static Seller seller1 = new Seller("XYz", "Alex", "dd", "ya@yahoo.com",
            "2441", "12344", "izo", "street5", "6777");
    public static Seller seller2 = new Seller("Cr7", "cristiano", "Ronaldo", "rooo", "852"
            , "43", "tt", "sd d", "787");
    public static Category category1 = new Category("car", null, "ali");
    public static Category category2 = new Category("digital", null, "aff");
    public static Request request1 = new Request();
    public static CodedDiscount codedDiscount1 = new CodedDiscount(new Date(1380), new Date(123), 30,
            5000);
    public static CodedDiscount codedDiscount2 = new CodedDiscount(new Date(87), new Date(65), 87, 987);
    public static Product product1 = new Product(category1, "peraid", "saipa", 12000,
            "good Product!", "12");
    public static Product product2 = new Product(category2, "bmw", "bmw", 10000, "good", "32");
    public static Product product3 = new Product(category2,"peris","aipr",80000,"nice","ado");
    public static Product product4 = new Product(category1,"door","xxxxxxx",9000,"soft","tss");
    public static ArrayList<Product> setProductsListForOffer1() {
        ArrayList<Product> productsListForOffer1 = new ArrayList<>();
        productsListForOffer1.add(product1);
        return productsListForOffer1;
    }

    public static Offer offer1 = new Offer(setProductsListForOffer1(), new Date(138), new Date(738), 35);

    public static HashMap<Product, Seller> setProductsListForBuyLog() {
        HashMap<Product, Seller> output = new HashMap<>();
        output.put(product1, seller2);
        return output;
    }

    public static BuyLog buyLog1 = new BuyLog(new Date(1290), 2.0, 12.5, setProductsListForOffer1(),
            setProductsListForBuyLog());
}
