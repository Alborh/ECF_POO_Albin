package controleur;

import dao.DAOClient;
import dao.DAOProspect;
import metier.Client;
import metier.Prospect;
import metier.Societe;
import vues.VueAcceuil;

import java.util.ArrayList;

/**
 * Contrôleur de la vue Acceuil
 */
public class ControleurAcceuil {
    /**
     * Appelle l'acceuil
     */
    public static void init(){
        VueAcceuil acceuil = new VueAcceuil();
        acceuil.setVisible(true);
    }

    /**
     * Appel la vue Afficher
     */
    public static void onAfficher(String typeSociete) throws Exception {
        ControlleurAffichage.init(typeSociete);
    }

    /**
     * Appel une vue formulaire de type Création
     * @param typeSociete String Client ou Prospect
     */
    public static void onCreation(String typeSociete) {
        ControleurFormulaire.init("Creation", typeSociete,"Creation");
    }

    /**
     * Appel une vue formulaire de type Modification
     * @param typeSociete String Client ou Prospect
     * @param typeFormulaire String Modification
     * @param raisonSociale String raison sociale de la société sélectionnée
     */
    public static void onModifier(String typeSociete, String typeFormulaire, String raisonSociale) {
        ControleurFormulaire.init(typeFormulaire, typeSociete, raisonSociale);
    }

    /**
     * Appel une vue formulaire de tupe Supprimer
     * @param typeSociete String Client ou Prospect
     * @param typeFormulaire String Suppression
     * @param raisonSociale String raison sociale de la société sélectionnée
     */
    public static void onSupprimer(String typeSociete, String typeFormulaire, String raisonSociale) {
        ControleurFormulaire.init(typeFormulaire, typeSociete, raisonSociale);
    }

    /**
     * Appel une ArrayList des sociétés du type sélectionné
     * @param typeSociete String Client ou Prospect
     * @return Arraylist de Societe
     * @throws Exception remonte les exceptions
     */
    public static ArrayList<Societe> choixSocieteSetChoix(String typeSociete) throws Exception {
        ArrayList<Societe> societes = new ArrayList<>();
        switch (typeSociete){
            case "Client"->{
                ArrayList<Client> clients = new ArrayList<>();
                clients = DAOClient.findAll();
                for (Client client : clients){
                    societes.add(client);
                }
            }
            case "Prospect"->{
                ArrayList<Prospect> prospects = new ArrayList<>();
                prospects = DAOProspect.findAll();
                for (Prospect prospect : prospects){
                    societes.add(prospect);
                }
            }
        }
        return societes;
    }
}
