package controller;


import controller.fields.ProductsPageAvailableFilters;
import controller.sortComparators.*;
import exception.*;

import model.Category;
import model.Sort.Sort;
import model.product.Product;

import java.util.ArrayList;
import java.util.Collections;

public abstract class ProductsPageController {
    static Product selectedProduct = null;
    static Sort currentProductsSort = Sort.VIEW;
    static ArrayList<Product> allFilteredProducts = Product.getAllProducts();
    static ArrayList<String> allFilters = new ArrayList<>();

    static {
        processSortByView(true);
    }

    public static ArrayList<String> processViewCategories() {
        return Category.getAllCategoryNames();
    }

    public static ArrayList<String> processShowAvailableFiltersEach() {
        ArrayList<String> allFilters = new ArrayList<>();
        for (ProductsPageAvailableFilters filter : ProductsPageAvailableFilters.values()) {
            allFilters.add(filter.toString());
        }
        return allFilters;
    }

    public static void processFilter(String field, String filter)
            throws CategoryNotExistsException, FilterNotExistsException {
        if (field.equals("BY_NAME")) {
            processFilterEachByName(filter);
        } else if (field.equals("BY_CATEGORY")) {
            processFilterByCategory(filter);
        } else if (field.equals("BY_PRICE")) {
            processFilterByPriceRange(Integer.parseInt(filter.split(",")[0]), Integer.parseInt(filter.split(",")[1]));
        } else {
            throw new FilterNotExistsException("Wrong filter.");
        }
    }

    static void processFilterEachByName(String availableFilter) {
        allFilters.add("BY_NAME");
        ArrayList<Product> tempFilteredProducts = new ArrayList<>(allFilteredProducts);
        allFilteredProducts.clear();
        for (Product product : tempFilteredProducts) {
            if (product.getName().contains(availableFilter)) {
                allFilteredProducts.add(product);
            }
        }
    }

    static void processFilterByCategory(String categoryFilter) throws CategoryNotExistsException {
        ValidationController.checkCategoryExistence(categoryFilter);
        allFilters.add("BY_" + categoryFilter.toUpperCase() + "_CATEGORY");
        ArrayList<Product> tempFilteredProducts = new ArrayList<>(allFilteredProducts);
        allFilteredProducts.clear();
        for (Product product : tempFilteredProducts) {
            if (ManagerAccountController.getCategoryProducts(categoryFilter).contains(product.getName())) {
                allFilteredProducts.add(product);
            }
        }
    }

    static void processFilterByPriceRange(int minPrice, int maxPrice) {
        allFilters.add("BY_PRICE_RANGE_( " + minPrice + " , " + maxPrice + " )");
        ArrayList<Product> tempFilteredProducts = new ArrayList<>(allFilteredProducts);
        allFilteredProducts.clear();
        for (Product product : tempFilteredProducts) {
            if (product.getPrice() <= maxPrice && product.getPrice() >= minPrice) {
                allFilteredProducts.add(product);
            }
        }
    }

    public static ArrayList<String> processCurrentFilterEach() {
        return allFilters;
    }

    public static void processDeleteFilterEach(String selectedFilter) throws FilterNotExistsException {
        ValidationController.checkFilterExistence(selectedFilter);
        allFilters.remove(selectedFilter);
    }

    public static ArrayList<String> processShowAvailableSortsEach() {
        ArrayList<String> allSorts = new ArrayList<>();
        for (Sort sort : Sort.values()) {
            allSorts.add(sort.toString());
        }
        return allSorts;
    }

    public static void processSortEach(String availableSort, boolean isUp) throws SortNotExistsException {
//        ValidationController.checkSortExistence(availableSort);
//        if (availableSort.equals(Sort.TIME)) {
//            currentProductsSort = Sort.TIME;
//            processSortByTime(isUp);
//        } else if (availableSort.equals(Sort.SCORE)) {
//            currentProductsSort = Sort.SCORE;
//            processSortByScore(isUp);
//        } else if (availableSort.equals(Sort.PRICE)) {
//            currentProductsSort = Sort.PRICE;
//            processSortByPrice(isUp);
//        } else {
//            currentProductsSort = Sort.VIEW;
//            processSortByView(isUp);
//        }
    }

    public static void processSortByTime(boolean isUp) {
        Collections.sort(allFilteredProducts, new TimeComparator());
        if (!isUp) {
            Collections.reverse(allFilteredProducts);
        }
    }

    public static void processSortByScore(boolean isUp) {
        Collections.sort(allFilteredProducts, new ScoreComparator());
        if (!isUp) {
            Collections.reverse(allFilteredProducts);
        }
    }

    public static void processSortByView(boolean isUp) {
        Collections.sort(allFilteredProducts, new ViewComparator());
        if (!isUp) {
            Collections.reverse(allFilteredProducts);
        }
    }

    public static void processSortByPrice(boolean isUp) {
        Collections.sort(allFilteredProducts, new PriceComparator());
        if (!isUp) {
            Collections.reverse(allFilteredProducts);
        }
    }

    public static String processCurrentSortEach() {
        return currentProductsSort.toString();
    }

    public static void processDisableSortEach() {
        currentProductsSort = Sort.VIEW;
    }

    public static ArrayList<String> processShowProducts() {
        ArrayList<String> allProducts = new ArrayList<>();
        for (Product product : allFilteredProducts) {
            allProducts.add(product.getName());
        }
        return allProducts;
    }

    public static void processShowProduct(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
//        ProductsPageController.selectedProduct.setViewTimes();
        ProductsPageController.selectedProduct = Product.getProductByID(productId);
    }


}
