package vues;

import controleur.ControlleurAffichage;
import exception.ExceptionDAO;
import exception.ExceptionMetier;
import log.LoggerPoo;
import metier.Client;
import metier.Prospect;
import outils.*;

import javax.swing.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
            Object[][] data;
            if (typeSociete == outils.typeSociete.CLIENT){
                titre = new String[]{"Raison sociale", "Numéro rue", "Nom rue", "Code postal", "Ville", "Téléphone",
                        "Mail", "Commentaire", "Chiffre d'affaire", "Nombre d'employés"};
                ArrayList<Client> clients = ControlleurAffichage.listeClients();
                data = new Object[clients.size()][10];
                for (int i = 0; i<clients.size(); i++){
                    data[i][0] = clients.get(i).getRaisonSociale();
                    data[i][1] = clients.get(i).getNumeroRue();
                    data[i][2] = clients.get(i).getNomRue();
                    data[i][3] = clients.get(i).getCodePostal();
                    data[i][4] = clients.get(i).getVille();
                    data[i][5] = clients.get(i).getTelephone();
                    data[i][6] = clients.get(i).getMail();
                    data[i][7] = clients.get(i).getCommentaire();
                    data[i][8] = clients.get(i).getChiffreDAffaire();
                    data[i][9] = clients.get(i).getNbEmploye();
                }
            } else {
                titre = new String[]{"Raison sociale", "Numéro rue", "Nom rue", "Code postal", "Ville", "Téléphone",
                        "Mail", "Commentaire", "Date de Prospection", "Prospect interressé"};
                ArrayList<Prospect> prospects = ControlleurAffichage.listeProspect();
                data = new Object[prospects.size()][10];
                for (int i = 0; i<prospects.size(); i++){
                    data[i][0] = prospects.get(i).getRaisonSociale();
                    data[i][1] = prospects.get(i).getNumeroRue();
                    data[i][2] = prospects.get(i).getNomRue();
                    data[i][3] = prospects.get(i).getCodePostal();
                    data[i][4] = prospects.get(i).getVille();
                    data[i][5] = prospects.get(i).getTelephone();
                    data[i][6] = prospects.get(i).getMail();
                    data[i][7] = prospects.get(i).getCommentaire();
                    data[i][8] = prospects.get(i).getDateProspection().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    data[i][9] = prospects.get(i).getInteresse();
                }
            }
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
