//package server.controller;
//
//
//import server.controller.fields.ProductsPageAvailableFilters;
//import server.controller.sortComparators.*;
//import server.exception.*;
//
//import server.model.Category;
//import server.model.Sort.Sort;
//import server.model.product.Product;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//
//public abstract class ProductsPageController {
//    static Product selectedProduct = null;
//    static Sort currentProductsSort = Sort.VIEW;
//    static ArrayList<Product> allFilteredProducts = new ArrayList<>();
//    static ArrayList<String> allFilters = new ArrayList<>();
//
//
//    static {
//        processSortByView(true);
//    }
//
//    public static ArrayList<Product> getAllFilteredProducts() {
//        return allFilteredProducts;
//    }
//
//    public static ArrayList<String> processViewCategories() {
//        return Category.getAllCategoryNames();
//    }
//
//    public static ArrayList<String> processShowAvailableFiltersEach() {
//        ArrayList<String> allFilters = new ArrayList<>();
//        for (ProductsPageAvailableFilters filter : ProductsPageAvailableFilters.values()) {
//            allFilters.add(filter.toString());
//        }
//        return allFilters;
//    }
//
//    public static void processFilter(String field, String filter) throws FilterNotExistsException, CategoryNotExistsException {
//        if (field.equals("BY_NAME")) {
//            processFilterEachByName(filter);
//        } else if (field.equals("BY_CATEGORY")) {
//            processFilterByCategory(filter);
//        } else if (field.equals("BY_PRICE")) {
//            processFilterByPriceRange(Integer.parseInt(filter.split(",")[0]), Integer.parseInt(filter.split(",")[1]));
//        } else {
//            throw new FilterNotExistsException("Wrong filter.");
//        }
//    }
//
//    static void processFilterEachByName(String availableFilter) {
//        allFilters.add("BY_NAME_" + availableFilter);
//        ArrayList<Product> tempFilteredProducts = new ArrayList<>(allFilteredProducts);
//        allFilteredProducts.clear();
//        for (Product product : tempFilteredProducts) {
//            if (product.getName().contains(availableFilter)) {
//                allFilteredProducts.add(product);
//            }
//        }
//    }
//
//    static void processFilterByCategory(String categoryFilter) throws CategoryNotExistsException {
//        ValidationController.checkCategoryExistence(categoryFilter);
//        allFilters.add("BY_CATEGORY_" + categoryFilter.toUpperCase());
//        ArrayList<Product> tempFilteredProducts = new ArrayList<>(Product.getAllProducts());
//        System.out.println("5     :    " + allFilteredProducts.size());
//
//        if (allFilteredProducts.size() == Product.getAllProducts().size())
//            allFilteredProducts.clear();
//        System.out.println("4     :    " + allFilteredProducts.size());
//
//        tempFilteredProducts.removeAll(allFilteredProducts);
//        int minPrice = -1;
//        int maxPrice = -1;
//        for (String allFilter : allFilters) {
//            if (allFilter.startsWith("BY_PRICE")) {
//                minPrice = Integer.parseInt(allFilter.substring(15).split(",")[0]);
//                maxPrice = Integer.parseInt(allFilter.substring(15).split(",")[1]);
//                break;
//            }
//        }
//        for (Product product : tempFilteredProducts) {
//            if (ManagerAccountController.getCategoryProducts(categoryFilter).contains(product.getName())) {
//                if (minPrice != -1 && maxPrice != -1) {
//                    if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
//                        allFilteredProducts.add(product);
//                } else
//                    allFilteredProducts.add(product);
//            }
//        }
//        System.out.println("3     :    " + allFilteredProducts.size());
//
////        for (int i = 0; i < allFilteredProducts.size(); i++) {
////            if (!allFilteredProducts.get(i).getCategory().getName().equals(categoryFilter)) {
////                allFilteredProducts.remove(i);
////                i--;
////            }
////        }
//        System.out.println(" cat name    : " + categoryFilter);
//        System.out.println("2     :    " + allFilteredProducts.size());
//
//        if (minPrice != -1 && maxPrice != -1) {
//            for (int i = 0; i < allFilteredProducts.size(); i++) {
//                if (allFilteredProducts.get(i).getPrice() < minPrice || allFilteredProducts.get(i).getPrice() > maxPrice) {
//                    allFilteredProducts.remove(i);
//                    i--;
//                }
//            }
//        }
//        System.out.println("1     :    " + allFilteredProducts.size());
//    }
//
//    static void processFilterByPriceRange(int minPrice, int maxPrice) throws CategoryNotExistsException, FilterNotExistsException {
//        if (allFilters.size() == 0) {
//            if (allFilteredProducts.size() == 0)
//                allFilteredProducts.addAll(Product.getAllProducts());
//        }
//        for (int i = 0; i < allFilters.size(); i++) {
//            if (allFilters.get(i).startsWith("BY_PRICE_RANGE_"))
//                processDeleteFilterEach(allFilters.get(i));
//            i--;
//        }
//        if (minPrice != -1 && maxPrice != -1) {
//            ArrayList<Product> tempFilteredProducts = new ArrayList<>();
//            tempFilteredProducts.addAll(allFilteredProducts);
//            allFilters.add("BY_PRICE_RANGE_" + minPrice + "," + maxPrice);
//            for (Product product : tempFilteredProducts)
//                if (product.getPrice() > maxPrice || product.getPrice() < minPrice)
//                    allFilteredProducts.remove(product);
//        }
//    }
//
//    public static ArrayList<String> processCurrentFilterEach() {
//        return allFilters;
//    }
//
//    public static boolean checkIsPutInPriceFilter(Product product) {
//        String priceFilter = null;
//        for (String allFilter : allFilters) {
//            if (allFilter.startsWith("BY_PRICE")) {
//                priceFilter = allFilter;
//                break;
//            }
//        }
//        if (priceFilter != null) {
//            int min = Integer.parseInt(priceFilter.substring(15).split(",")[0]);
//            int max = Integer.parseInt(priceFilter.substring(15).split(",")[1]);
//            if (product.getPrice() >= min && product.getPrice() <= max)
//                return true;
//        }
//        return false;
//    }
//
//    public static void processDeleteFilterEach(String selectedFilter) throws FilterNotExistsException, CategoryNotExistsException {
//        String upperCaseSelectedFilter = selectedFilter;
//        allFilters.remove(upperCaseSelectedFilter.toUpperCase());
//        for (Product allProduct : Product.getAllProducts()) {
//            if (selectedFilter.startsWith("BY_NAME_")) {
//                if (allProduct.getName().contains(selectedFilter.substring(8))) {
//                    allFilteredProducts.remove(allProduct);
//                }
//            } else if (selectedFilter.startsWith("BY_CATEGORY_")) {
//                try {
//                    if (ManagerAccountController.getCategoryProducts(selectedFilter.substring(12)).contains(allProduct.getName()) && !checkIsPutInPriceFilter(allProduct)) {
//                        allFilteredProducts.remove(allProduct);
//                    }
//                } catch (CategoryNotExistsException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                if (allProduct.getPrice() > Integer.parseInt(selectedFilter.substring(15).split(",")[1]) || allProduct.getPrice() < Integer.parseInt(selectedFilter.substring(15).split(",")[0])) {
//                    allFilteredProducts.add(allProduct);
//                }
//            }
//
//        }
//        if (allFilters.size() == 0 && allFilteredProducts.size() == 0)
//            allFilteredProducts.addAll(Product.getAllProducts());
//    }
//
//    public static ArrayList<String> processShowAvailableSortsEach() {
//        ArrayList<String> allSorts = new ArrayList<>();
//        for (Sort sort : Sort.values()) {
//            allSorts.add(sort.toString());
//        }
//        return allSorts;
//    }
//
//    public static void processSortEach(String availableSort, boolean isUp) throws SortNotExistsException {
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
//    }
//
//    public static void processSortByTime(boolean isUp) {
//        Collections.sort(allFilteredProducts, new TimeComparator());
//        if (!isUp) {
//            Collections.reverse(allFilteredProducts);
//        }
//    }
//
//    public static void processSortByScore(boolean isUp) {
//        Collections.sort(allFilteredProducts, new ScoreComparator());
//        if (!isUp) {
//            Collections.reverse(allFilteredProducts);
//        }
//    }
//
//    public static void processSortByView(boolean isUp) {
//        Collections.sort(allFilteredProducts, new ViewComparator());
//        if (!isUp) {
//            Collections.reverse(allFilteredProducts);
//        }
//    }
//
//    public static void processSortByPrice(boolean isUp) {
//        Collections.sort(allFilteredProducts, new PriceComparator());
//        if (!isUp) {
//            Collections.reverse(allFilteredProducts);
//        }
//    }
//
//    public static String processCurrentSortEach() {
//        return currentProductsSort.toString();
//    }
//
//    public static void processDisableSortEach() {
//        currentProductsSort = Sort.VIEW;
//    }
//
//    public static ArrayList<String> processShowProducts() {
//        ArrayList<String> allProducts = new ArrayList<>();
//        for (int i = allFilteredProducts.size() - 1; i >= 0; i--) {
//            allProducts.add(allFilteredProducts.get(i).getName());
//        }
//        if (allProducts.size() == 0) {
//            for (int i = Product.getAllProducts().size() - 1; i >= 0; i--) {
//                allProducts.add(Product.getAllProducts().get(i).getName());
//            }
//        }
//        return allProducts;
//    }
//
//    public static void processShowProduct(String productId) throws ProductIdNotExistsException {
//        ValidationController.checkProductExistence(productId);
////        ProductsPageController.selectedProduct.setViewTimes();
//        ProductsPageController.selectedProduct = Product.getProductByID(productId);
//    }
//
//
//}
package server.controller;


