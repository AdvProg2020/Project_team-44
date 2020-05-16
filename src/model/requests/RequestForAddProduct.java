package model.requests;

import model.account.Seller;
import model.product.Product;

import java.util.ArrayList;

public class RequestForAddProduct extends Request {
    private Seller seller;
    private Product product;
    private static ArrayList<RequestForAddProduct> allRequestsForAddProduct = new ArrayList<>();

    public RequestForAddProduct(Seller seller, Product product, int requestId) {
        super(requestId);
        this.seller = seller;
        this.product = product;
        allRequestsForAddProduct.add(this);
    }
}
