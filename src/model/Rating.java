package model;

import model.account.Purchaser;
import model.product.Product;

public class Rating {
    private Product product;
    private Purchaser purchaser;
    private int rating;

    public Rating(Product product, Purchaser purchaser, int rating) {
        this.product = product;
        this.purchaser = purchaser;
        this.rating = rating;
    }
}
