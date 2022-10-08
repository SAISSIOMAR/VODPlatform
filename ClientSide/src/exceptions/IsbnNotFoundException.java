package exceptions;

/**
 * an exception when isbn not found
 */
public class IsbnNotFoundException extends Exception{
    public IsbnNotFoundException(String message) {
        super(message);
    }
}
