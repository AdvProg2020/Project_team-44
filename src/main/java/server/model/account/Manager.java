package server.model.account;

import com.google.gson.Gson;
import server.model.Category;
import server.model.CodedDiscount;
import server.model.offer.Offer;
import server.model.product.Product;
import server.model.requests.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Account {

    private static ArrayList<CodedDiscount> allDiscountCode = new ArrayList<>();
    private static ArrayList<Manager> allManagers = new ArrayList<>();
    private int wage;
    private int minAmount;

    public Manager(String userName, String firstName, String lastName, String eMail, String telephoneNumber, String password) {
        super(userName, firstName, lastName, eMail, telephoneNumber, password);
        super.createAndUpdateJson();
        allManagers.add(this);
        createAndUpdateJson();
    }

    public static Manager getManagerByUsername(String username) {
        for (Manager manager : allManagers) {
            if (manager.getUserName().equals(username)) {
                return manager;
            }
        }
        return null;
    }

    public static ArrayList<Manager> getAllManagers() {
        return allManagers;
    }

    public static void setAllManagers(ArrayList<Manager> allManagers) {
        Manager.allManagers = allManagers;
    }

    public void createAndUpdateJson() {
        try {
            Writer writer = new FileWriter("src/server.main/resources/Accounts/Managers/" + this.getUserName() + ".json");
            new Gson().toJson(this, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getWage() {
        return wage;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
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

    public void accept(String requestId) throws ParseException {
        Request.getRequestById(requestId).setStatus(RequestStatus.VERIFIED);
        String firstPartId = getMatcher(requestId, "(.*)_\\d+").group(1);
        if (firstPartId.equalsIgnoreCase("RequestForAddOff")) {
            doRequestForAddOf(requestId);
        } else if (firstPartId.equalsIgnoreCase("RequestForAddProduct")) {
            doRequestForAddProduct(requestId);
        } else if (firstPartId.equalsIgnoreCase("RequestForEditOff")) {
            doRequestForEditOff(requestId);
        } else if (firstPartId.equalsIgnoreCase("RequestForEditProduct")) {
            doRequestForEditProduct(requestId);
        } else if (firstPartId.equalsIgnoreCase("RequestForRemoveProduct")) {
            doRequestForRemoveProduct(requestId);
        } else if (firstPartId.equalsIgnoreCase("RequestForSeller")) {
            doRequestForSeller(requestId);
        }
    }

    public void doRequestForAddOf(String requestId) {
        RequestForAddOff request = (RequestForAddOff) RequestForAddOff.getRequestById(requestId);
        new Offer(request.getProductList(), request.getInitialDate(), request.getFinalDate(), request.getDiscountPercentage());
    }

    public void doRequestForAddProduct(String requestId) {
        RequestForAddProduct request = ((RequestForAddProduct) Request.getRequestById(requestId));
        if (getProductWithInfo(request.getCategory(), request.getName(), request.getPrice(), request.getExplanationText()) == null) {
            new Product(request.getCategory(), request.getName(), request.getSeller().getCompanyName(), request.getPrice(), request.getExplanationText(), request.getCategory().getImageName());
        } else {
            getProductWithInfo(request.getCategory(), request.getName(), request.getPrice(), request.getExplanationText()).getAllSellers().add(request.getSeller());
        }
    }

    public void doRequestForEditOff(String requestId) throws ParseException {
        RequestForEditOff request = (RequestForEditOff) RequestForEditOff.getRequestById(requestId);
        request.getOffer().setProductList(request.getProductList());
        request.getOffer().setInitialDate(request.getInitialDate());
        request.getOffer().setFinalDate(request.getFinalDate());
        request.getOffer().setDiscountPercentage(request.getDiscountPercentage());
    }

    public void doRequestForEditProduct(String requestId) {
        RequestForEditProduct request = (RequestForEditProduct) RequestForEditProduct.getRequestById(requestId);
        request.getProduct().setName(request.getName());
        request.getProduct().setCompanyName(request.getCompanyName());
        request.getProduct().setPrice(request.getPrice());
        request.getProduct().setExplanationText(request.getExplanationText());
        request.getProduct().setImageName(request.getImageName());
    }

    public void doRequestForRemoveProduct(String requestId) {
        RequestForRemoveProduct request = (RequestForRemoveProduct) RequestForRemoveProduct.getRequestById(requestId);
        Product.getAllProducts().remove(request.getProduct());
//        Product product = request.getProduct();
//        product = null;
        // da fuq?
    }

    public void doRequestForSeller(String requestId) {
        RequestForSeller request = (RequestForSeller) RequestForSeller.getRequestById(requestId);
        new Seller(request.getUserName(), request.getFirstName(), request.getLastName(), request.getEMail(), request.getTelephoneNumber(), request.getPassword(), request.getCompanyName(), request.getCompanyAddress(), request.getCompanyTelephone());
    }

    public Product getProductWithInfo(Category category, String name, double price, String explanationText) {
        System.out.println(category.getName());
        System.out.println(name);
        System.out.println(price);
        System.out.println(explanationText);
        for (Product allProduct : Product.getAllProducts()) {
            System.out.println("   in for     :  " + allProduct.getName() + "  cat   " + allProduct.getCategory().getName());
            System.out.println("   in for     :  " + allProduct.getName() + "  name  " + allProduct.getName());
            System.out.println("   in for     :  " + allProduct.getName() + " price  " + allProduct.getPrice());
            System.out.println("   in for     :  " + allProduct.getName() + " explanation  " + allProduct.getExplanationText());

            if (allProduct.getCategory().getName().equals(category.getName()) && allProduct.getName().equals(name) && allProduct.getPrice() == price && allProduct.getExplanationText().equals(explanationText)) {

                return allProduct;
            }
        }
        return null;
    }

    public void decline(String requestId) {
        Request request = Request.getRequestById(requestId);
        request.setStatus(RequestStatus.DECLINED);
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

    public static void createCodedDiscount(Date initialDate, Date finalDate, int discountPercentage, int maxAuthorizedPrice) {
        new CodedDiscount(initialDate, finalDate, discountPercentage, maxAuthorizedPrice);
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

    public void addCategory(String name, Category parent, String imageName) {
        new Category(name, parent, imageName);
    }
}
