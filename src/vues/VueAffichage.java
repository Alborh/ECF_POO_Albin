package vues;

import controleur.ControlleurAffichage;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import log.LoggerPoo;
import outils.Outils;

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
    public VueAffichage(String typeSociete) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonRetourAcceuil);
        initComposants(typeSociete);
        actionListeners();
    }

    /**
     * initialisation des composants de l'affichage
     * @param typeSociete String Client ou Prospect
     */
    public void initComposants(String typeSociete){
        try {
            setSize(1200, 400);
            labTitre.setText("Affichage " + typeSociete);
            scrollPane.getViewport().add(new JScrollPane(ControlleurAffichage.tableAffichage(typeSociete)));
        } catch (ExceptionMetier | ExceptionDAO ex) {
            Outils.fenetrePopUp("Erreur",ex.getMessage());
            System.out.println(ex.getMessage());
        } catch (Exception ex){
            LoggerPoo.LOGGER.log(Level.SEVERE, "Erreur : "+ex.getMessage());
            Outils.fenetrePopUp("Erreur",ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    /**
     * mise en place des actionListener des différents bouttons
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
