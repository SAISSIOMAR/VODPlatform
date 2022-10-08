package exceptions;

/**
 * Exception when sign up failed
 */
public class SignUpException extends Exception{
    public SignUpException(String errorMessage) {
        super(errorMessage);
    }
}
