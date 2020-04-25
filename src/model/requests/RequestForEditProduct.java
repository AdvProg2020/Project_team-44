package model.requests;

import model.account.Seller;
import model.product.Product;

import java.util.ArrayList;

public class RequestForEditProduct {
    private Seller seller;
    private Product pastProduct;
    private Product newProduct;
    private RequestStatus status;
    private static ArrayList<RequestForEditProduct> allRequestsForEditProduct = new ArrayList<>();

    public RequestForEditProduct(Seller seller, Product pastProduct, Product newProduct) {
        this.seller = seller;
        this.pastProduct = pastProduct;
        this.newProduct = newProduct;
        this.status = RequestStatus.IN_PROGRESS;
        allRequestsForEditProduct.add(this);
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
