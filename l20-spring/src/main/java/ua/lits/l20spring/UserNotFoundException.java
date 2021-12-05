package ua.lits.l20spring;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String fieldName, String value) {
        super(String.format("User with %s='%s' not found", fieldName, value));
    }

    public UserNotFoundException(String fieldName, Long value) {
        super(String.format("User with %s=%d not found", fieldName, value));
    }
}
