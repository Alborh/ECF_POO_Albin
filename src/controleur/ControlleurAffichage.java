package controleur;

import dao.DAOClient;
import dao.DAOProspect;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import metier.Client;
import metier.Prospect;
import outils.ModelAffichage;
import vues.VueAffichage;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class ControlleurAffichage {
    private static VueAffichage vueAffichage;

    /**
     *
     * @param typeSociete Client ou Prospect
     * @throws ExceptionMetier
     * @throws ExceptionDAO
     */
    public static void init(String typeSociete) throws ExceptionMetier, ExceptionDAO {
        vueAffichage = new VueAffichage();
        vueAffichage.setSize(1200,400);
        vueAffichage.labTitre.setText("Affichage "+typeSociete);
        if (typeSociete.equals("Client")){
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
            vueAffichage.scrollPane.getViewport().add(new JScrollPane(jTable));
        } else if (typeSociete.equals("Prospect")) {
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
                data[i][8] = prospects.get(i).getDateFormatFormulaire();
                data[i][9] = prospects.get(i).getInteresse();
            }
            ModelAffichage modelAffichage = new ModelAffichage(data, titre);
            JTable jTable = new JTable(modelAffichage);
            vueAffichage.scrollPane.getViewport().add(new JScrollPane(jTable));
        }
        vueAffichage.setVisible(true);
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
        vueAffichage.dispose();
    }
}