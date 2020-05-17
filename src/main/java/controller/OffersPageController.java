package controller;

import exception.FilterNotExistsException;
import exception.ProductIdNotExistsException;
import exception.SortNotExistsException;
import model.Sort.Sort;
import model.offer.Offer;

import java.util.ArrayList;

public abstract class OffersPageController {
    static Sort currentOffersSort = Sort.VIEW;

    public static ArrayList<String> processShowOffs() {
        return Offer.getAllOffersId();
    }

    public static void processShowProduct(String productId) throws ProductIdNotExistsException {
        ValidationController.checkProductExistence(productId);
        /*TODO*/

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

    public static Sort[] processShowAvailableSortsEach() {
        return Sort.values();
    }

    public static void processSortEach(String availableSort) throws SortNotExistsException {
        ValidationController.checkSortExistence(availableSort);
        if (availableSort.equals(Sort.TIME)) {
            currentOffersSort = Sort.TIME;
        } else if (availableSort.equals(Sort.SCORE)) {
            currentOffersSort = Sort.SCORE;
        } else {
            currentOffersSort = Sort.VIEW;
        }
        /*TODO*/
    }

    public static String processCurrentSortEach() {
        return currentOffersSort.toString();
    }

    public static void processDisableSortEach() {
        currentOffersSort = Sort.VIEW;
    }


}
