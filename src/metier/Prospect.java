package metier;

import exception.ExceptionMetier;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;


/**
 * Type de société avec date de prospection et intéressé ou non
 */
public class Prospect extends Societe {
    private LocalDate dateProspection;
    private String interesse;

    /**
     * setter date de prospection
     * @param dateProspection LocalDate
     */
    public void setDateProspection(LocalDate dateProspection) throws Exception {
        if(dateProspection==null){
            throw (new ExceptionMetier("Erreur : la valeur Date prospection ne dois pas être vide"));
        }
        this.dateProspection = dateProspection;
    }

    /**
     * getter date de prospection
     * @return LocalDate date de prospection
     */
    public LocalDate getDateProspection() {
        return dateProspection;
    }

    /**
     * setter interessé
     * @param interesse String "Oui" ou "Non"
     * @throws Exception si n'est pas "Oui" ou "Non"
     */
    public void setInteresse(String interesse) throws Exception {
        if (interesse.isEmpty()){
            throw (new ExceptionMetier("Erreur : la valeur interesse ne dois pas être nulle"));
        }
        if (!(interesse.equals("Oui") || interesse.equals("Non"))) {
            throw (new ExceptionMetier("la valeur interesse doit être Oui ou Non"));
        }
        this.interesse = interesse;
    }

    /**
     * getter interessé
     * @return String interessé
     */
    public String getInteresse() {
        return interesse;
    }

    /**
     * Contructeur de Prospect
     * @param identifiant int
     * @param raisonSociale String
     * @param numeroRue String
     * @param nomRue String
     * @param codePostal String
     * @param ville String
     * @param telephone String
     * @param mail String
     * @param commentaire String
     * @param dateProspection LocalDate
     * @param interesse String
     */
    public Prospect(int identifiant, String raisonSociale, String numeroRue, String nomRue, String codePostal,
                    String ville, String telephone, String mail, String commentaire, LocalDate dateProspection, String interesse) throws Exception {
        super(identifiant, raisonSociale, numeroRue, nomRue, codePostal, ville, telephone, mail, commentaire);
        setDateProspection(dateProspection);
        setInteresse(interesse);
    }

    /**
     *
     * @return String décrivant le prospect
     */
    @Override
    public String toString() {
        return super.toString()+getDateProspection().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+" "+getInteresse();
    }
}
