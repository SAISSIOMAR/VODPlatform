package exceptions;

public class MovieNotFoundException extends Exception{
    public MovieNotFoundException(String mssg){
        super(mssg);
}
}
