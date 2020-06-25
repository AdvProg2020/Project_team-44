package exception;

public class NotEnoughMoneyToPayException extends Exception {
    public NotEnoughMoneyToPayException(String message) {
        super(message);
    }
}
