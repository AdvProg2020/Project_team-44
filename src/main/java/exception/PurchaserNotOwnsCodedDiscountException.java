package sample.exception;

public class PurchaserNotOwnsCodedDiscountException extends Exception {
    public PurchaserNotOwnsCodedDiscountException(String message) {
        super(message);
    }
}
