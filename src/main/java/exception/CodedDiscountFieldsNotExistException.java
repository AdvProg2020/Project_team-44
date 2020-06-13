package sample.exception;

public class CodedDiscountFieldsNotExistException extends Exception{
    public CodedDiscountFieldsNotExistException(String message) {
        super(message);
    }
}
