package model.requests;

import model.account.Seller;
import model.offer.Offer;
import model.product.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RequestForEditProduct extends Request {
    private Seller seller;
    private Product product;
    private String field;
    private String oldValue;
    private String newValue;
    private static ArrayList<RequestForEditProduct> allRequestsForEditProduct = new ArrayList<>();

    public RequestForEditProduct(Seller seller, Product product, String field, String oldValue, String newValue) {
        this.seller = seller;
        this.product = product;
        this.field = field;
        this.oldValue = oldValue;
        this.newValue = newValue;
        allRequestsForEditProduct.add(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public Product getProduct() {
        return product;
    }

    public String getField() {
        return field;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
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
        details.add(this.getProduct().getCompanyName());
        details.add(String.valueOf(this.getProduct().getPrice()));
        details.add(this.getProduct().getExplanationText());
        details.add(this.getField());
        details.add(this.getOldValue());
        details.add(this.getNewValue());
        return super.getRequestDetails();
    }
}
