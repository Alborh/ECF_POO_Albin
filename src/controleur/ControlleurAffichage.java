package controleur;

import dao.DAOClient;
import dao.DAOProspect;
import metier.Client;
import metier.Prospect;
import outils.ModelAffichage;
import vues.VueAffichage;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Contrôleur de la vue Affichage
 */
public class ControlleurAffichage {

    /**
     * Appelle la vue Affichage
     * @param typeSociete String Client ou Prospect
     */
    public static void init(String typeSociete) {
        VueAffichage vueAffichage = new VueAffichage(typeSociete);
        vueAffichage.setVisible(true);
    }

    /**
     * Renvoie une JTable affichant les paramètres des sociétés du type sélectionné
     * @param typeSociete String Client ou Prospect
     * @return JTable des sociétés du type sélectionné
     * @throws Exception remonte les exceptions
     */
    public static JTable tableAffichage(String typeSociete) throws Exception {
        switch (typeSociete){
            case "Client"->{
                ArrayList<Client> clients = DAOClient.findAll();
                String[] titre = {"Raison sociale","Numéro rue","Nom rue","Code postal","Ville","Téléphone","Mail","Commentaire","Chiffre d'affaire","Nombre d'employés"};
                Object[][] data = new Object[clients.size()][10];
                for (int i = 0; i<clients.size(); i++){
                    data[i][0] = clients.get(i).getRaisonSociale();
                    data[i][1] = clients.get(i).getNumeroRue();
                    data[i][2] = clients.get(i).getNomRue();
                    data[i][3] = clients.get(i).getCodePostal();
                    data[i][4] = clients.get(i).getVille();
                    data[i][5] = clients.get(i).getTelephone();
                    data[i][6] = clients.get(i).getMail();
                    data[i][7] = clients.get(i).getCommentaire();
                    data[i][8] = clients.get(i).getChiffreDAffaire();
                    data[i][9] = clients.get(i).getNbEmploye();
                }
                ModelAffichage modelAffichage = new ModelAffichage(data, titre);
                JTable jTable = new JTable(modelAffichage);
                return jTable;
            }
            case "Prospect"->{
                ArrayList<Prospect> prospects = DAOProspect.findAll();
                String[] titre = {"Raison sociale","Numéro rue","Nom rue","Code postal","Ville","Téléphone","Mail","Commentaire","Date de Prospection","Prospect interressé"};
                Object[][] data = new Object[prospects.size()][10];
                for (int i = 0; i<prospects.size(); i++){
                    data[i][0] = prospects.get(i).getRaisonSociale();
                    data[i][1] = prospects.get(i).getNumeroRue();
                    data[i][2] = prospects.get(i).getNomRue();
                    data[i][3] = prospects.get(i).getCodePostal();
                    data[i][4] = prospects.get(i).getVille();
                    data[i][5] = prospects.get(i).getTelephone();
                    data[i][6] = prospects.get(i).getMail();
                    data[i][7] = prospects.get(i).getCommentaire();
                    data[i][8] = prospects.get(i).getDateProspection().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    data[i][9] = prospects.get(i).getInteresse();
                }
                ModelAffichage modelAffichage = new ModelAffichage(data, titre);
                JTable jTable = new JTable(modelAffichage);
                return jTable;
            }
        }
        return new JTable();
    }

    /**
     * Appel la vue acceuil
     */
    public static void onRetourAcceuil(){
        ControleurAcceuil.init();
    }
}