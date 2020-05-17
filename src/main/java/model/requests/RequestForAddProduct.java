package model.requests;

import model.Category;
import model.account.Seller;

import java.util.ArrayList;

public class RequestForAddProduct extends Request {
    private Seller seller;
    private String productID;
    private Category category;
    private String name;
    private String companyName;
    private int price;
    private String explanationText;
    private static ArrayList<RequestForAddProduct> allRequestsForAddProduct = new ArrayList<>();

    public RequestForAddProduct(int requestId, Seller seller, String productID, Category category, String name, String companyName, int price, String explanationText) {
        this.seller = seller;
        this.productID = productID;
        this.category = category;
        this.name = name;
        this.companyName = companyName;
        this.price = price;
        this.explanationText = explanationText;
        allRequestsForAddProduct.add(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public String getProductID() {
        return productID;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getPrice() {
        return price;
    }

    public String getExplanationText() {
        return explanationText;
    }

    public static ArrayList<RequestForAddProduct> getAllRequestsForAddProduct() {
        return allRequestsForAddProduct;
    }

    @Override
    public String produceRequestId() {
        String id = "RequestForAddProduct_" + super.produceRequestId();
        return id;
    }

    @Override
    public ArrayList<String> getRequestDetails() {
        ArrayList<String> details = super.getRequestDetails();
        details.add(this.getSeller().getFirstName());
        details.add(this.getSeller().getLastName());
        details.add(this.getName());
        details.add(this.getCompanyName());
        details.add(String.valueOf(this.getPrice()));
        details.add(this.getExplanationText());
        return details;
    }
}
