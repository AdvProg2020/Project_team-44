package server.model;

import server.model.account.Purchaser;
import server.model.product.Product;

public class Rating {
    private transient Product product;
    private Purchaser purchaser;
    private int rating;

    public Rating(Product product, Purchaser purchaser, int rating) {
        this.product = product;
        this.purchaser = purchaser;
        this.rating = rating;
        product.getAllRating().add(this);
        product.createAndUpdateJson(product);
    }

    public int getRating() {
        return rating;
    }
}
