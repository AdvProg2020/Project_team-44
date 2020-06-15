package sample.exception;

public class RequestNotExistsException extends Exception {
    public RequestNotExistsException(String message) {
        super(message);
    }
}
