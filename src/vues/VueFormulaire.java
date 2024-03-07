package vues;

import controleur.ControleurFormulaire;
import dao.DAOClient;
import dao.DAOProspect;
import exception.ExceptionControleur;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import log.LoggerPoo;
import metier.Client;
import metier.Prospect;
import outils.Outils;

import javax.swing.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

/**
 * Vue du formulaire
 */
public class VueFormulaire extends JDialog {
    private JPanel contentPane;
    private JButton buttonValider;
    private JButton buttonQuitter;
    private JButton retourAcceuilButton;
    private JPanel pannel;
    private JLabel labTitre;
    private JTextField textFieldRaisonSociale;
    private JTextField textFieldNumeroRue;
    private JTextField textFieldNomRue;
    private JTextField textFieldCodePostal;
    private JTextField textFieldVille;
    private JTextField textFieldTelephone;
    private JTextField textFieldMail;
    private JTextField textFieldCommentaires;
    private JLabel labChiffreDAffaire;
    private JTextField textFieldChiffreDAffaire;
    private JTextField textFieldNombreEmploye;
    private JTextField dateTextField;
    private JRadioButton ouiRadioButton;
    private JRadioButton nonRadioButton;
    private JLabel labEmploye;
    private JLabel labDate;
    private JLabel labInteresse;
    private int id;

    /**
     * création de la vue du formulaire
     * @param typeFormulaire String Creation, Modification ou Suppression
     * @param typeSociete String Client ou Prospect
     * @param raisonSociale String raison sociale de la société
     */
    public VueFormulaire(String typeFormulaire, String typeSociete, String raisonSociale) {
        try {
            setContentPane(contentPane);
            setModal(true);
            getRootPane().setDefaultButton(buttonValider);
            initComposants(typeFormulaire, typeSociete, raisonSociale);
            actionListeners();
        } catch (ExceptionMetier | ExceptionDAO e) {
            Outils.fenetrePopUp("Erreur",e.getMessage());
            System.out.println(e.getMessage());
        } catch (Exception e){
            Outils.fenetrePopUp("Erreur",e.getMessage());
            System.out.println(e.getMessage());
            LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur : "+e.getMessage());
        }
    }

