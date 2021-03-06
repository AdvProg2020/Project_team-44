package server.model.requests;

import server.model.account.Seller;
import server.model.product.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RequestForAddOff extends Request {
    private Seller seller;
    private ArrayList<Product> productList;
    private Date initialDate;
    private Date finalDate;
    private int discountPercentage;
    private static ArrayList<RequestForAddOff> allRequestsForAddOff = new ArrayList<>();

    public static ArrayList<RequestForAddOff> getAllRequestsForAddOff() {
        return allRequestsForAddOff;
    }

    public RequestForAddOff(Seller seller, ArrayList<Product> productList, Date initialDate, Date finalDate, int discountPercentage) {
        this.seller = seller;
        this.productList = productList;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.discountPercentage = discountPercentage;
        allRequestsForAddOff.add(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    @Override
    public String produceRequestId() {
        String id = "RequestForAddOff_" + super.produceRequestId();
        return id;
    }

    @Override
    public ArrayList<String> getRequestDetails() {
        ArrayList<String> details = super.getRequestDetails();
        details.add(this.getSeller().getFirstName());
        details.add(this.getSeller().getLastName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(this.getInitialDate());
        details.add(strDate);
        strDate = dateFormat.format(this.getFinalDate());
        details.add(strDate);
        details.add(String.valueOf(this.getDiscountPercentage()));
        return details;
    }

    @Override
    public String getRequestId() {
        return super.getRequestId();
    }
}