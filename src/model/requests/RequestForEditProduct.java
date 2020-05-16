package model.requests;

import model.account.Seller;
import model.product.Product;

import java.util.ArrayList;

public class RequestForEditProduct extends Request {
    private Seller seller;
    private Product pastProduct;
    private Product newProduct;
    private static ArrayList<RequestForEditProduct> allRequestsForEditProduct = new ArrayList<>();

    public RequestForEditProduct(Seller seller, Product pastProduct, Product newProduct, int requestId) {
        super(requestId);
        this.seller = seller;
        this.pastProduct = pastProduct;
        this.newProduct = newProduct;
        allRequestsForEditProduct.add(this);
    }
}
