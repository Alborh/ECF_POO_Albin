package exception;

public class ExceptionMetier extends Exception{
    public ExceptionMetier(){    }
    public ExceptionMetier(String message){
        super(message);
    }
}
