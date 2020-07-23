package server.exception;

public class ProductAlreadyExistsInCartException extends Exception {
    public ProductAlreadyExistsInCartException(String message) {
        super(message);
    }
}
