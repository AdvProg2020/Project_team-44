package model.account;

import model.Category;
import model.CodedDiscount;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager extends Account {

    private static ArrayList<CodedDiscount> allDiscountCode = new ArrayList<>();
    private static ArrayList<Account> allAccounts = new ArrayList<>();
    private static ArrayList<Category> allCategories = new ArrayList<>();
    private static ArrayList<Manager> allManagers = new ArrayList<>();

    public Manager(String username, String firstName, String secondName, String email, String telephoneNumber, String password) {
        super(username, firstName, secondName, email, telephoneNumber, password);
//        allManagers.add(this);
    }

    @Override
    public ArrayList<String> getInfo() {
        return super.getInfo();
    }

    @Override
    public void editInfo(String field, String newValue) {
        super.editInfo(field, newValue);
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

    public static HashMap<String, String> showAllUsers() {
        HashMap<String, String> userInfo = new HashMap<>();
        for (Account account : Account.getAllAccounts()) {
            userInfo.put(account.getType(), account.getUsername());
        }
        return userInfo;
    }


    public void editCodedDiscount() {

    }

    public static void createCodedDiscount() {

    }

    public void removeUserEach(Account toRemoveAccount) {

    }

    public void addManager(Account newManager) {

    }

    public void editCategoryEach(Category category) {

    }

    public void addCategory(Category category) {

    }
}
