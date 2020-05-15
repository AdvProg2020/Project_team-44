package model.account;

import model.Category;
import model.CodedDiscount;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager extends Account {

    private static ArrayList<CodedDiscount> allDiscountCode = new ArrayList<>();
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
            userInfo.put(account.getType(), account.getUserName());
        }
        return userInfo;
    }


    public void editCodedDiscount() {

    }

    public static void createCodedDiscount() {

    }

    public void removeUserEach(Account toRemoveAccount) {
        allAccounts.remove(toRemoveAccount);
    }

    public void addManager(Account newManager) {
        new Manager(newManager.getUserName(), newManager.getFirstName(), newManager.getLastName(), newManager.getEmail(), newManager.getTelephoneNumber(), newManager.getPassword());
    }

    public void editCategoryEach(Category category) {

    }

    public void addCategory(Category category) {
        allCategories.add(new Category(category.getName()));
    }
}
