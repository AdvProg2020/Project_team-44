package controller.sortComparators;

import model.product.Product;

import java.util.Comparator;

public class ViewComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
//        return -p1.getViewTimes() + p2.getViewTimes();
        return 0;
    }
}
