package controller.sortComparators;

import model.product.Product;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return (int) (-p1.getAverageRating() + p2.getAverageRating());
    }
}
