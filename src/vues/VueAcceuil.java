package vues;

import controleur.ControleurAcceuil;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import log.LoggerPoo;

import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Level;

public class VueAcceuil extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonQuitter;
    public JRadioButton clientRadioButton;
    public JRadioButton prospectRadioButton;
    private JButton afficherButton;
    private JButton creationButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JLabel labAcceuil;
    private JPanel pannelPrincipal;
    public JPanel pannelChoixSociete;
    public JComboBox comboBoxChoixSociete;
    public JButton validerButton;

    public String typeFormulaire;

    public VueAcceuil() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(validerButton);

        clientRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prospectRadioButton.setEnabled(true);
                prospectRadioButton.setSelected(false);
                clientRadioButton.setEnabled(false);
                try {
                    ControleurAcceuil.choixSocieteSetChoix();
                } catch (ExceptionMetier ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur Metier : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (ExceptionDAO ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }
        });

        prospectRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientRadioButton.setEnabled(true);
                clientRadioButton.setSelected(false);
                prospectRadioButton.setEnabled(false);
                try {
                    ControleurAcceuil.choixSocieteSetChoix();
                } catch (ExceptionMetier ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur Metier : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (ExceptionDAO ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }
        });

        buttonQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControleurAcceuil.onQuitter();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ControleurAcceuil.onQuitter();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControleurAcceuil.onQuitter();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        afficherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ControleurAcceuil.onAfficher();
                } catch (ExceptionMetier ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur Metier : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (ExceptionDAO ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }
        });

        creationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                typeFormulaire = "Creation";
                try {
                    ControleurAcceuil.onCreation();
                } catch (ExceptionMetier ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur Metier : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (ExceptionDAO ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }
        });

        modifierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                typeFormulaire = "Modification";
                pannelChoixSociete.setVisible(true);
                try {
                    ControleurAcceuil.choixSocieteSetChoix();
                } catch (ExceptionMetier ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur Metier : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (ExceptionDAO ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                typeFormulaire = "Suppression";
                pannelChoixSociete.setVisible(true);
                try {
                    ControleurAcceuil.choixSocieteSetChoix();
                } catch (ExceptionMetier ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur Metier : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (ExceptionDAO ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }
        });
        validerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    switch (typeFormulaire){
                        case "Modification" -> {
                            ControleurAcceuil.onModifier();
                        }
                        case "Suppression" -> {
                            ControleurAcceuil.onSupprimer();
                        }
                    }
                } catch (ExceptionMetier ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur Metier : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (ExceptionDAO ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
}
