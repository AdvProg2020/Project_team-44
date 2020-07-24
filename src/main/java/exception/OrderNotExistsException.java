package exception;

public class OrderNotExistsException extends Exception {
    public OrderNotExistsException(String message) {
        super(message);
    }
}
