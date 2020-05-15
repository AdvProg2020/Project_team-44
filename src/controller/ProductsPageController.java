package controller;

import exception.FilterNotExistsException;
import exception.ProductIdNotExistsException;
import exception.SortNotExistsException;
import model.product.Product;

public abstract class ProductsPageController {

    public static void processViewCategories() {

    }

    public static void processFiltering() {

    }

    public static void processShowAvailableFiltersEach() {

    }

    public static void processFilterEach(String availableFilter) throws FilterNotExistsException {
        checkFilterExistence(availableFilter);
        /*TODO*/

    }

    public static void processCurrentFilterEach() {

    }

    public static void processDeleteFilterEach(String selectedFilter) throws FilterNotExistsException {
        checkFilterExistence(selectedFilter);
        /*TODO*/

    }

    public static void processSorting() {

    }

    public static void processShowAvailableSortsEach() {

    }

    public static void processSortEach(String availableSort) throws SortNotExistsException {
        checkSortExistence(availableSort);
        /*TODO*/

    }

    public static void processCurrentSortEach() {

    }

    public static void processDisableSortEach() {

    }

    public static void processShowProducts() {

    }

    public static void processShowProduct(String productId) throws ProductIdNotExistsException {
        checkProductExistence(productId);
        /*TODO*/

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
