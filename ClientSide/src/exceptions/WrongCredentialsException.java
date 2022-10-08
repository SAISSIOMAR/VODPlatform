package exceptions;

/**
 * Exception for invalid crediantials while login
 */
public class WrongCredentialsException extends Exception {
    public WrongCredentialsException(String message) {
        super(message);
    }
}
