package metier;

import exception.ExceptionMetier;

/**
 *
 */
public class Client extends Societe{
    private double chiffreDAffaire;
    private int nbEmploye;

    /**
     *
     * @param chiffreDAffaire >200
     */
    public void setChiffreDAffaire(double chiffreDAffaire) throws ExceptionMetier {
        if (chiffreDAffaire<200){
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
     */
    public void setNbEmploye(int nbEmploye) throws ExceptionMetier {
        if (nbEmploye<1){
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
     */
    public Client(int identifiant, String raisonSociale, String numeroRue, String nomRue, String codePostal,
                  String ville, String telephone, String mail, String commentaire, double chiffreDAffaire, int nbEmploye) throws ExceptionMetier {
        super(identifiant,raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail,commentaire);
        setChiffreDAffaire(chiffreDAffaire);
        setNbEmploye(nbEmploye);
    }
}
