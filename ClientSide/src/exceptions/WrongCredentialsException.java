package exceptions;

/**
 * This class designed to throw exception message when the client
 * enters wrong credentials
 */

public class WrongCredentialsException extends Exception{

    public WrongCredentialsException(String message) {
        super(message);
    }

}
