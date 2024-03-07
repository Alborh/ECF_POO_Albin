package exception;

/**
 * Excpetions des controleur
 */
public class ExceptionControleur extends Exception{
    /**
     * Constructeur par défaut
     */
    public ExceptionControleur(){}

    /**
     * Costructeur avec message
     * @param message String
     */
    public ExceptionControleur(String message){
        super(message);
    }
}
