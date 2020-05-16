package model.account;

import model.Category;
import model.CodedDiscount;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Manager extends Account {

    private static ArrayList<CodedDiscount> allDiscountCode = new ArrayList<>();
    //private static ArrayList<Category> allCategories = new ArrayList<>();
    private static ArrayList<Manager> allManagers = new ArrayList<>();

    public Manager(String username, String firstName, String secondName, String email, String telephoneNumber, String password) {
        super(username, firstName, secondName, email, telephoneNumber, password);
//        allManagers.add(this);
    }

    public void seeRequestList() {

    }

    public void showSellerRegistrationRequests() {

    }

    public void showAddProductRequests() {

    }

    public void showEditProductRequests() {

    }

    public void showAddOfferRequests() {

    }

    public void showEditOfferRequests() {

    }

    public static ArrayList<String> showAllUsers() {
        ArrayList<String> info = new ArrayList<>();
        for (Account allAccount : allAccounts) {
            info.add(allAccount.getUserName());
        }
        return info;
    }

    public static void editCodedDiscount(String discountCode, String field, String newValue) throws ParseException {
        if (field.equalsIgnoreCase("initialDate")) {
            Date date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(newValue);
            CodedDiscount.getCodedDiscountByCode(discountCode).setInitialDate(date);
        } else if (field.equalsIgnoreCase("finalDate")) {
            Date date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(newValue);
            CodedDiscount.getCodedDiscountByCode(discountCode).setFinalDate(date);
        } else if (field.equalsIgnoreCase("discountPercentage")) {
            CodedDiscount.getCodedDiscountByCode(discountCode).setDiscountPercentage(Integer.parseInt(newValue));
        } else if (field.equalsIgnoreCase("maxAuthorizedPrice")) {
            CodedDiscount.getCodedDiscountByCode(discountCode).setMaxAuthorizedPrice(Integer.parseInt(newValue));
        }
    }

    public static void createCodedDiscount(String discountCode, Date initialDate, Date finalDate, int discountPercentage, int maxAuthorizedPrice) {
        new CodedDiscount(discountCode, initialDate, finalDate, discountPercentage, maxAuthorizedPrice);
    }

    public void addManager(Account newManager) {
        new Manager(newManager.getUserName(), newManager.getFirstName(), newManager.getLastName(), newManager.getEmail(), newManager.getTelephoneNumber(), newManager.getPassword());
    }

    public void editCategoryEach(Category category) {

    }

    public void addCategory(Category category) {
        Category.getAllCategories().add(new Category(category.getName()));
    }
}
