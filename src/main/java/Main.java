
import com.sun.javaws.security.AppContextUtil;
import model.Category;
import model.CodedDiscount;
import model.account.Manager;
import model.account.Purchaser;
import model.account.Seller;
import model.offer.Offer;
import model.product.Product;
import model.requests.Request;
import view.CommandProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Main {
//    public ArrayList<Product> setProductsListForOffer1() {
//        ArrayList<Product> productsListForOffer1 = new ArrayList<>();
//        productsListForOffer1.add(product1);
//        return productsListForOffer1;
//    }


//    public HashMap<Product, Seller> setProductsListForBuyLog() {
//        HashMap<Product, Seller> output = new HashMap<>();
//        output.put(product1, seller2);
//        return output;
//    }

   // BuyLog buyLog1 = new BuyLog(new Date(1290), 2.0, 12.5, setProductsListForOffer1(),
         //   setProductsListForBuyLog());
    public static void main(String[] args) {
        Purchaser purchaser1 = new Purchaser("Am 4", "Amir", "Mohammadi",
                "amir160@gmail.com", "212121", "12aa", "1a");
        Purchaser purchaser2 = new Purchaser("G@3", "David", "James", "jgi@email.com",
                "091212", "12mm", "4re");
        Purchaser purchaser3 = new Purchaser("Lm10", "Lionel", "Messi",
                "messi@", "767", "lll", "lan");
        Manager manager = new Manager("Qmm", "Dariush", "Amiri", "432",
                "901", "sd");
        Seller seller1 = new Seller("aaaa", "Alex", "dd", "ya@yahoo.com",
                "2441", "1234", "izo", "street5", "6777");
        Seller seller2 = new Seller("Cr7", "cristiano", "Ronaldo", "rooo", "852"
                , "43", "tt", "sd d", "787");
        Category category1 = new Category("car",null);
        Category category2 = new Category("digital", null);
        Request request1 = new Request();
        CodedDiscount codedDiscount1 = new CodedDiscount(new Date(120,10,18), new Date(123,9,19), 30,
                5000);
        CodedDiscount codedDiscount2 = new CodedDiscount(new Date(120,8,28), new Date(121,9,3), 87, 987);
        ArrayList<CodedDiscount> purchaseCodedDiscount = new ArrayList<>();
        purchaseCodedDiscount.add(codedDiscount1);
        purchaseCodedDiscount.add(codedDiscount2);
        purchaser3.setAllDiscountCodes(purchaseCodedDiscount);
        Product product1 = new Product(category1, "peraid", "saipa", 12000,
                "good Product!");
        Product product2 = new Product(category2, "bmw", "bmw", 10000, "good");
        Product product3 = new Product(category2,"peris","aipr",80000,"nice");
        Product product4 = new Product(category1,"door","xxxxxxx",9000,"soft");
        ArrayList<Product> offerProduct = new ArrayList<>();
        offerProduct.add(product1);
        offerProduct.add(product3);
       Offer offer1 = new Offer(offerProduct, new Date(138), new Date(738), 35);
       ArrayList<Offer> sellerOffer1 = new ArrayList<>();
       sellerOffer1.add(offer1);
       seller1.setOffersList(sellerOffer1);
        HashMap<Product , Integer> productIntegerHashMap1 = new HashMap<>();
        productIntegerHashMap1.put(product1,1);
        productIntegerHashMap1.put(product3,2);
        ArrayList<Purchaser> productPurchasers1 = new ArrayList<>();
        productPurchasers1.add(purchaser2);
        productPurchasers1.add(purchaser3);
        product1.setAllPurchaser(productPurchasers1);
        seller1.setProductsToSell(productIntegerHashMap1);
        CommandProcessor.runCommandProcessorByMenu();

    }


}
