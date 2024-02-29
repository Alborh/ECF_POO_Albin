package controleur;

import dao.DAOClient;
import dao.DAOProspect;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import metier.Client;
import metier.Prospect;
import vues.VueAcceuil;

import java.util.ArrayList;

public class ControleurAcceuil {

    private static VueAcceuil acceuil;

    /**
     *
     */
    public static void init(){
        acceuil = new VueAcceuil();
        acceuil.setSize(500,400);
        acceuil.pannelChoixSociete.setVisible(false);

        acceuil.setVisible(true);
    }

    /**
     *
     */
    public static void onAfficher() throws ExceptionMetier, ExceptionDAO {
        String typeSociete;
        if (acceuil.clientRadioButton.isSelected()){
            typeSociete = "Client";
        }else {
            typeSociete = "Prospect";
        }
        onQuitter();
        ControlleurAffichage.init(typeSociete);
    }

    /**
     *
     * @throws ExceptionMetier
     * @throws ExceptionDAO
     */
    public static void onCreation() throws ExceptionMetier, ExceptionDAO {
        String typeSociete;
        if (acceuil.clientRadioButton.isSelected()){
            typeSociete = "Client";
        }else {
            typeSociete = "Prospect";
        }
        //System.out.println("Creation");
        onQuitter();
        ControleurFormulaire.init("Creation", typeSociete,"Creation");

    }

    /**
     *
     * @throws ExceptionMetier
     * @throws ExceptionDAO
     */
    public static void onModifier() throws ExceptionMetier, ExceptionDAO {
        String typeSociete;
        if (acceuil.clientRadioButton.isSelected()){
            typeSociete = "Client";
        }else {
            typeSociete = "Prospect";
        }
        //System.out.println("Modifier "+acceuil.comboBoxChoixSociete.getSelectedItem().toString());
        onQuitter();
        ControleurFormulaire.init(acceuil.typeFormulaire, typeSociete,acceuil.comboBoxChoixSociete.getSelectedItem().toString());

    }

    /**
     *
     * @throws ExceptionMetier
     * @throws ExceptionDAO
     */
    public static void onSupprimer() throws ExceptionMetier, ExceptionDAO {
        String typeSociete;
        if (acceuil.clientRadioButton.isSelected()){
            typeSociete = "Client";
        }else {
            typeSociete = "Prospect";
        }
        //System.out.println("Supprimer");
        onQuitter();
        ControleurFormulaire.init(acceuil.typeFormulaire, typeSociete,acceuil.comboBoxChoixSociete.getSelectedItem().toString());
    }

    /**
     *
     */
    public static void onQuitter(){
        acceuil.dispose();
    }

    /**
     *
     * @throws ExceptionMetier
     * @throws ExceptionDAO
     */
    public static void choixSocieteSetChoix() throws ExceptionMetier, ExceptionDAO {
        if (acceuil.clientRadioButton.isSelected()){
            acceuil.comboBoxChoixSociete.removeAllItems();
            ArrayList<Client> clients = DAOClient.findAll();
            for (Client client : clients){
                acceuil.comboBoxChoixSociete.addItem(client.getRaisonSociale());
            }
        }
        else if (acceuil.prospectRadioButton.isSelected()){
            acceuil.comboBoxChoixSociete.removeAllItems();
            ArrayList<Prospect> prospects = DAOProspect.findAll();
            for (Prospect prospect : prospects){
                acceuil.comboBoxChoixSociete.addItem(prospect.getRaisonSociale());
            }
        }
    }
}
