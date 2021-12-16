package ua.lits.l20spring.exception;

public class UserNotFoundException extends NotFoundException {

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
