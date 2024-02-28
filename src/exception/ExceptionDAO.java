package exception;

public class ExceptionDAO extends Exception{
    public ExceptionDAO(){}

    public ExceptionDAO(String message){
        super(message);
    }
}