import server.controller.fields.ProductsPageAvailableFilters;
import server.controller.sortComparators.PriceComparator;
import server.controller.sortComparators.ScoreComparator;
import server.controller.sortComparators.TimeComparator;
import server.controller.sortComparators.ViewComparator;
import server.exception.FilterNotExistsException;
import server.exception.ProductIdNotExistsException;
import server.exception.SortNotExistsException;
import server.model.Category;
import server.model.Sort.Sort;
import server.model.product.Product;

import java.util.ArrayList;
import java.util.Collections;

public abstract class ProductsPageController {
    static Product selectedProduct = null;
    static Sort currentProductsSort = Sort.TIME_UP;
    static ArrayList<Product> allFilteredProducts = Product.getAllProducts();
    public static ArrayList<String> allFilters = new ArrayList<>();

//    static {
//        processSortByView(true);
//    }

    public static ArrayList<String> getAllFilters() {
        return allFilters;
    }

    public static void setSelectedProduct(Product selectedProduct) {
        ProductsPageController.selectedProduct = selectedProduct;
    }

    public static void setCurrentProductsSort(Sort currentProductsSort) {
        ProductsPageController.currentProductsSort = currentProductsSort;
    }

    public static Sort getCurrentProductsSort() {
        return currentProductsSort;
    }

