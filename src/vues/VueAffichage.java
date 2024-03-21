package vues;

import controleur.ControlleurAffichage;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import log.LoggerPoo;
import outils.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Level;

/**
 * Vue de l'affichage
 */
public class VueAffichage extends JDialog {
    private JPanel contentPane;
    private JButton buttonRetourAcceuil;
    private JButton buttonQuitter;
    private JScrollPane scrollPane;
    private JPanel pannelPrincipal;
    private JLabel labTitre;

    /**
     * Création de la vue affichage
     * @param typeSociete String Client ou Prospect
     */
    public VueAffichage(typeSociete typeSociete) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonRetourAcceuil);
        initComposants(typeSociete);
        actionListeners();
    }

    /**
     * Initialisation des composants de l'affichage
     * @param typeSociete String Client ou Prospect
     */
    public void initComposants(typeSociete typeSociete){
        try {
            setSize(1200, 400);
            labTitre.setText("Affichage " + typeSociete.getNom());
            String[] titre;
            if (typeSociete.equals("Client")){
                titre = new String[]{"Raison sociale", "Numéro rue", "Nom rue", "Code postal", "Ville", "Téléphone",
                        "Mail", "Commentaire", "Chiffre d'affaire", "Nombre d'employés"};
            } else {
                titre = new String[]{"Raison sociale", "Numéro rue", "Nom rue", "Code postal", "Ville", "Téléphone",
                        "Mail", "Commentaire", "Date de Prospection", "Prospect interressé"};

            }
            Object[][] data = ControlleurAffichage.tableAffichage(typeSociete);
            scrollPane.getViewport().add(new JScrollPane(new JTable(data,titre)));
        } catch (ExceptionMetier ex) {
            Outils.fenetrePopUp("Erreur",ex.getMessage());
        } catch (ExceptionDAO ex) {
            if (ex.getGravite()==1){
                Outils.fenetrePopUp("Erreur",ex.getMessage());
            } else {
                Outils.fenetrePopUp("Erreur", ex.getMessage());
                System.exit(1);
            }
        } catch (Exception ex){
            LoggerPoo.LOGGER.log(Level.SEVERE, "Erreur : "+ex.getMessage());
            Outils.fenetrePopUp("Erreur","Une erreur inconnue est survenue");
            System.exit(1);
        }
    }

    /**
     * Mise en place des actionListener des différents bouttons
     */
    public void actionListeners(){
        buttonRetourAcceuil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ControlleurAffichage.onRetourAcceuil();
            }
        });

        buttonQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
}
