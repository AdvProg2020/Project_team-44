package server.model.requests;

import server.model.account.Seller;
import server.model.product.Product;

import java.util.ArrayList;

public class RequestForEditProduct extends Request {
    private Seller seller;
    private Product product;
    private String name;
    private String companyName;
    double price;
    private String explanationText;
    private String imageName;
    private static ArrayList<RequestForEditProduct> allRequestsForEditProduct = new ArrayList<>();

    public RequestForEditProduct(Seller seller, Product product, String name, String companyName, double price, String explanationText, String imageName) {
        this.seller = seller;
        this.product = product;
        this.name = name;
        this.companyName = companyName;
        this.price = price;
        this.explanationText = explanationText;
        this.imageName = imageName;
        allRequestsForEditProduct.add(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public Product getProduct() {
        return product;
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

    public String getExplanationText() {
        return explanationText;
    }

    public String getImageName() {
        return imageName;
    }

    public static ArrayList<RequestForEditProduct> getAllRequestsForEditProduct() {
        return allRequestsForEditProduct;
    }

    @Override
    public String produceRequestId() {
        String id = "RequestForEditProduct_" + super.produceRequestId();
        return id;
    }

    @Override
    public ArrayList<String> getRequestDetails() {
        ArrayList<String> details = super.getRequestDetails();
        details.add(this.getSeller().getFirstName());
        details.add(this.getSeller().getLastName());
        details.add(this.getProduct().getName());
        details.add(this.getCompanyName());
        details.add(String.valueOf(this.getPrice()));
        details.add(this.getExplanationText());
        return super.getRequestDetails();
    }
}
