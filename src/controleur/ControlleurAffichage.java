package controleur;

import dao.DAOClient;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import metier.Client;
import vues.VueAffichage;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class ControlleurAffichage {
    private static VueAffichage vueAffichage;

    /**
     *
     * @param typeSociete
     */
    public static void init(String typeSociete) throws ExceptionMetier, ExceptionDAO {
        vueAffichage = new VueAffichage();
        vueAffichage.setSize(500,400);

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
