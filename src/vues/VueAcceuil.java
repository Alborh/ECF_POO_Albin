package vues;

import controleur.ControleurAcceuil;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import log.LoggerPoo;
import metier.Societe;
import outils.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Vue de l'acceuil
 */
public class VueAcceuil extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonQuitter;
    private JRadioButton clientRadioButton;
    private JRadioButton prospectRadioButton;
    private JButton afficherButton;
    private JButton creationButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JLabel labAcceuil;
    private JPanel pannelPrincipal;
    private JPanel pannelChoixSociete;
    private JComboBox comboBoxChoixSociete;
    private JButton validerButton;

    private typeFormulaire typeFormulaire;
    private typeSociete typeSociete;

    /**
     * Création de la vue acceuil
     */
    public VueAcceuil() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(validerButton);
        initComposants();
        actionListeners();
    }

    /**
     * Initialisation des composants de la vue acceuil
     */
    public void initComposants(){
        setSize(500,400);
        pannelChoixSociete.setVisible(false);
        typeSociete = outils.typeSociete.CLIENT;
        initChoixSociete();
    }

    /**
     * fait en sorte que la combobox ait des choix qui correspondent aux choix entre Client ou Prospect
     */
    public void initChoixSociete(){
        try {
            comboBoxChoixSociete.removeAllItems();
            ArrayList<Societe> societes = ControleurAcceuil.choixSocieteSetChoix(typeSociete);
            for (Societe societe : societes){
                comboBoxChoixSociete.addItem(societe.getRaisonSociale());
            }
            if(societes.isEmpty()){
                comboBoxChoixSociete.addItem("--Aucun Choix--");
            }
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
        clientRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prospectRadioButton.setEnabled(true);
                prospectRadioButton.setSelected(false);
                clientRadioButton.setEnabled(false);
                typeSociete = outils.typeSociete.CLIENT;
                initChoixSociete();
            }
        });

        prospectRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientRadioButton.setEnabled(true);
                clientRadioButton.setSelected(false);
                prospectRadioButton.setEnabled(false);
                typeSociete = outils.typeSociete.PROSPECT;
                initChoixSociete();
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

        afficherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                    ControleurAcceuil.onAfficher(typeSociete);
                } catch (Exception ex){
                    LoggerPoo.LOGGER.log(Level.SEVERE, "Erreur : "+ex.getMessage());
                    Outils.fenetrePopUp("Erreur","Une erreur inconnue est survenue");
                    System.exit(1);
                }
            }
        });

        creationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                typeFormulaire = outils.typeFormulaire.CREATION;
                try {
                    dispose();
                    ControleurAcceuil.onCreation(typeSociete);
                } catch (Exception ex){
                    LoggerPoo.LOGGER.log(Level.SEVERE, "Erreur : "+ex.getMessage());
                    Outils.fenetrePopUp("Erreur","Une erreur inconnue est survenue");
                    System.exit(1);
                }
            }
        });

        modifierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                typeFormulaire = outils.typeFormulaire.MODIFICATION;
                pannelChoixSociete.setVisible(true);
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                typeFormulaire = outils.typeFormulaire.SUPPRESSION;
                pannelChoixSociete.setVisible(true);
            }
        });
        validerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (comboBoxChoixSociete.getSelectedItem().toString().equals("--Aucun Choix--")){
                        Outils.fenetrePopUp("Erreur","Pas de choix disponibles");
                        System.exit(1);
                    }
                    dispose();
                    switch (typeFormulaire){
                        case MODIFICATION -> {
                            ControleurAcceuil.onModifier(typeSociete, typeFormulaire, comboBoxChoixSociete.getSelectedItem().toString());
                        }
                        case SUPPRESSION -> {
                            ControleurAcceuil.onSupprimer(typeSociete, typeFormulaire, comboBoxChoixSociete.getSelectedItem().toString());
                        }
                    }
                } catch (Exception ex){
                    LoggerPoo.LOGGER.log(Level.SEVERE, "Erreur : "+ex.getMessage());
                    Outils.fenetrePopUp("Erreur","Une erreur inconnue est survenue");
                    System.exit(1);
                }
            }
        });
    }
}
