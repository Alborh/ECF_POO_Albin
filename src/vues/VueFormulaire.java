package vues;

import controleur.ControleurFormulaire;
import exception.ExceptionControleur;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import log.LoggerPoo;

import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Level;

public class VueFormulaire extends JDialog {
    private JPanel contentPane;
    private JButton buttonValider;
    private JButton buttonQuitter;
    private JButton retourAcceuilButton;

    private JPanel pannel;
    public JLabel labTitre;
    public JTextField textFieldRaisonSociale;
    public JTextField textFieldNumeroRue;
    public JTextField textFieldNomRue;
    public JTextField textFieldCodePostal;
    public JTextField textFieldVille;
    public JTextField textFieldTelephone;
    public JTextField textFieldMail;
    public JTextField textFieldCommentaires;
    public JLabel labChiffreDAffaire;
    public JTextField textFieldChiffreDAffaire;
    public JTextField textFieldNombreEmploye;
    public JTextField dateTextField;
    public JRadioButton ouiRadioButton;
    public JRadioButton nonRadioButton;
    public JLabel labEmploye;
    public JLabel labDate;
    public JLabel labInteresse;

    public VueFormulaire() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonValider);
        //pannelClient.setVisible(false);

        retourAcceuilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControleurFormulaire.onRetourAcceuil();
            }
        });

        buttonValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ControleurFormulaire.onValider();
                } catch (ExceptionMetier ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur Metier : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (ExceptionDAO ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur DAO : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (ExceptionControleur ex) {
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur Controleur : "+ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }
        });

        buttonQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControleurFormulaire.onQuitter();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ControleurFormulaire.onQuitter();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControleurFormulaire.onQuitter();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        ouiRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ouiRadioButton.setEnabled(false);
                nonRadioButton.setEnabled(true);
                nonRadioButton.setSelected(false);
            }
        });

        nonRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nonRadioButton.setEnabled(false);
                ouiRadioButton.setEnabled(true);
                ouiRadioButton.setSelected(false);
            }
        });
    }
}
