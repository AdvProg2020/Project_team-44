package sample.exception;

public class UsernameNotExistsException extends Exception {
    public UsernameNotExistsException(String message) {
        super(message);
    }
}
