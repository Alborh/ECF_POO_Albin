package metier;

import exception.ExceptionMetier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 *
 */
public class Prospect extends Societe {
    private Date dateProspection;
    private String interesse;

    /**
     * @param dateProspection
     */
    public void setDateProspection(Date dateProspection) {
        this.dateProspection = dateProspection;
    }

    /**
     * /!\ l'exception ne prend pas en compte les spécificités du mois de février
     * @param dateProspection
     * @throws ExceptionMetier
     */
    public void setDateProspection(String dateProspection) throws ExceptionMetier {
        try {
            Pattern patternDate = Pattern.compile("^((0[1-9])|([12][0-9])|(3[01]))/((0[0-9])|(1[012]))/([0-9])*$");
            if(!patternDate.matcher(dateProspection).matches()){
                throw (new ExceptionMetier("Mauvais format de date (doit être jj/MM/yyyy)"));
            }
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            this.dateProspection = dateFormat.parse(dateProspection);
        } catch (ParseException e) {
            throw new ExceptionMetier("Mauvais format de date (doit être jj/MM/yyyy)");
        }
    }

    /**
     * @return
     */
    public Date getDateProspection() {
        return dateProspection;
    }

    /**
     * @param interesse
     */
    public void setInteresse(String interesse) throws ExceptionMetier {
        if (!(interesse.equals("Oui") || interesse.equals("Non"))) {
            throw (new ExceptionMetier("la valeur interesse doit être Oui ou Non"));
        }
        this.interesse = interesse;
    }

    /**
     * @return
     */
    public String getInteresse() {
        return interesse;
    }

    /**
     * @param identifiant
     * @param raisonSociale
     * @param numeroRue
     * @param nomRue
     * @param codePostal
     * @param ville
     * @param telephone
     * @param mail
     * @param commentaire
     * @param dateProspection
     * @param interesse
     */
    public Prospect(int identifiant, String raisonSociale, String numeroRue, String nomRue, String codePostal,
                    String ville, String telephone, String mail, String commentaire, Date dateProspection, String interesse) throws ExceptionMetier {
        super(identifiant, raisonSociale, numeroRue, nomRue, codePostal, ville, telephone, mail, commentaire);
        setDateProspection(dateProspection);
        setInteresse(interesse);
    }

    /**
     *
     */
    public Prospect(int identifiant, String raisonSociale, String numeroRue, String nomRue, String codePostal,
                    String ville, String telephone, String mail, String commentaire, String dateProspection, String interesse) throws ExceptionMetier {
    super(identifiant,raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail,commentaire);
    setDateProspection(dateProspection);
    setInteresse(interesse);
    }
}
