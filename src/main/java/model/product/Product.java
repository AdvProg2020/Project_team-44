package model.product;

import model.Category;
import model.Rating;
import model.account.Purchaser;
import model.account.Seller;
import model.comment.Comment;
import model.offer.Offer;

import java.util.ArrayList;
import java.util.Random;

public class Product {
    private String productID;
    private Category category;
    private ProductStatus status = ProductStatus.IN_CREATION_PROGRESS;
    private ArrayList<String> categoryAttributes = new ArrayList<>();
    private String name;
    private String companyName;
    private double price;
    private ArrayList<Seller> allSellers = new ArrayList<>();
    private boolean isAvailable;
    private ArrayList<Comment> allComments = new ArrayList<>();
    private String explanationText;
    private int availableNumber;
    private Offer offer;
    private ArrayList<Rating> allRating = new ArrayList<>();
    private static ArrayList<Product> allProducts = new ArrayList<>();
    private static ArrayList<Purchaser> allPurchaser = new ArrayList<>();

    public Product(Category category, String name, String companyName, int price, String explanationText) {
        this.productID = produceProductId();
        this.category = category;
        this.name = name;
        this.companyName = companyName;
        this.price = price;
        this.explanationText = explanationText;
        allProducts.add(this);
    }

    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public static Product getProductByID(String productId) {
        for (Product product : allProducts) {
            if (product.getProductID().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public String getProductID() {
        return productID;
    }

    public Category getCategory() {
        return category;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public ArrayList<String> getCategoryAttributes() {
        return categoryAttributes;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<Seller> getAllSellers() {
        return allSellers;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public ArrayList<Comment> getAllComments() {
        return allComments;
    }

    public String getExplanationText() {
        return explanationText;
    }

    public int getAvailableNumber() {
        return availableNumber;
    }

    public Offer getOffer() {
        return offer;
    }

    public ArrayList<Rating> getAllRating() {
        return allRating;
    }

    public static ArrayList<Purchaser> getAllPurchaser() {
        return allPurchaser;
    }

    public float getAverageRating() {
        float sum = 0;
        float num = 0;
        for (Rating rating : this.getAllRating()) {
            sum += rating.getRating();
            num++;
        }
        return sum / num;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public ArrayList<String> getProductInfo() {
        ArrayList<String> info = new ArrayList<>();
        info.add(this.getName());
        info.add(String.valueOf(this.getPrice()));
        info.add(this.getCategory().getName());
        info.add(String.valueOf(this.getAverageRating()));
        info.add(this.getExplanationText());
        if (this.getOffer().getOfferID() == null)
            info.add("null");
        else info.add(this.getOffer().getOfferID());
        String sellers = "";
        for (Seller allSeller : this.getAllSellers()) {
            sellers = allSeller.getFirstName() + "  " + allSeller.getLastName() + " : ";
        }
        info.add(sellers);
        return info;
    }
    public String produceProductId() {
        String logId = "Product_";
        Random random = new Random();
        int min = 3;
        int max = 100000000;
        int range = max - min;
        int rand = random.nextInt(range) + min;
        logId += rand;
        return logId;
    }
}
