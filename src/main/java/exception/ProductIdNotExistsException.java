package sample.exception;

public class ProductIdNotExistsException extends Exception {
    public ProductIdNotExistsException(String message) {
        super(message);
    }
}
