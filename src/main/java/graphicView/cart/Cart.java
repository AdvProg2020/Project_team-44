package graphicView.cart;

/**
 * require adding a Cart class property to Person class instances,
 * to show in CartPage
 * this class should be modified to model
 */
public class Cart {
    private String productName;
    private String seller;
    private String priceFee;
    private String quantity;
    private String amount;

    public Cart(String productName, String seller, String priceFee, String quantity, String amount) {
        this.productName = productName;
        this.seller = seller;
        this.priceFee = priceFee;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getPriceFee() {
        return priceFee;
    }

    public void setPriceFee(String priceFee) {
        this.priceFee = priceFee;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
