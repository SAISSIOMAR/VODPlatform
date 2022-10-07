package exceptions;


/**
 * This class designed to throw exception message when the client
 * fails to sign up for example creating the same account twice
 */

public class SignUpException extends Exception{

    public SignUpException(String mssg){
        super(mssg);
    }
}
