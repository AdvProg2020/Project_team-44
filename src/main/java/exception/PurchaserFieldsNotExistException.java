package sample.exception;

public class PurchaserFieldsNotExistException extends Exception {
    public PurchaserFieldsNotExistException(String message) {
        super(message);
    }
}
