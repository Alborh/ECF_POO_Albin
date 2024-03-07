package controleur;

import dao.DAOClient;
import dao.DAOProspect;
import exception.ExceptionControleur;
import log.LoggerPoo;
import metier.Client;
import metier.Prospect;
import vues.VueFormulaire;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.regex.Pattern;

import static outils.Outils.regexMail;

/**
 * Contrôleur de la vue Formulaire
 */
public class ControleurFormulaire {
    private static String formulaire;
    private static String societe;

    /**
     * Appelle la vue formulaire
     * @param typeFormulaire String Creation, Modification ou Suppression
     * @param typeSociete String Client ou Prospect
     * @param raisonSociale String raison sociale de la société selectionnée (sauf pour Création)
     */
    public static void init(String typeFormulaire, String typeSociete, String raisonSociale) {
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
     * @throws Exception remonte les exceptions
     */
    public static void onValider(String raisonSociale, String numeroRue, String nomRue, String codePostal, String ville,
                                 String telephone, String mail, String commentaire, String chiffreDAffaire, String nbEmploye,
                                 String dateProspect, String interesse, int id) throws Exception {
        //Vérification des contraintes (champs devant êtres renseignés et taille maximale acceptée par la BDD)
        if (raisonSociale.isEmpty()){
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : raison sociale ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : raison sociale ne dois pas être vide"));
        } else if (raisonSociale.length() > 50) {
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : raison sociale ne dois pas faire plus de 50 caractères");
            throw (new ExceptionControleur("Erreur : raison sociale ne dois pas faire plus de 50 caractères"));
        }
        if (numeroRue.isEmpty()){
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : numéro rue ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : numéro rue ne dois pas être vide"));
        } else if (numeroRue.length() > 10) {
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : numéro rue ne dois pas faire plus de 10 cacatères");
            throw (new ExceptionControleur("Erreur : numéro rue ne dois pas faire plus de 10 cacatères"));
        }
        if(nomRue.isEmpty()){
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : nom rue ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : nom rue ne dois pas être vide"));
        } else if (nomRue.length() > 30) {
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : nom rue ne dois pas faire plus de 30 caractères");
            throw (new ExceptionControleur("Erreur : nom rue ne dois pas plus de 30 caractères"));
        }
        if (codePostal.isEmpty()){
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : code postal ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : code postal ne dois pas être vide"));
        } else if (codePostal.length() > 5) {
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : code postal ne dois pas faire plus de 5 caractères");
            throw (new ExceptionControleur("Erreur : code postal ne dois pas faire plus de 5 caractères"));
        }
        if (ville.isEmpty()){
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : ville ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : ville ne dois pas être vide"));
        } else if (ville.length() > 20) {
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : ville ne dois pas faire plus de 20 caractères");
            throw (new ExceptionControleur("Erreur : ville ne dois pas faire plus de 20 caractères"));
        }
        if (telephone.isEmpty()){
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : telephone ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : telephone ne dois pas être vide"));
        } else if (telephone.length() > 15) {
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : telephone ne dois pas faire plus de 15 caractères");
            throw (new ExceptionControleur("Erreur : telephone ne dois pas faire plus de 15 caractères"));
        }
        if (mail.isEmpty()){
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : mail ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : mail ne dois pas être vide"));
        } else if (mail.length() > 50) {
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : mail ne dois pas faire plus de 50 caractères");
            throw (new ExceptionControleur("Erreur : mail ne dois pas plus de 50 caractères"));
        }
        if (commentaire.length()>100){
            //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : commentaire ne dois pas faire plus de 100 caractères");
            throw (new ExceptionControleur("Erreur : commentaire ne dois pas faire plus de 100 caractères"));
        }
        if (societe.equals("Client")){
            if (chiffreDAffaire.isEmpty()){
                //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : chiffre d'affaire ne dois pas être vide");
                throw (new ExceptionControleur("Erreur : chiffre d'affaire ne dois pas être vide"));
            }
            if (nbEmploye.isEmpty()){
                //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : nombre d'employés ne dois pas être vide");
                throw (new ExceptionControleur("Erreur : nombre d'employés ne dois pas être vide"));
            }
        }
        if (societe.equals("Prospect")){
            if (dateProspect.isEmpty()){
                //LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : date de prospection ne dois pas être vide");
                throw (new ExceptionControleur("Erreur : date de prospection ne dois pas être vide"));
            }
        }
        //Execution des actions en fonction du type de formulaire et de société
        switch (formulaire){
            case "Creation"->{
                if (societe.equals("Client")){
                    Client client = createClient(raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail, commentaire,
                            Double.parseDouble(chiffreDAffaire),Integer.parseInt(nbEmploye), id);
                    DAOClient.create(client);
                }
                else {
                    Prospect prospect = createProspect(raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail, commentaire,
                            dateProspect, interesse, id);
                    DAOProspect.create(prospect);
                }
            }
            case "Modification"->{
                if(societe.equals("Client")){
                    Client client = createClient(raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail, commentaire,
                            Double.parseDouble(chiffreDAffaire),Integer.parseInt(nbEmploye), id);
                    DAOClient.update(client);
                }
                else {
                    Prospect prospect = createProspect(raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail, commentaire,
                            dateProspect, interesse, id);
                    DAOProspect.update(prospect);
                }
            }
            case "Suppression"->{
                if(societe.equals("Client")){
                    Client client = DAOClient.findByName(raisonSociale);
                    DAOClient.delete(client);
                }
                else {
                    Prospect prospect = DAOProspect.findByName(raisonSociale);
                    DAOProspect.delete(prospect);
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

    /**
     * Crée un client pour la DAO
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
     * @param id int
     * @return le client donné en entrée dans le formulaire
     * @throws Exception remonte les exceptions
     */
    private static Client createClient(String raisonSociale, String numeroRue, String nomRue, String codePostal, String ville,
                                       String telephone, String mail, String commentaire, double chiffreDAffaire, int nbEmploye,
                                       int id) throws Exception {
        //génération identifiant
        int identifiant = 0;
        ArrayList<Client> clients = DAOClient.findAll();
        if(formulaire.equals("Creation")) {
            for (Client client : clients) {
                if (client.getIdentifiant() > identifiant) {
                    identifiant = client.getIdentifiant();
                }
            }
            identifiant++;
        } else {
            identifiant = id;
        }
        //test unicité de raison sociale
        for (Client client : clients){
            if (formulaire.equals("Modification")){
                if (client.getIdentifiant() != identifiant && client.getRaisonSociale().equals(raisonSociale)){
                    LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : Raison sociale déjà existante");
                    throw (new ExceptionControleur("Erreur : Raison sociale déjà existante"));
                }
            } else {
                if (client.getRaisonSociale().equals(raisonSociale)){
                    LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : Raison sociale déjà existante");
                    throw (new ExceptionControleur("Erreur : Raison sociale déjà existante"));
                }
            }
        }
        //test regex mail
        if (!regexMail(mail)){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : Email invalide : doit être au format [adresse]@[mail].[domaine]");
            throw (new ExceptionControleur("Email invalide : doit être au format [adresse]@[mail].[domaine]"));
        }
        Client res = new Client(identifiant,raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail,commentaire,chiffreDAffaire,nbEmploye);
        return res;
    }

    /**
     * crée un prospect pour la DAo
     * @param raisonSociale String
     * @param numeroRue String
     * @param nomRue String
     * @param codePostal String
     * @param ville String
     * @param telephone String
     * @param mail String
     * @param commentaire String
     * @param dateProspect String
     * @param interesse String
     * @param id int
     * @return le prospect donnée en entrée par le formulaire
     * @throws Exception remonte les exceptions
     */
    private static Prospect createProspect(String raisonSociale, String numeroRue, String nomRue, String codePostal, String ville,
                                           String telephone, String mail, String commentaire, String dateProspect, String interesse,
                                           int id) throws Exception {
        //géération identifiant
        int identifiant = 0;
        ArrayList<Prospect> prospects = DAOProspect.findAll();
        if (formulaire.equals("Creation")) {
            for (Prospect prospect : prospects) {
                if (prospect.getIdentifiant() > identifiant) {
                    identifiant = prospect.getIdentifiant();
                }
            }
            identifiant++;
        } else {
            identifiant = id;
        }
        //test unicité de raison sociale
        for (Prospect prospect : prospects){
            if (formulaire.equals("Modification")){
                if (prospect.getIdentifiant() != identifiant && prospect.getRaisonSociale().equals(raisonSociale)){
                    LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : Raison sociale déjà existante");
                    throw (new ExceptionControleur("Erreur : Raison sociale déjà existante"));
                }
            } else {
                if (prospect.getRaisonSociale().equals(raisonSociale)){
                    LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : Raison sociale déjà existante");
                    throw (new ExceptionControleur("Erreur : Raison sociale déjà existante"));
                }
            }
        }
        //test regex mail
        if (!regexMail(mail)){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : Email invalide : doit être au format [adresse]@[mail].[domaine]");
            throw (new ExceptionControleur("Email invalide : doit être au format [adresse]@[mail].[domaine]"));
        }
        // /!\ ne prend pas en compte la taille des mois
        Pattern patternDate = Pattern.compile("^((0[1-9])|([12][0-9])|(3[01]))/((0[0-9])|(1[012]))/([0-9])*$");
        if(!patternDate.matcher(dateProspect).matches()){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : Mauvais format de date (doit être dd/MM/yyyy)");
            throw (new ExceptionControleur("Mauvais format de date (doit être dd/MM/yyyy)"));
        }
        //Conversion de la date de String à LocalDate
        LocalDate date = LocalDate.parse(dateProspect,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Prospect res = new Prospect(identifiant,raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail,commentaire,date,interesse);
        return res;
    }
}