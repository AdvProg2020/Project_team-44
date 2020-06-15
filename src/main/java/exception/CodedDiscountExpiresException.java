package sample.exception;

public class CodedDiscountExpiresException extends Exception {
    public CodedDiscountExpiresException(String message) {
        super(message);
    }
}
