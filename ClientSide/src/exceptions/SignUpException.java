package exceptions;


public class SignUpException extends Exception{
    public SignUpException(String errorMessage) {
        super(errorMessage);
    }
}
