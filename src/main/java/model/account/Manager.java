package model.account;

import com.google.gson.Gson;
import model.Category;
import model.CodedDiscount;
import model.offer.Offer;
import model.product.Product;
import model.requests.*;

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

    public Manager(String userName, String firstName, String lastName, String eMail, String telephoneNumber, String password) {
        super(userName, firstName, lastName, eMail, telephoneNumber, password);
        allManagers.add(this);
        createAndUpdateJson();
    }

    public static ArrayList<Manager> getAllManagers() {
        return allManagers;
    }

    public static void setAllManagers(ArrayList<Manager> allManagers) {
        Manager.allManagers = allManagers;
    }

    public void createAndUpdateJson() {
        try {
            Writer writer = new FileWriter("src/main/resources/Accounts/Managers/" + this.getUserName() + ".json");
            new Gson().toJson(this, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        System.out.println("alan");
    }

    public void doRequestForAddOf(String requestId) {
        RequestForAddOff request = (RequestForAddOff) RequestForAddOff.getRequestById(requestId);
        new Offer(request.getProductList(), request.getInitialDate(), request.getFinalDate(), request.getDiscountPercentage());
    }

    public void doRequestForAddProduct(String requestId) {
        RequestForAddProduct request = (RequestForAddProduct) RequestForAddProduct.getRequestById(requestId);
        if (getProductWithInfo(request.getCategory(), request.getName(), request.getPrice(), request.getExplanationText()) == null) {
            new Product(request.getCategory(), request.getName(), request.getSeller().getCompanyName(), request.getPrice(), request.getExplanationText(), request.getCategory().getImageName());

        } else {
            getProductWithInfo(request.getCategory(), request.getName(), request.getPrice(), request.getExplanationText()).getAllSellers().add(request.getSeller());
        }
    }

    public void doRequestForEditOff(String requestId) throws ParseException {
        RequestForEditOff request = (RequestForEditOff) RequestForEditOff.getRequestById(requestId);
        if (request.getField().equalsIgnoreCase("productList")) {
            ArrayList<Product> productList = new ArrayList<>();
            for (String id : request.getNewValue()) {
                productList.add(Product.getProductByID(id));
            }
            request.getOffer().setProductList(productList);
        } else if (request.getField().equalsIgnoreCase("initialDate")) {
            Date date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(request.getNewValue().get(0));
            request.getOffer().setInitialDate(date);
        } else if (request.getField().equalsIgnoreCase("finalDate")) {
            Date date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(request.getNewValue().get(0));
            request.getOffer().setFinalDate(date);
        } else if (request.getField().equalsIgnoreCase("discountPercentage")) {
            request.getOffer().setDiscountPercentage(Integer.parseInt(request.getNewValue().get(0)));
        }
    }

    public void doRequestForEditProduct(String requestId) {
        RequestForEditProduct request = (RequestForEditProduct) RequestForEditProduct.getRequestById(requestId);
        if (request.getField().equalsIgnoreCase("name")) {
            request.getProduct().setName(request.getNewValue());
        } else if (request.getField().equalsIgnoreCase("companyName")) {
            request.getProduct().setCompanyName(request.getNewValue());
        } else if (request.getField().equalsIgnoreCase("price")) {
            request.getProduct().setPrice(Double.parseDouble(request.getNewValue()));
        } else if (request.getField().equalsIgnoreCase("explanationText")) {
            request.getProduct().setExplanationText(request.getNewValue());
        } else if (request.getField().equalsIgnoreCase("isAvailable")) {
            if (request.getNewValue() == "yes") {
                request.getProduct().setAvailable(true);
            } else if (request.getNewValue() == "no")
                request.getProduct().setAvailable(false);
        }
    }

    public void doRequestForRemoveProduct(String requestId) {
        RequestForRemoveProduct request = (RequestForRemoveProduct) RequestForRemoveProduct.getRequestById(requestId);
        //Product.getAllProducts().remove(request.getProduct());
        Product product = request.getProduct();
        product = null;
    }

    public void doRequestForSeller(String requestId) {
        RequestForSeller request = (RequestForSeller) RequestForSeller.getRequestById(requestId);
        new Seller(request.getUserName(), request.getFirstName(), request.getLastName(), request.getEMail(), request.getTelephoneNumber(), request.getPassword(), request.getCompanyName(), request.getCompanyAddress(), request.getCompanyTelephone());
    }

    public Product getProductWithInfo(Category category, String name, double price, String explanationText) {
        for (Product allProduct : Product.getAllProducts()) {
            if (allProduct.getCategory().equals(category) && allProduct.getName().equals(name) && allProduct.getPrice() == price && allProduct.getExplanationText().equals(explanationText)) {
                return allProduct;
            }
        }
        return null;
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
