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

    public Product(String productID, Category category, String name, String companyName, int price, String explanationText) {
        this.productID = productID;
        this.category = category;
        this.name = name;
        this.companyName = companyName;
        this.price = price;
        this.explanationText = explanationText;
    }
}
