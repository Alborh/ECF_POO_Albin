package controleur;

import dao.DAOClient;
import dao.DAOProspect;
import exception.ExceptionControleur;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import metier.Client;
import metier.Prospect;
import vues.VueFormulaire;

import java.util.ArrayList;

public class ControleurFormulaire {
    private static String formulaire;
    private static String societe;

    private static VueFormulaire vueFormulaire;

    /**
     *
     * @param typeFormulaire
     * @param typeSociete
     * @param raisonSociale
     * @throws ExceptionMetier
     * @throws ExceptionDAO
     */
    public static void init(String typeFormulaire, String typeSociete, String raisonSociale) throws ExceptionMetier, ExceptionDAO {
        formulaire = typeFormulaire;
        societe = typeSociete;
        vueFormulaire = new VueFormulaire();
        vueFormulaire.setSize(400,500);
        //options client
        vueFormulaire.labChiffreDAffaire.setVisible(false);
        vueFormulaire.textFieldChiffreDAffaire.setVisible(false);
        vueFormulaire.labEmploye.setVisible(false);
        vueFormulaire.textFieldNombreEmploye.setVisible(false);
        //options prospect
        vueFormulaire.labDate.setVisible(false);
        vueFormulaire.dateTextField.setVisible(false);
        vueFormulaire.labInteresse.setVisible(false);
        vueFormulaire.ouiRadioButton.setVisible(false);
        vueFormulaire.nonRadioButton.setVisible(false);
        vueFormulaire.ouiRadioButton.setSelected(true);
        vueFormulaire.ouiRadioButton.setEnabled(false);

        vueFormulaire.labTitre.setText(formulaire+" de "+societe);
        switch (societe){
            case "Client"->{
                vueFormulaire.labChiffreDAffaire.setVisible(true);
                vueFormulaire.textFieldChiffreDAffaire.setVisible(true);
                vueFormulaire.labEmploye.setVisible(true);
                vueFormulaire.textFieldNombreEmploye.setVisible(true);
            }
            case "Prospect"->{
                vueFormulaire.labDate.setVisible(true);
                vueFormulaire.dateTextField.setVisible(true);
                vueFormulaire.labInteresse.setVisible(true);
                vueFormulaire.ouiRadioButton.setVisible(true);
                vueFormulaire.nonRadioButton.setVisible(true);
            }
        }
        if (!formulaire.equals("Creation")){
            switch (societe){
                case "Client"->{
                    Client client = DAOClient.findByName(raisonSociale);
                    vueFormulaire.textFieldRaisonSociale.setText(client.getRaisonSociale());
                    vueFormulaire.textFieldNumeroRue.setText(client.getNumeroRue());
                    vueFormulaire.textFieldNomRue.setText(client.getNomRue());
                    vueFormulaire.textFieldCodePostal.setText(client.getCodePostal());
                    vueFormulaire.textFieldVille.setText(client.getVille());
                    vueFormulaire.textFieldTelephone.setText(client.getTelephone());
                    vueFormulaire.textFieldMail.setText(client.getMail());
                    vueFormulaire.textFieldCommentaires.setText(client.getCommentaire());
                    vueFormulaire.textFieldChiffreDAffaire.setText(String.valueOf(client.getChiffreDAffaire()));
                    vueFormulaire.textFieldNombreEmploye.setText(String.valueOf(client.getNbEmploye()));
                }
                case "Prospect"->{
                    Prospect prospect = DAOProspect.findByName(raisonSociale);
                    vueFormulaire.textFieldRaisonSociale.setText(prospect.getRaisonSociale());
                    vueFormulaire.textFieldNumeroRue.setText(prospect.getNumeroRue());
                    vueFormulaire.textFieldNomRue.setText(prospect.getNomRue());
                    vueFormulaire.textFieldCodePostal.setText(prospect.getCodePostal());
                    vueFormulaire.textFieldVille.setText(prospect.getVille());
                    vueFormulaire.textFieldTelephone.setText(prospect.getTelephone());
                    vueFormulaire.textFieldMail.setText(prospect.getMail());
                    vueFormulaire.textFieldCommentaires.setText(prospect.getCommentaire());
                    vueFormulaire.dateTextField.setText(prospect.getDateFormatFormulaire());
                    if(prospect.getInteresse().equals("Non")){
                        vueFormulaire.nonRadioButton.setSelected(true);
                        vueFormulaire.ouiRadioButton.setSelected(false);
                        vueFormulaire.nonRadioButton.setEnabled(false);
                        vueFormulaire.ouiRadioButton.setEnabled(true);
                    }
                }
            }
        }
        if(formulaire.equals("Suppression")){
            vueFormulaire.textFieldRaisonSociale.setEditable(false);
            vueFormulaire.textFieldNumeroRue.setEditable(false);
            vueFormulaire.textFieldNomRue.setEditable(false);
            vueFormulaire.textFieldCodePostal.setEditable(false);
            vueFormulaire.textFieldVille.setEditable(false);
            vueFormulaire.textFieldTelephone.setEditable(false);
            vueFormulaire.textFieldMail.setEditable(false);
            vueFormulaire.textFieldCommentaires.setEditable(false);
            vueFormulaire.textFieldChiffreDAffaire.setEditable(false);
            vueFormulaire.textFieldNombreEmploye.setEditable(false);
            vueFormulaire.dateTextField.setEditable(false);
            vueFormulaire.ouiRadioButton.setEnabled(false);
            vueFormulaire.nonRadioButton.setEnabled(false);
        }
        vueFormulaire.setVisible(true);
    }

