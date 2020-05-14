package model.product;

import model.Category;
import model.Rating;
import model.account.Seller;
import model.comment.Comment;
import model.offer.Offer;

import java.util.ArrayList;

public class Product {
    private String productID;
    private Category category;
    private ProductStatus status = ProductStatus.IN_CREATION_PROGRESS;
    private ArrayList<String> categoryAttributes = new ArrayList<>();
    private String name;
    private String companyName;
    private double price;
    private ArrayList<Seller> allSellers = new ArrayList<>();
    private float averageRating;
    private boolean isAvailable;
    private ArrayList<Comment> allComments = new ArrayList<>();
    private String explanationText;
    private int availableNumber;
    private Offer offer;
    private Rating rating;
    private static ArrayList<Product> allProducts;

    public Product(String productID, Category category, String name, String companyName, int price, String explanationText) {
        this.productID = productID;
        this.category = category;
        this.name = name;
        this.companyName = companyName;
        this.price = price;
        this.explanationText = explanationText;
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

    public float getAverageRating() {
        return averageRating;
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

    public Rating getRating() {
        return rating;
    }
}
