package exceptions;

/**
 * an exception when movie is not found
 */
public class IsbnNotFoundException extends Exception{
    public IsbnNotFoundException(String message) {
        super(message);
    }
}
