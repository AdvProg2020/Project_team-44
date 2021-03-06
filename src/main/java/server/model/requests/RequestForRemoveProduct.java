package server.model.requests;

import server.model.account.Seller;
import server.model.product.Product;

import java.util.ArrayList;

public class RequestForRemoveProduct extends Request {
    //    private String ID;  ???????????????????????????????????
    private Seller seller;
    private Product product;
    private static ArrayList<RequestForRemoveProduct> allRequestForRemoveProduct = new ArrayList<>();

    public static ArrayList<RequestForRemoveProduct> getAllRequestForRemoveProduct() {
        return allRequestForRemoveProduct;
    }

    public RequestForRemoveProduct(Seller seller, Product product) {
        super();
        this.seller = seller;
        this.product = product;
        allRequestForRemoveProduct.add(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String produceRequestId() {
        String id = "RequestForRemoveProduct_" + super.produceRequestId();
        return id;
    }

    @Override
    public ArrayList<String> getRequestDetails() {
        ArrayList<String> details = super.getRequestDetails();
        details.add(this.getSeller().getFirstName());
        details.add(this.getSeller().getLastName());
        details.add(this.getProduct().getName());
        details.add(this.getProduct().getCompanyName());
        return details;
    }
}
