package metier;

import exception.ExceptionMetier;
import log.LoggerPoo;

import java.util.logging.Level;

/**
 * Type de société Client avec chiffre d'affaires et nombre d'employés
 */
public class Client extends Societe{
    private double chiffreDAffaire;
    private int nbEmploye;

    /**
     * Setter du chiffre d'affaires
     * @param chiffreDAffaire double supérieur ou égal à 200
     * @throws Exception si inférieur à 200
     */
    public void setChiffreDAffaire(double chiffreDAffaire) throws Exception {
        if (chiffreDAffaire<200){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Métier : Le chiffre d'affaire doit être supérieur à 200");
            throw (new ExceptionMetier("Le chiffre d'affaire doit être supérieur à 200"));
        }
        this.chiffreDAffaire = chiffreDAffaire;
    }

    /**
     * Getter du chiffre d'affaires
     * @return double chiffre d'affaires
     */
    public double getChiffreDAffaire() {
        return chiffreDAffaire;
    }

    /**
     * Setter du nombre d'employés
     * @param nbEmploye int supérieur à 0
     * @throws Exception si inférieur ou égal à 0
     */
    public void setNbEmploye(int nbEmploye) throws Exception {
        if (nbEmploye<1){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Métier : Le nombre d'employé doit être supérieur à 0");
            throw (new ExceptionMetier("Le nombre d'employé doit être supérieur à 0"));
        }
        this.nbEmploye = nbEmploye;
    }

    /**
     * Getter du nombre d'employés
     * @return int nombre d'employés
     */
    public int getNbEmploye() {
        return nbEmploye;
    }

    /**
     * Contructeur de Client
     * @param identifiant int
     * @param raisonSociale String
     * @param numeroRue String
     * @param nomRue String
     * @param codePostal String
     * @param ville String
     * @param telephone String
     * @param mail String
     * @param commentaire String
     * @param chiffreDAffaire double
     * @param nbEmploye int
     * @throws Exception remonte les exceptions
     */
    public Client(int identifiant, String raisonSociale, String numeroRue, String nomRue, String codePostal,
                  String ville, String telephone, String mail, String commentaire, double chiffreDAffaire, int nbEmploye) throws Exception {
        super(identifiant,raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail,commentaire);
        setChiffreDAffaire(chiffreDAffaire);
        setNbEmploye(nbEmploye);
    }
}
