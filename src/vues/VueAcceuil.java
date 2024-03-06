package vues;

import controleur.ControleurAcceuil;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import log.LoggerPoo;
import metier.Societe;
import outils.Outils;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;

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

    private String typeFormulaire;
    private String typeSociete;

    public VueAcceuil() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(validerButton);
        initComposants();
        actionListeners();

    }
    public void initComposants(){
        setSize(500,400);
        pannelChoixSociete.setVisible(false);
        typeSociete = "Client";
        initChoixSociete();
    }
    public void initChoixSociete(){
        try {
            comboBoxChoixSociete.removeAllItems();
            ArrayList<Societe> societes = ControleurAcceuil.choixSocieteSetChoix(typeSociete);
            for (Societe societe : societes){
                comboBoxChoixSociete.addItem(societe.getRaisonSociale());
            }
        } catch (ExceptionMetier | ExceptionDAO ex) {
            Outils.fenetrePopUp("Erreur",ex.getMessage());
            System.out.println(ex.getMessage());
        } catch (Exception ex){
            LoggerPoo.LOGGER.log(Level.SEVERE, "Erreur : "+ex.getMessage());
            Outils.fenetrePopUp("Erreur",ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    public void actionListeners(){
        clientRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prospectRadioButton.setEnabled(true);
                prospectRadioButton.setSelected(false);
                clientRadioButton.setEnabled(false);
                typeSociete = "Client";
                initChoixSociete();
            }
        });

        prospectRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientRadioButton.setEnabled(true);
                clientRadioButton.setSelected(false);
                prospectRadioButton.setEnabled(false);
                typeSociete = "Prospect";
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
                } catch (ExceptionMetier | ExceptionDAO ex) {
                    Outils.fenetrePopUp("Erreur",ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (Exception ex){
                    LoggerPoo.LOGGER.log(Level.SEVERE, "Erreur : "+ex.getMessage());
                    Outils.fenetrePopUp("Erreur",ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }
        });

        creationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                typeFormulaire = "Creation";
                try {
                    dispose();
                    ControleurAcceuil.onCreation(typeSociete);
                } catch (ExceptionMetier | ExceptionDAO ex) {
                    Outils.fenetrePopUp("Erreur",ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (Exception ex){
                    LoggerPoo.LOGGER.log(Level.SEVERE, "Erreur : "+ex.getMessage());
                    Outils.fenetrePopUp("Erreur",ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }
        });

        modifierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                typeFormulaire = "Modification";
                pannelChoixSociete.setVisible(true);
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                typeFormulaire = "Suppression";
                pannelChoixSociete.setVisible(true);
            }
        });
        validerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                    switch (typeFormulaire){
                        case "Modification" -> {
                            ControleurAcceuil.onModifier(typeSociete, typeFormulaire, comboBoxChoixSociete.getSelectedItem().toString());
                        }
                        case "Suppression" -> {
                            ControleurAcceuil.onSupprimer(typeSociete, typeFormulaire, comboBoxChoixSociete.getSelectedItem().toString());
                        }
                    }
                } catch (ExceptionMetier | ExceptionDAO ex) {
                    Outils.fenetrePopUp("Erreur",ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (Exception ex){
                    LoggerPoo.LOGGER.log(Level.SEVERE, "Erreur : "+ex.getMessage());
                    Outils.fenetrePopUp("Erreur",ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
}
