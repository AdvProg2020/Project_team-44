package model.account;

import model.Category;
import model.CodedDiscount;

import java.util.ArrayList;

public class Manager extends Account {

    private static ArrayList<CodedDiscount> allDiscountCode = new ArrayList<>();
    private static ArrayList<Category> allCategories = new ArrayList<>();
    private static ArrayList<Manager> allManagers = new ArrayList<>();

    public Manager(String username, String firstName, String lastName, String email, String telephoneNumber, String password) {
        super(username, firstName, lastName, email, telephoneNumber, password);
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

    public void showAllUsers() {

    }


    public void editCodedDiscount() {

    }

    public void createCodedDiscount() {

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
