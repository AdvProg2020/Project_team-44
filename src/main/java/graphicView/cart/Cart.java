package graphicView.cart;

/**
 * require adding a Cart class property to Person class instances,
 * to show in CartPage
 * this class should be modified to model
 */
public class Cart {
    private String productName;
    private Integer priceFee;

    public Cart(String productName, Integer priceFee) {
        this.productName = productName;
        this.priceFee = priceFee;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPriceFee() {
        return priceFee;
    }

    public void setPriceFee(Integer priceFee) {
        this.priceFee = priceFee;
    }
}
