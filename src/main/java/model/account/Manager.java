package model.account;

import model.Category;
import model.CodedDiscount;
import model.requests.Request;
import model.requests.RequestStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Account {

    private static ArrayList<CodedDiscount> allDiscountCode = new ArrayList<>();
    private static ArrayList<Manager> allManagers = new ArrayList<>();

    public Manager(String userName, String firstName, String lastName, String eMail, String telephoneNumber, String password) {
        super(userName, firstName, lastName, eMail, telephoneNumber, password);
        allManagers.add(this);
    }

    public ArrayList<String> getRequestIdLists() {
        ArrayList<String> IdLists = new ArrayList<>();
        for (Request allRequest : Request.getAllRequests()) {
            IdLists.add(allRequest.getRequestId());
        }
        return IdLists;
    }

    public Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher;
    }

    public void accept(String requestId) {
        Request.getRequestById(requestId).setStatus(RequestStatus.VERIFIED);
        String firstPartId = getMatcher(requestId, "(.*)_\\d+").group(1);
        if (firstPartId.equalsIgnoreCase("RequestForAddOff")){

        }else if (firstPartId.equalsIgnoreCase("RequestForAddProduct")){

        }else if (firstPartId.equalsIgnoreCase("RequestForEditOff")){

        }else if (firstPartId.equalsIgnoreCase("RequestForEditProduct")){

        }else if (firstPartId.equalsIgnoreCase("RequestForRemoveProduct")){

        }else if (firstPartId.equalsIgnoreCase("RequestForSeller")){

        }
    }

    public void decline(String requestId) {
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
    }
}
