package controleur;

import dao.DAOClient;
import dao.DAOProspect;
import exception.ExceptionControleur;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import log.LoggerPoo;
import metier.Client;
import metier.Prospect;
import vues.VueFormulaire;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.regex.Pattern;

public class ControleurFormulaire {
    private static String formulaire;
    private static String societe;

    private static VueFormulaire vueFormulaire;

    /**
     *
     * @param typeFormulaire
     * @param typeSociete
     * @param raisonSociale
     */
    public static void init(String typeFormulaire, String typeSociete, String raisonSociale) {
        formulaire = typeFormulaire;
        societe = typeSociete;
        vueFormulaire = new VueFormulaire(formulaire,societe,raisonSociale);
        vueFormulaire.setVisible(true);
    }

    /**
     *
     * @throws Exception
     */
    public static void onValider() throws Exception {
        if (vueFormulaire.textFieldRaisonSociale.getText().isEmpty()){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : raison sociale ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : raison sociale ne dois pas être vide"));
        }
        if (vueFormulaire.textFieldNumeroRue.getText().isEmpty()){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : numéro rue ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : numéro rue ne dois pas être vide"));
        }
        if(vueFormulaire.textFieldNomRue.getText().isEmpty()){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : nom rue ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : nom rue ne dois pas être vide"));
        }
        if (vueFormulaire.textFieldCodePostal.getText().isEmpty()){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : code postal ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : code postal ne dois pas être vide"));
        }
        if (vueFormulaire.textFieldVille.getText().isEmpty()){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : ville ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : ville ne dois pas être vide"));
        }
        if (vueFormulaire.textFieldTelephone.getText().isEmpty()){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : telephone ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : telephone ne dois pas être vide"));
        }
        if (vueFormulaire.textFieldMail.getText().isEmpty()){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : mail ne dois pas être vide");
            throw (new ExceptionControleur("Erreur : mail ne dois pas être vide"));
        }
        if (societe.equals("Client")){
            if (vueFormulaire.textFieldChiffreDAffaire.getText().isEmpty()){
                LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : chiffre d'affaire ne dois pas être vide");
                throw (new ExceptionControleur("Erreur : chiffre d'affaire ne dois pas être vide"));
            }
            if (vueFormulaire.textFieldNombreEmploye.getText().isEmpty()){
                LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : nombre d'employés ne dois pas être vide");
                throw (new ExceptionControleur("Erreur : nombre d'employés ne dois pas être vide"));
            }
        }
        if (societe.equals("Prospect")){
            if (vueFormulaire.dateTextField.getText().isEmpty()){
                LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : date de prospection ne dois pas être vide");
                throw (new ExceptionControleur("Erreur : date de prospection ne dois pas être vide"));
            }
        }
        switch (formulaire){
            case "Creation"->{
                if (societe.equals("Client")){
                    Client client = createClient();
                    DAOClient.create(client);
                }
                else {
                    Prospect prospect = createProspect();
                    DAOProspect.create(prospect);
                }
            }
            case "Modification"->{
                if(societe.equals("Client")){
                    Client client = createClient();
                    DAOClient.update(client);
                }
                else {
                    Prospect prospect = createProspect();
                    DAOProspect.update(prospect);
                }
            }
            case "Suppression"->{
                if(societe.equals("Client")){
                    Client client = DAOClient.findByName(vueFormulaire.textFieldRaisonSociale.getText());
                    DAOClient.delete(client);
                }
                else {
                    Prospect prospect = DAOProspect.findByName(vueFormulaire.textFieldRaisonSociale.getText());
                    DAOProspect.delete(prospect);
                }
            }
        }
        onRetourAcceuil();
    }

    /**
     *
     */
    public static void onRetourAcceuil(){
        onQuitter();
        ControleurAcceuil.init();
    }

    /**
     *
     */
    public static void onQuitter(){
        vueFormulaire.dispose();
    }

    /**
     *
     * @return le client donné en entrée dans le formulaire
     * @throws Exception
     */
    private static Client createClient() throws Exception {
        int identifiant = 0;
        if(formulaire.equals("Creation")) {
            ArrayList<Client> clients = DAOClient.findAll();
            for (Client client : clients) {
                if (client.getIdentifiant() > identifiant) {
                    identifiant = client.getIdentifiant();
                }
            }
            identifiant++;
        } else {
            identifiant = DAOClient.findByName(vueFormulaire.textFieldRaisonSociale.getText()).getIdentifiant();
        }
        String raisonSociale = vueFormulaire.textFieldRaisonSociale.getText();
        String numeroRue = vueFormulaire.textFieldNumeroRue.getText();
        String nomRue = vueFormulaire.textFieldNomRue.getText();
        String codePostal = vueFormulaire.textFieldCodePostal.getText();
        String ville = vueFormulaire.textFieldVille.getText();
        String telephone = vueFormulaire.textFieldTelephone.getText();
        String mail = vueFormulaire.textFieldMail.getText();
        String commentaire = vueFormulaire.textFieldCommentaires.getText();
        double chiffreDAffaire = Double.parseDouble(vueFormulaire.textFieldChiffreDAffaire.getText());
        int nbEmploye = Integer.parseInt(vueFormulaire.textFieldNombreEmploye.getText());
        Pattern patternMail = Pattern.compile("^(.*)@(.*)[.](.*)$");
        if (!patternMail.matcher(mail).matches()){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Controleur : Email invalide : doit être au format [adresse]@[mail].[domaine]");
            throw (new ExceptionControleur("Email invalide : doit être au format [adresse]@[mail].[domaine]"));
        }
        Client res = new Client(identifiant,raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail,commentaire,chiffreDAffaire,nbEmploye);
        return res;
    }

    /**
     *
     * @return le prospect donnée en entrée par le formulaire
     * @throws Exception
     */
    private static Prospect createProspect() throws Exception {
        int identifiant = 0;
        if (formulaire.equals("Creation")) {
            ArrayList<Prospect> prospects = DAOProspect.findAll();
            for (Prospect prospect : prospects) {
                if (prospect.getIdentifiant() > identifiant) {
                    identifiant = prospect.getIdentifiant();
                }
            }
            identifiant++;
        } else {
            identifiant = DAOProspect.findByName(vueFormulaire.textFieldRaisonSociale.getText()).getIdentifiant();
        }
        String raisonSociale = vueFormulaire.textFieldRaisonSociale.getText();
        String numeroRue = vueFormulaire.textFieldNumeroRue.getText();
        String nomRue = vueFormulaire.textFieldNomRue.getText();
        String codePostal = vueFormulaire.textFieldCodePostal.getText();
        String ville = vueFormulaire.textFieldVille.getText();
        String telephone = vueFormulaire.textFieldTelephone.getText();
        String mail = vueFormulaire.textFieldMail.getText();
        String commentaire = vueFormulaire.textFieldCommentaires.getText();
        String dateProspect = vueFormulaire.dateTextField.getText();
        String interesse = "";
        if (vueFormulaire.ouiRadioButton.isSelected()){
            interesse = "Oui";
        } else if (vueFormulaire.nonRadioButton.isSelected()){
            interesse = "Non";
        }
        Pattern patternMail = Pattern.compile("^(.*)@(.*)[.](.*)$");
        if (!patternMail.matcher(mail).matches()){
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
