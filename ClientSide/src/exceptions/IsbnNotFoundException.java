package exceptions;


public class IsbnNotFoundException extends Exception{
    public IsbnNotFoundException(String message) {
        super(message);
    }
}