    /**
     *
     * @throws ExceptionMetier
     * @throws ExceptionDAO
     * @throws ExceptionControleur
     */
    public static void onValider() throws ExceptionMetier, ExceptionDAO, ExceptionControleur {
        if (vueFormulaire.textFieldRaisonSociale.getText().isEmpty()){
            throw (new ExceptionControleur("Erreur controleur : raison sociale ne dois pas être vide"));
        }
        if (vueFormulaire.textFieldNumeroRue.getText().isEmpty()){
            throw (new ExceptionControleur("Erreur controleur : numéro rue ne dois pas être vide"));
        }
        if(vueFormulaire.textFieldNomRue.getText().isEmpty()){
            throw (new ExceptionControleur("Erreur controleur : nom rue ne dois pas être vide"));
        }
        if (vueFormulaire.textFieldCodePostal.getText().isEmpty()){
            throw (new ExceptionControleur("Erreur controleur : code postal ne dois pas être vide"));
        }
        if (vueFormulaire.textFieldVille.getText().isEmpty()){
            throw (new ExceptionControleur("Erreur controleur : ville ne dois pas être vide"));
        }
        if (vueFormulaire.textFieldTelephone.getText().isEmpty()){
            throw (new ExceptionControleur("Erreur controleur : telephone ne dois pas être vide"));
        }
        if (vueFormulaire.textFieldMail.getText().isEmpty()){
            throw (new ExceptionControleur("Erreur controleur : mail ne dois pas être vide"));
        }
        if (societe.equals("Client")){
            if (vueFormulaire.textFieldChiffreDAffaire.getText().isEmpty()){
                throw (new ExceptionControleur("Erreur controleur : chiffre d'affaire ne dois pas être vide"));
            }
            if (vueFormulaire.textFieldNombreEmploye.getText().isEmpty()){
                throw (new ExceptionControleur("Erreur controleur : nombre d'employés ne dois pas être vide"));
            }
        }
        if (societe.equals("Prospect")){
            if (vueFormulaire.dateTextField.getText().isEmpty()){
                throw (new ExceptionControleur("Erreur controleur : date de prospection ne dois pas être vide"));
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
     * @throws ExceptionMetier
     * @throws ExceptionDAO
     */
    private static Client createClient() throws ExceptionMetier, ExceptionDAO {
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
        Client res = new Client(identifiant,raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail,commentaire,chiffreDAffaire,nbEmploye);
        return res;
    }

    /**
     *
     * @return le prospect donnée en entrée par le formulaire
     * @throws ExceptionMetier
     * @throws ExceptionDAO
     */
    private static Prospect createProspect() throws ExceptionMetier, ExceptionDAO {
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
        Prospect res = new Prospect(identifiant,raisonSociale,numeroRue,nomRue,codePostal,ville,telephone,mail,commentaire,dateProspect,interesse);
        return res;
    }
}
