package server.exception;

public class SellerNotOwnsProductException extends Exception {
    public SellerNotOwnsProductException(String message) {
        super(message);
    }
}
