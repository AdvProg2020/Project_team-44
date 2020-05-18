package controller;

import exception.FilterNotExistsException;
import exception.ProductIdNotExistsException;
import exception.SortNotExistsException;
import model.Category;
import model.Sort.Sort;
import model.product.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class ProductsPageController {
    static Product selectedProduct = null;
    static Sort currentProductsSort = Sort.VIEW;

    public static ArrayList<String> processViewCategories() {
        return Category.getAllCategoryNames();
    }

    public static void processShowAvailableFiltersEach() {

    }

    public static void processFilterEach(String availableFilter) throws FilterNotExistsException {
        ValidationController.checkFilterExistence(availableFilter);
        /*TODO*/

    }

    public static void processCurrentFilterEach() {

    }

    public static void processDeleteFilterEach(String selectedFilter) throws FilterNotExistsException {
        ValidationController.checkFilterExistence(selectedFilter);
        /*TODO*/

    }

    public static ArrayList<String> processShowAvailableSortsEach() {
        ArrayList<String> allSorts = new ArrayList<>();
        for (Sort sort : Sort.values()) {
            allSorts.add(sort.toString());
        }
        return allSorts;
    }

    public static void processSortEach(String availableSort) throws SortNotExistsException {
        ValidationController.checkSortExistence(availableSort);
        if (availableSort.equals(Sort.TIME)) {
            currentProductsSort = Sort.TIME;
        } else if (availableSort.equals(Sort.SCORE)) {
            currentProductsSort = Sort.SCORE;
        } else {
            currentProductsSort = Sort.VIEW;
        }
        /*TODO*/

    }

    public static String processCurrentSortEach() {
        return currentProductsSort.toString();
    }

    public static void processDisableSortEach() {
        currentProductsSort = Sort.VIEW;
    }

    public static void processShowProducts() {

    }

    public static void processShowProduct(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        ProductsPageController.selectedProduct = Product.getProductByID(productId);
    }


}
