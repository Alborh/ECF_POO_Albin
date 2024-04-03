package controleur;

import dao.DAOClient;
import dao.DAOProspect;
import metier.Client;
import metier.Prospect;
import vues.VueAffichage;
import outils.*;

import java.util.ArrayList;

/**
 * Contrôleur de la vue Affichage
 */
public class ControlleurAffichage {

    /**
     * Appelle la vue Affichage
     * @param typeSociete String Client ou Prospect
     */
    public static void init(typeSociete typeSociete) {
        VueAffichage vueAffichage = new VueAffichage(typeSociete);
        vueAffichage.setVisible(true);
    }

    /**
     *
     * @return Arraylist\<Client> de tous les clients
     * @throws Exception remonte les exceptions
     */
    public static ArrayList<Client> listeClients() throws Exception {
        return DAOClient.findAll();
    }

    /**
     *
     * @return Arraylist\<Prospect> de tous les prospects
     * @throws Exception remonte les exceptions
     */
    public static ArrayList<Prospect> listeProspect() throws Exception {
        return DAOProspect.findAll();
    }
    /**
     * Appel la vue acceuil
     */
    public static void onRetourAcceuil(){
        ControleurAcceuil.init();
    }
}