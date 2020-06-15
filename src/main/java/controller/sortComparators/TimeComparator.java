package sample.controller.sortComparators;

import model.product.Product;

import java.util.Comparator;

public class TimeComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
//        return p1.getGeneratedDate().compareTo(p2.getGeneratedDate());
        return 0;
    }
}
