package sample.exception;

public class SellerFieldsNotExistException extends Exception {
    public SellerFieldsNotExistException(String message) {
        super(message);
    }
}
