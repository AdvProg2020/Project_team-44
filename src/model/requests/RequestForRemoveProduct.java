package model.requests;

import model.account.Seller;
import model.product.Product;

import java.util.ArrayList;

public class RequestForRemoveProduct {
    private Seller seller;
    private Product product;
    private RequestStatus status;
    private static ArrayList<RequestForRemoveProduct> allRequestForRemoveProduct = new ArrayList<>();

    public RequestForRemoveProduct(Seller seller, Product product) {
        this.seller = seller;
        this.product = product;
        this.status = RequestStatus.IN_PROGRESS;
        allRequestForRemoveProduct.add(this);
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