    public static ArrayList<Product> getAllFilteredProducts() {
        return allFilteredProducts;
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

    public static void processFilter(String field, String filter, ArrayList<Product> categoryFilterProducts, ArrayList<Product> productsToShow, ArrayList<Category> allowedCategory)
            throws FilterNotExistsException {
        if (field.equals("BY_NAME")) {
            processFilterEachByName(filter, categoryFilterProducts, productsToShow, allowedCategory);
        } else if (field.equals("BY_PRICE")) {
            processFilterByPriceRange(Integer.parseInt(filter.split(",")[0]), Integer.parseInt(filter.split(",")[1]), categoryFilterProducts, productsToShow, allowedCategory);
        } else {
            throw new FilterNotExistsException("Wrong filter.");
        }
    }

    static void processFilterEachByName(String name, ArrayList<Product> categoryFilterProducts, ArrayList<Product> productsToShow, ArrayList<Category> allowedCategory) {
        ArrayList<Product> temp = new ArrayList<>(categoryFilterProducts);
        String filterName = null;
        for (String allFilter : allFilters) {
            if (allFilter.startsWith("BY_NAME_")) filterName = allFilter;
            if (allFilter.startsWith("BY_PRICE_RANGE_")) {
                int min = Integer.parseInt(allFilter.substring(15).split(",")[0]);
                int max = Integer.parseInt(allFilter.substring(15).split(",")[1]);
                for (Product product : categoryFilterProducts)
                    if (product.getPrice() < min || product.getPrice() > max || !allowedCategory.contains(product.getCategory())) {
                        temp.remove(product);
                    }
            }

        }
        if (filterName != null) {
            allFilters.remove(filterName);
        }
        allFilters.add("BY_NAME_" + name);
        for (int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).getName().startsWith(name) || !allowedCategory.contains(temp.get(i).getCategory())) {
                temp.remove(i);
                i--;
            }
        }
        productsToShow.clear();
        productsToShow.addAll(temp);

    }

    static void processFilterByPriceRange(int minPrice, int maxPrice, ArrayList<Product> categoryFilterProducts, ArrayList<Product> productsToShow, ArrayList<Category> allowedCategory) {
        ArrayList<Product> temp = new ArrayList<>(categoryFilterProducts);
        String filterPrice = null;
        for (String allFilter : allFilters) {
            if (allFilter.startsWith("BY_PRICE_RANGE_")) filterPrice = allFilter;
            if (allFilter.startsWith("BY_NAME_")) {
                for (Product categoryFilterProduct : categoryFilterProducts)
                    if (!categoryFilterProduct.getName().startsWith(allFilter.substring(8)) || !allowedCategory.contains(categoryFilterProduct.getCategory())) {
                        temp.remove(categoryFilterProduct);
                    }
            }
        }
        if (filterPrice != null)
            allFilters.remove(filterPrice);
        allFilters.add("BY_PRICE_RANGE_" + minPrice + "," + maxPrice);
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getPrice() > maxPrice || temp.get(i).getPrice() < minPrice || !allowedCategory.contains(temp.get(i).getCategory())) {
                temp.remove(i);
                i--;
            }
        }
        productsToShow.clear();
        productsToShow.addAll(temp);
    }

    public static ArrayList<String> processCurrentFilterEach() {
        return allFilters;
    }

