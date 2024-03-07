package exception;

/**
 * Exceptions Métier
 */
public class ExceptionMetier extends Exception{
    /**
     * Constructeur par défaut
     */
    public ExceptionMetier(){}

    /**
     * Constructeur avce message
     * @param message String
     */
    public ExceptionMetier(String message){
        super(message);
    }
}
