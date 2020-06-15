package sample.exception;

public class ProductAlreadyExistsInCartException extends Exception {
    public ProductAlreadyExistsInCartException(String message) {
        super(message);
    }
}
