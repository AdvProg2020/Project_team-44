package server.exception;

public class ProductNotExistsInCartException extends Exception {
    public ProductNotExistsInCartException(String message) {
        super(message);
    }
}
