package model.product;

import com.google.gson.Gson;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Category;
import model.Rating;
import model.account.Purchaser;
import model.account.Seller;
import model.comment.Comment;
import model.offer.Offer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class Product {
    private String productID;
    private transient Category category;
    private ProductStatus status = ProductStatus.IN_CREATION_PROGRESS;
    private HashMap<String, String> categoryAttributes = new HashMap<>();
    private String name;
    private String companyName;
    private double price;
    private ArrayList<Seller> allSellers = new ArrayList<>();
    private boolean isAvailable;
    private ArrayList<Comment> allComments;
    private String explanationText;
    private Offer offer;
    private ArrayList<Rating> allRating = new ArrayList<>();
    private static ArrayList<Product> allProducts = new ArrayList<>();
    private static ArrayList<Purchaser> allPurchaser = new ArrayList<>();
    private Date generatedDate;
    private int viewTimes;
    private String imageName;

    public Product(Category category, String name, String companyName, double price, String explanationText, String imageName) {
        this.productID = produceProductId();
        this.category = category;
        this.name = name;
        this.companyName = companyName;
        this.price = price;
        this.explanationText = explanationText;
        this.generatedDate = new Date();
        this.imageName = imageName;
        this.allComments = new ArrayList<>();
        allProducts.add(this);
//        category.getAllSubProducts().add(this);
//        createAndUpdateJson(this);
//        updateAllParent(category);
//        category.createAndUpdateJson(category);
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getImageName() {
        return imageName;
    }

    public void setAllSellers(ArrayList<Seller> allSellers) {
        this.allSellers = allSellers;
    }

    public void createAndUpdateJson(Product product) {
        try {
            Writer writer = new FileWriter("src/main/resources/Products/" + product.getName() + ".json");
            new Gson().toJson(product, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + "!!!!!!!!!!!!!!");
        }
    }

    public void updateAllParent(Category category) {
        category.updateAllParent(category);
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

    public HashMap<String, String> getCategoryAttributes() {
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

    public Offer getOffer() {
        return offer;
    }

    public ArrayList<Rating> getAllRating() {
        return allRating;
    }

    public static ArrayList<Purchaser> getAllPurchaser() {
        return allPurchaser;
    }

    public int getViewTimes() {
        return viewTimes;
    }

    public Date getGeneratedDate() {
        return generatedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setExplanationText(String explanationText) {
        this.explanationText = explanationText;
    }

    public void setViewTimes() {
        this.viewTimes++;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static void setAllProducts(ArrayList<Product> allProducts) {
        Product.allProducts = allProducts;
    }

    public float getAverageRating() {
        float sum = 0;
        float num = 0;
        for (Rating rating : this.getAllRating()) {
            sum += rating.getRating();
            num++;
        }
        if (this.getAllRating().size() == 0)
            return 0;
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

    public static Product getProductByName(String productName) {
        for (Product allProduct : allProducts) {
            if (allProduct.getName().equals(productName))
                return allProduct;
        }
        return null;
    }

    public boolean isPurchasedByPurchaser(Purchaser purchaser) {
        for (Purchaser purchaser1 : getAllPurchaser()) {
            if (purchaser == purchaser1)
                return true;
        }
        return false;
    }

}
