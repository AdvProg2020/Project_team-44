package controller;

import exception.FilterNotExistsException;
import exception.ProductIdNotExistsException;
import exception.SortNotExistsException;
import model.account.Seller;
import model.offer.Offer;
import model.product.Product;

import java.util.ArrayList;

public abstract class OffersPageController {
    public static ArrayList<String> processShowOffs() {
        return Offer.getAllOffs();
    }

    public static void processShowProduct(String productId) throws ProductIdNotExistsException {
        checkProductExistence(productId);
    }

    public static void processFiltering() {

    }

    public static void processShowAvailableFiltersEach() {

    }

    public static void processFilterEach(String availableFilter) throws FilterNotExistsException {
        checkFilterExistence(availableFilter);
    }

    public static void processCurrentFilterEach() {

    }

    public static void processDeleteFilterEach(String selectedFilter) throws FilterNotExistsException {
        checkFilterExistence(selectedFilter);
    }

    public static void processSorting() {

    }

    public static void processShowAvailableSortsEach() {

    }

    public static void processSortEach(String availableSort) throws SortNotExistsException {
        checkSortExistence(availableSort);
    }

    public static void processCurrentSortEach() {

    }

    public static void processDisableSortEach() {

    }

    public static void checkSortExistence(String availableSort) throws SortNotExistsException {
        for (String sort : allSorts()) {
            if (availableSort.equals(sort)) {
                return;
            }
        }
        throw new SortNotExistsException("Wrong sort.");
    }

    public static void checkFilterExistence(String availableFilter) throws FilterNotExistsException {
        for (String filter : allFilters()) {
            if (availableFilter.equals(filter)) {
                return;
            }
        }
        throw new FilterNotExistsException("Wrong filter.");
    }

    public static void checkProductExistence(String productId) throws ProductIdNotExistsException {
        for (Product product : Product.getAllProducts()) {
            if (productId.equals(product.getProductID())) {
                return;
            }
        }
        throw new ProductIdNotExistsException("No product exists with this Id.");
    }
}
