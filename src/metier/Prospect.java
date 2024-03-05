package metier;

import exception.ExceptionMetier;
import log.LoggerPoo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 *
 */
public class Prospect extends Societe {
    private LocalDate dateProspection;
    private String interesse;

    /**
     *
     * @param dateProspection
     */
    public void setDateProspection(LocalDate dateProspection) {
        this.dateProspection = dateProspection;
    }

    /**
     *
     * @return
     */
    public LocalDate getDateProspection() {
        return dateProspection;
    }

    /**
     *
     * @param interesse
     * @throws Exception
     */
    public void setInteresse(String interesse) throws Exception {
        if (!(interesse.equals("Oui") || interesse.equals("Non"))) {
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Métier : la valeur interesse doit être Oui ou Non");
            throw (new ExceptionMetier("la valeur interesse doit être Oui ou Non"));
        }
        this.interesse = interesse;
    }

    /**
     *
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
                    String ville, String telephone, String mail, String commentaire, LocalDate dateProspection, String interesse) throws Exception {
        super(identifiant, raisonSociale, numeroRue, nomRue, codePostal, ville, telephone, mail, commentaire);
        setDateProspection(dateProspection);
        setInteresse(interesse);
    }
}