    /**
     * Initialiste les composants du formulaire
     * @param typeFormulaire String Creation, Modification ou Suppression
     * @param typeSociete String Client ou Prospect
     * @param raisonSociale String raison sociale de la société
     * @throws Exception remonte les exceptions
     */
    public void initComposants(String typeFormulaire, String typeSociete, String raisonSociale) throws Exception {
        setSize(400, 500);
        //options client
        labChiffreDAffaire.setVisible(false);
        textFieldChiffreDAffaire.setVisible(false);
        labEmploye.setVisible(false);
        textFieldNombreEmploye.setVisible(false);
        //options prospect
        labDate.setVisible(false);
        dateTextField.setVisible(false);
        labInteresse.setVisible(false);
        ouiRadioButton.setVisible(false);
        nonRadioButton.setVisible(false);
        ouiRadioButton.setSelected(true);
        ouiRadioButton.setEnabled(false);
        labTitre.setText(typeFormulaire + " de " + typeSociete);
        //rend les champs visibles en fonction  du type de société
        switch (typeSociete) {
            case "Client" -> {
                labChiffreDAffaire.setVisible(true);
                textFieldChiffreDAffaire.setVisible(true);
                labEmploye.setVisible(true);
                textFieldNombreEmploye.setVisible(true);
            }
            case "Prospect" -> {
                labDate.setVisible(true);
                dateTextField.setVisible(true);
                labInteresse.setVisible(true);
                ouiRadioButton.setVisible(true);
                nonRadioButton.setVisible(true);
            }
        }
        //rempli les champs ou non en fonction du type de formulaire
        if (!typeFormulaire.equals("Creation")) {
            switch (typeSociete) {
                case "Client" -> {
                    Client client = DAOClient.findByName(raisonSociale);
                    textFieldRaisonSociale.setText(client.getRaisonSociale());
                    textFieldNumeroRue.setText(client.getNumeroRue());
                    textFieldNomRue.setText(client.getNomRue());
                    textFieldCodePostal.setText(client.getCodePostal());
                    textFieldVille.setText(client.getVille());
                    textFieldTelephone.setText(client.getTelephone());
                    textFieldMail.setText(client.getMail());
                    textFieldCommentaires.setText(client.getCommentaire());
                    textFieldChiffreDAffaire.setText(String.valueOf(client.getChiffreDAffaire()));
                    textFieldNombreEmploye.setText(String.valueOf(client.getNbEmploye()));
                }
                case "Prospect" -> {
                    Prospect prospect = DAOProspect.findByName(raisonSociale);
                    textFieldRaisonSociale.setText(prospect.getRaisonSociale());
                    textFieldNumeroRue.setText(prospect.getNumeroRue());
                    textFieldNomRue.setText(prospect.getNomRue());
                    textFieldCodePostal.setText(prospect.getCodePostal());
                    textFieldVille.setText(prospect.getVille());
                    textFieldTelephone.setText(prospect.getTelephone());
                    textFieldMail.setText(prospect.getMail());
                    textFieldCommentaires.setText(prospect.getCommentaire());
                    dateTextField.setText(prospect.getDateProspection().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    if (prospect.getInteresse().equals("Non")) {
                        nonRadioButton.setSelected(true);
                        ouiRadioButton.setSelected(false);
                        nonRadioButton.setEnabled(false);
                        ouiRadioButton.setEnabled(true);
                    }
                }
            }
        }
        //rend les champs non-éditable pour les formulaires de suppression
        if (typeFormulaire.equals("Suppression")) {
            textFieldRaisonSociale.setEditable(false);
            textFieldNumeroRue.setEditable(false);
            textFieldNomRue.setEditable(false);
            textFieldCodePostal.setEditable(false);
            textFieldVille.setEditable(false);
            textFieldTelephone.setEditable(false);
            textFieldMail.setEditable(false);
            textFieldCommentaires.setEditable(false);
            textFieldChiffreDAffaire.setEditable(false);
            textFieldNombreEmploye.setEditable(false);
            dateTextField.setEditable(false);
            ouiRadioButton.setEnabled(false);
            nonRadioButton.setEnabled(false);
        }
        //enregistre l'identifiant de la société pour les formulaires de modification
        if (typeFormulaire.equals("Modification")){
            if (typeSociete.equals("Client")){
                this.id = DAOClient.findByName(raisonSociale).getIdentifiant();
            } else {
                this.id = DAOProspect.findByName(raisonSociale).getIdentifiant();
            }
        }
    }

    /**
     * Mise en place des actionListener des différents bouttons
     */
    public void actionListeners(){
        retourAcceuilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ControleurFormulaire.onRetourAcceuil();
            }
        });

        buttonValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String interesse = "";
                    if (ouiRadioButton.isSelected()){
                        interesse = "Oui";
                    } else if (nonRadioButton.isSelected()){
                        interesse = "Non";
                    }
                    ControleurFormulaire.onValider(textFieldRaisonSociale.getText(),
                            textFieldNumeroRue.getText(),
                            textFieldNomRue.getText(),
                            textFieldCodePostal.getText(),
                            textFieldVille.getText(),
                            textFieldTelephone.getText(),
                            textFieldMail.getText(),
                            textFieldCommentaires.getText(),
                            textFieldChiffreDAffaire.getText(),
                            textFieldNombreEmploye.getText(),
                            dateTextField.getText(),
                            interesse,
                            id);
                    dispose();
                    ControleurFormulaire.onRetourAcceuil();
                } catch (ExceptionMetier | ExceptionControleur | ExceptionDAO ex) {
                    Outils.fenetrePopUp("Erreur",ex.getMessage());
                    System.out.println(ex.getMessage());
                } catch (Exception ex){
                    LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur : "+ex.getMessage());
                    Outils.fenetrePopUp("Erreur",ex.getMessage());
                    System.out.println(ex.getMessage());
                }
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
