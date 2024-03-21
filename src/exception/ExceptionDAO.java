package exception;

/**
 * Exceptions de la DAO
 */
public class ExceptionDAO extends Exception{
    private int gravite;

    /**
     * Getter de la gravité de l'exception
     * @return int gravité
     */
    public int getGravite(){
        return gravite;
    }
    /**
     * Constructeur par défaut
     */
    public ExceptionDAO(){}

    /**
     * Constructeur avec Message
     * @param message String
     */
    public ExceptionDAO(String message){
        super(message);
    }

    /**
     * Constructeur avec message et niveau de gravité
     * @param message String
     * @param gravite Int
     */
    public ExceptionDAO(String message, int gravite){
        super(message);
        this.gravite = gravite;
    }
}