//    public static void processDeleteFilterEach(String selectedFilter) throws FilterNotExistsException {
//        allFilters.remove(selectedFilter);
//        allFilteredProducts = Product.getAllProducts();
//        for (String filter : allFilters) {
//            if (filter.startsWith("BY_NAME_")) {
//                try {
//                    processFilter("BY_NAME", filter.substring(9));
//                } catch (CategoryNotExistsException e) {
//                    e.printStackTrace();
//                }
//            } else if (filter.startsWith("BY_CATEGORY_")) {
//                try {
//                    processFilter("BY_CATEGORY", filter.substring(12));
//                } catch (CategoryNotExistsException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                try {
//                    processFilter("BY_PRICE_RANGE", filter.substring(15));
//                } catch (CategoryNotExistsException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }

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

    public static void processSortByTime(boolean isUp, ArrayList<Product> productsToShow) {
        if (isUp)
            setCurrentProductsSort(Sort.TIME_UP);
        else setCurrentProductsSort(Sort.TIME_DOWN);
        Collections.sort(productsToShow, new TimeComparator());
        if (!isUp) {
            Collections.reverse(productsToShow);
        }
    }

    public static void processSortByScore(boolean isUp, ArrayList<Product> productsToShow) {
        if (isUp)
            setCurrentProductsSort(Sort.SCORE_UP);
        else setCurrentProductsSort(Sort.SCORE_DOWN);
        Collections.sort(productsToShow, new ScoreComparator());
        if (!isUp) {
            Collections.reverse(productsToShow);
        }
    }

    public static void processSortByView(boolean isUp, ArrayList<Product> productsToShow) {
        if (isUp)
            setCurrentProductsSort(Sort.VIEW_UP);
        else setCurrentProductsSort(Sort.VIEW_DOWN);
        Collections.sort(productsToShow, new ViewComparator());
        if (!isUp) {
            Collections.reverse(productsToShow);
        }
    }

    public static void processSortByPrice(boolean isUp, ArrayList<Product> productsToShow) {
        if (isUp)
            setCurrentProductsSort(Sort.PRICE_UP);
        else setCurrentProductsSort(Sort.PRICE_DOWN);
        Collections.sort(productsToShow, new PriceComparator());
        if (!isUp) {
            Collections.reverse(productsToShow);
        }
    }

    public static String processCurrentSortEach() {
        return currentProductsSort.toString();
    }

    public static void processDisableSortEach(ArrayList<Product> productsToShow) {
        processSortByTime(true, productsToShow);
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