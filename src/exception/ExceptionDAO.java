package exception;

/**
 * Exceptions de la DAO
 */
public class ExceptionDAO extends Exception{
    /**
     * Constructeur par défaut
     */
    public ExceptionDAO(){}

    /**
     * Constructeur avce Message
     * @param message String
     */
    public ExceptionDAO(String message){
        super(message);
    }
}
