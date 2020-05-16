package model.requests;

import model.account.Seller;
import model.product.Product;

import java.util.ArrayList;

public class RequestForRemoveProduct extends Request {
    //    private String ID;  ???????????????????????????????????
    private Seller seller;
    private Product product;
    private static ArrayList<RequestForRemoveProduct> allRequestForRemoveProduct = new ArrayList<>();

    public RequestForRemoveProduct(Seller seller, Product product, int requestId) {
        super(requestId);
        this.seller = seller;
        this.product = product;
        allRequestForRemoveProduct.add(this);
    }

}
