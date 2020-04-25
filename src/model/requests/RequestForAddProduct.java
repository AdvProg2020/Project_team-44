package model.requests;

import model.account.Seller;
import model.product.Product;

import java.util.ArrayList;

public class RequestForAddProduct {
    private Seller seller;
    private Product product;
    private RequestStatus status;
    private static ArrayList<RequestForAddProduct> allRequestsForAddProduct = new ArrayList<>();

    public RequestForAddProduct(Seller seller, Product product) {
        this.seller = seller;
        this.product = product;
        this.status = RequestStatus.IN_PROGRESS;
        allRequestsForAddProduct.add(this);
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
