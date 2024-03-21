package controleur;

import dao.DAOClient;
import dao.DAOProspect;
import exception.ExceptionControleur;
import metier.Client;
import metier.Prospect;
import vues.VueFormulaire;
import outils.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


/**
 * Contrôleur de la vue Formulaire
 */
public class ControleurFormulaire {
    private static typeFormulaire formulaire;
    private static typeSociete societe;

    /**
     * Appelle la vue formulaire
     * @param typeFormulaire String Creation, Modification ou Suppression
     * @param typeSociete String Client ou Prospect
     * @param raisonSociale String raison sociale de la société selectionnée (sauf pour Création)
     */
    public static void init(typeFormulaire typeFormulaire, typeSociete typeSociete, String raisonSociale) {
        formulaire = typeFormulaire;
        societe = typeSociete;
        VueFormulaire vueFormulaire = new VueFormulaire(formulaire,societe,raisonSociale);
        vueFormulaire.setVisible(true);
    }

    /**
     * Valide le formulaire et effectue l'action sur la base de données, puis reviens à l'acceuil
     * @param raisonSociale String
     * @param numeroRue String
     * @param nomRue String
     * @param codePostal String
     * @param ville String
     * @param telephone String
     * @param mail String
     * @param commentaire String
     * @param chiffreDAffaire String
     * @param nbEmploye String
     * @param dateProspect String
     * @param interesse String
     * @param id int
     * @throws Exception remonte les exceptions et erreurs de saisie
     */
    public static void onValider(String raisonSociale, String numeroRue, String nomRue, String codePostal, String ville,
                                 String telephone, String mail, String commentaire, String chiffreDAffaire, String nbEmploye,
                                 String dateProspect, String interesse, int id) throws Exception {
        //Vérification des champs devant êtres renseignés ne pouvant pas être testé si null une fois convertit (double et int)
        if (societe == typeSociete.CLIENT){
            if (chiffreDAffaire.isEmpty()){
                throw (new ExceptionControleur("Erreur : chiffre d'affaire ne dois pas être vide"));
            }
            if (nbEmploye.isEmpty()){
                throw (new ExceptionControleur("Erreur : nombre d'employés ne dois pas être vide"));
            }
        }
        //vérifie que la date est au bon format avant de parse
        if (societe == typeSociete.PROSPECT){
            // /!\ ne prend pas en compte la taille des mois
            Pattern patternDate = Pattern.compile("^((0[1-9])|([12][0-9])|(3[01]))/((0[0-9])|(1[012]))/([0-9][0-9][0-9][0-9])$");
            if(!patternDate.matcher(dateProspect).matches()){
                throw (new ExceptionControleur("Mauvais format de date (doit être dd/MM/yyyy)"));
            }
        }
        LocalDate date = null;
        if (societe == typeSociete.PROSPECT){
            //Conversion de la date de String à LocalDate
            date = LocalDate.parse(dateProspect,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        //Execution des actions en fonction du type de formulaire et de société
        switch (formulaire){
            case CREATION->{
                switch (societe){
                    case CLIENT -> {
                        Client client = new Client(id,raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail, commentaire,
                                Double.parseDouble(chiffreDAffaire),Integer.parseInt(nbEmploye));
                        DAOClient.create(client);
                    }
                    case PROSPECT -> {
                        Prospect prospect = new Prospect(id, raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail, commentaire,
                                date, interesse);
                        DAOProspect.create(prospect);
                    }
                }
            }
            case MODIFICATION->{
                switch (societe){
                    case CLIENT -> {
                        Client client = new Client(id,raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail, commentaire,
                                Double.parseDouble(chiffreDAffaire),Integer.parseInt(nbEmploye));
                        DAOClient.update(client);
                    }
                    case PROSPECT -> {
                        Prospect prospect = new Prospect(id, raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail, commentaire,
                                date, interesse);
                        DAOProspect.update(prospect);
                    }
                }
            }
            case SUPPRESSION->{
                switch (societe){
                    case CLIENT -> {
                        Client client = DAOClient.findByName(raisonSociale);
                        DAOClient.delete(client);
                    }
                    case PROSPECT -> {
                        Prospect prospect = DAOProspect.findByName(raisonSociale);
                        DAOProspect.delete(prospect);
                    }
                }
            }
        }
    }

    /**
     * Appelle la vue Acceuil
     */
    public static void onRetourAcceuil(){
        ControleurAcceuil.init();
    }
}