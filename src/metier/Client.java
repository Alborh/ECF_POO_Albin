package metier;

import exception.ExceptionMetier;
import log.LoggerPoo;

import java.time.LocalDate;
import java.util.logging.Level;

/**
 *
 */
public class Client extends Societe{
    private double chiffreDAffaire;
    private int nbEmploye;

    /**
     *
     * @param chiffreDAffaire >200
     * @throws Exception
     */
    public void setChiffreDAffaire(double chiffreDAffaire) throws Exception {
        if (chiffreDAffaire<200){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Métier : Le chiffre d'affaire doit être supérieur à 200");
            throw (new ExceptionMetier("Le chiffre d'affaire doit être supérieur à 200"));
        }
        this.chiffreDAffaire = chiffreDAffaire;
    }

    /**
     *
     * @return
     */
    public double getChiffreDAffaire() {
        return chiffreDAffaire;
    }

    /**
     *
     * @param nbEmploye >0
     * @throws Exception
     */
    public void setNbEmploye(int nbEmploye) throws Exception {
        if (nbEmploye<1){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Métier : Le nombre d'employé doit être supérieur à 0");
            throw (new ExceptionMetier("Le nombre d'employé doit être supérieur à 0"));
        }
        this.nbEmploye = nbEmploye;
    }

    /**
     *
     * @return
     */
    public int getNbEmploye() {
        return nbEmploye;
    }

    /**
     *
     * @param identifiant
     * @param raisonSociale
     * @param numeroRue
     * @param nomRue
     * @param codePostal
     * @param ville
     * @param telephone
     * @param mail
     * @param commentaire
     * @param chiffreDAffaire
     * @param nbEmploye
     * @throws Exception
     */
    public Client(int identifiant, String raisonSociale, String numeroRue, String nomRue, String codePostal,
                  String ville, String telephone, String mail, String commentaire, double chiffreDAffaire, int nbEmploye) throws Exception {
        super(identifiant,raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail,commentaire);
        setChiffreDAffaire(chiffreDAffaire);
        setNbEmploye(nbEmploye);
    }
}
