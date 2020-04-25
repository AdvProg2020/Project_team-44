package model.account;

import model.Category;
import model.CodedDiscount;

import java.util.ArrayList;

public class Manager extends Account {

    private static ArrayList<CodedDiscount> allDiscountCode = new ArrayList<>();
    private static ArrayList<Account> allAccounts = new ArrayList<>();
    private static ArrayList<Category> allCategories = new ArrayList<>();
    private static ArrayList<Manager> allManagers = new ArrayList<>();

    public Manager(String username, String firstName, String secondName, String email, String telephoneNumber, String password) {
        super(username, firstName, secondName, email, telephoneNumber, password);
        allManagers.add(this);
    }

    public void getInfo() {

    }

    public void editInfo() {

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

    }

    public void addManager(Account newManager) {

    }

    public void editCategoryEach(Category category) {

    }

    public void addCategory(Category category) {

    }
}
