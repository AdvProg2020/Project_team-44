package server.controller;


import server.controller.fields.OfferPageAvailableFilters;
import server.controller.sortComparators.PriceComparator;
import server.controller.sortComparators.ScoreComparator;
import server.controller.sortComparators.TimeComparator;
import server.controller.sortComparators.ViewComparator;
import server.exception.CategoryNotExistsException;
import server.exception.FilterNotExistsException;
import server.exception.ProductIdNotExistsException;
import server.exception.SortNotExistsException;
import server.model.Sort.Sort;
import server.model.offer.Offer;
import server.model.product.Product;

import java.util.ArrayList;
import java.util.Collections;

public abstract class OffersPageController {
    static Sort currentOffersSort = Sort.VIEW_UP;
    static ArrayList<Product> allFilteredOffersProducts = new ArrayList<>();
    static ArrayList<String> allFilters = new ArrayList<>();

    static {
        for (Offer offer : Offer.getAllOffers()) {
            allFilteredOffersProducts.addAll(offer.getProductList());
        }
        processSortByView(true);
    }

    public static ArrayList<String> processShowOffs() {
        ArrayList<String> offersInfo = new ArrayList<>();
        for (Offer offer : Offer.getAllOffers()) {
            offersInfo.addAll(offer.getOfferInfo());
            for (Product product : offer.getProductList()) {
                offersInfo.add(product.getName());
            }
        }
        return offersInfo;
    }

    public static void processShowProduct(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
//        ProductsPageController.selectedProduct.setViewTimes();
        ProductsPageController.selectedProduct = Product.getProductByID(productId);
    }

    public static ArrayList<String> processShowAvailableFiltersEach() {
        ArrayList<String> allFilters = new ArrayList<>();
        for (OfferPageAvailableFilters filter : OfferPageAvailableFilters.values()) {
            allFilters.add(filter.toString());
        }
        return allFilters;
    }

    public static void processFilterEach(String field, String filter) throws FilterNotExistsException, CategoryNotExistsException {
        ValidationController.checkFilterExistence(filter);
        if (field.equals("BY_NAME")) {
            processFilterEachByName(filter);
        } else if (field.equals("BY_CATEGORY")) {
            processFilterByCategory(filter);
        } else if (field.equals("BY_PRICE")) {
            processFilterByPriceRange(Integer.parseInt(filter.split(",")[0]), Integer.parseInt(filter.split(",")[1]));
        } else if (field.equals("BY_DISCOUNT_PERCENTAGE")) {
            processFilterByDiscountPercentageRange(Integer.parseInt(filter.split(",")[0]), Integer.parseInt(filter.split(",")[1]));
        } else {
            throw new FilterNotExistsException("Wrong filter.");
        }
    }

    static void processFilterEachByName(String availableFilter) {
        allFilters.add("BY_NAME");
        ArrayList<Product> tempFilteredProducts = new ArrayList<>(allFilteredOffersProducts);
        allFilteredOffersProducts.clear();
        for (Product product : tempFilteredProducts) {
            if (product.getName().contains(availableFilter)) {
                allFilteredOffersProducts.add(product);
            }
        }
    }

    static void processFilterByCategory(String categoryFilter) throws CategoryNotExistsException {
        ValidationController.checkCategoryExistence(categoryFilter);
        allFilters.add("BY_" + categoryFilter.toUpperCase() + "_CATEGORY");
        ArrayList<Product> tempFilteredProducts = new ArrayList<>(allFilteredOffersProducts);
        allFilteredOffersProducts.clear();
        for (Product product : tempFilteredProducts) {
            if (ManagerAccountController.getCategoryProducts(categoryFilter).contains(product.getName())) {
                allFilteredOffersProducts.add(product);
            }
        }
    }

    static void processFilterByPriceRange(int minPrice, int maxPrice) {
        allFilters.add("BY_PRICE_RANGE_( " + minPrice + " , " + maxPrice + " )");
        ArrayList<Product> tempFilteredProducts = new ArrayList<>(allFilteredOffersProducts);
        allFilteredOffersProducts.clear();
        for (Product product : tempFilteredProducts) {
            if (product.getPrice() <= maxPrice && product.getPrice() >= minPrice) {
                allFilteredOffersProducts.add(product);
            }
        }
    }

    static void processFilterByDiscountPercentageRange(int minPercentage, int maxPercentage) {
        allFilters.add("BY_DISCOUNT_PERCENTAGE_RANGE_( " + minPercentage + " , " + maxPercentage + " )");
        ArrayList<Product> tempFilteredProducts = new ArrayList<>(allFilteredOffersProducts);
        allFilteredOffersProducts.clear();
        for (Product product : tempFilteredProducts) {
            if (product.getOffer().getDiscountPercentage() <= maxPercentage
                    && product.getOffer().getDiscountPercentage() >= minPercentage) {
                allFilteredOffersProducts.add(product);
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
        ValidationController.checkSortExistence(availableSort);
        if (availableSort.equals(Sort.TIME_UP)) {
            currentOffersSort = Sort.TIME_UP;
            processSortByTime(isUp);
        } else if (availableSort.equals(Sort.TIME_DOWN)) {
            currentOffersSort = Sort.TIME_DOWN;
            processSortByTime(isUp);
        } else if (availableSort.equals(Sort.SCORE_UP)) {
            currentOffersSort = Sort.SCORE_UP;
            processSortByScore(isUp);
        } else if (availableSort.equals(Sort.SCORE_DOWN)) {
            currentOffersSort = Sort.SCORE_DOWN;
            processSortByScore(isUp);
        } else if (availableSort.equals(Sort.PRICE_UP)) {
            currentOffersSort = Sort.PRICE_UP;
            processSortByPrice(isUp);
        } else if (availableSort.equals(Sort.PRICE_DOWN)) {
            currentOffersSort = Sort.PRICE_DOWN;
            processSortByPrice(isUp);
        } else if (availableSort.equals(Sort.VIEW_UP)) {
            currentOffersSort = Sort.VIEW_UP;
            processSortByView(isUp);
        } else {
            currentOffersSort = Sort.VIEW_DOWN;
            processSortByView(isUp);
        }
    }

    public static void processSortByTime(boolean isUp) {
        Collections.sort(allFilteredOffersProducts, new TimeComparator());
        if (!isUp) {
            Collections.reverse(allFilteredOffersProducts);
        }
    }

    public static void processSortByScore(boolean isUp) {
        Collections.sort(allFilteredOffersProducts, new ScoreComparator());
        if (!isUp) {
            Collections.reverse(allFilteredOffersProducts);
        }
    }

    public static void processSortByView(boolean isUp) {
        Collections.sort(allFilteredOffersProducts, new ViewComparator());
        if (!isUp) {
            Collections.reverse(allFilteredOffersProducts);
        }
    }

    public static void processSortByPrice(boolean isUp) {
        Collections.sort(allFilteredOffersProducts, new PriceComparator());
        if (!isUp) {
            Collections.reverse(allFilteredOffersProducts);
        }
    }

    public static String processCurrentSortEach() {
        return currentOffersSort.toString();
    }

    public static void processDisableSortEach() {
        currentOffersSort = Sort.VIEW_UP;
    }


}
