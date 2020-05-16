package controller;

import exception.FilterNotExistsException;
import exception.ProductIdNotExistsException;
import exception.SortNotExistsException;
import model.Sort.Sort;
import model.account.Seller;
import model.offer.Offer;
import model.product.Product;

import java.util.ArrayList;

public abstract class OffersPageController {
    public static ArrayList<String> processShowOffs() {
        return Offer.getAllOffers();
    }

    public static void processShowProduct(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        /*TODO*/

    }

    public static void processFiltering() {

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

    public static void processSorting() {

    }

    public static void processShowAvailableSortsEach() {

    }

    public static void processSortEach(String availableSort) throws SortNotExistsException {
        ValidationController.checkSortExistence(availableSort);
        /*TODO*/
    }

    public static void processCurrentSortEach() {

    }

    public static void processDisableSortEach() {

    }


}
