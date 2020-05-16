package model.account;

import model.Category;
import model.CodedDiscount;
import model.requests.Request;
import model.requests.RequestStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Manager extends Account {

    private static ArrayList<CodedDiscount> allDiscountCode = new ArrayList<>();
    private static ArrayList<Manager> allManagers = new ArrayList<>();

    public Manager(String username, String firstName, String secondName, String email, String telephoneNumber, String password) {
        super(username, firstName, secondName, email, telephoneNumber, password);
//        allManagers.add(this);
    }

    public ArrayList<Integer> getRequestIdLists() {
        ArrayList<Integer> IdLists = new ArrayList<>();
        for (Request allRequest : Request.getAllRequests()) {
            IdLists.add(allRequest.getRequestId());
        }
        return IdLists;
    }

    public void accept(int requestId) {
        Request.getRequestById(requestId).setStatus(RequestStatus.VERIFIED);
    }

    public void decline(int requestId) {
        Request.getRequestById(requestId).setStatus(RequestStatus.DECLINED);
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

    public void editCategoryEach(String categoryName, String field, String oldValue, String newValue) {
        if (field.equalsIgnoreCase("name")) {
            Category.getCategoryByName(categoryName).setName(newValue);
        } else if (field.equalsIgnoreCase("attributes")) {
            if (oldValue == null) {
                Category.getCategoryByName(categoryName).getAttributes().add(newValue);
            } else {
                ArrayList<String> allAttributes = new ArrayList<>();
                for (String attribute : Category.getCategoryByName(categoryName).getAttributes()) {
                    if (attribute.equalsIgnoreCase(oldValue)) {
                        allAttributes.add(newValue);
                    } else allAttributes.add(attribute);
                }
            }
        }
    }

    public void addCategory(String name) {
        new Category(name);
        /////ask about attributes..............
    }
}
