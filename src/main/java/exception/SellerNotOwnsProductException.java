package sample.exception;

public class SellerNotOwnsProductException extends Exception {
    public SellerNotOwnsProductException(String message) {
        super(message);
    }
}
