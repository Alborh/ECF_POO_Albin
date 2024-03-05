package vues;

import controleur.ControlleurAffichage;

import javax.swing.*;
import java.awt.event.*;

public class VueAffichage extends JDialog {
    private JPanel contentPane;
    private JButton buttonRetourAcceuil;
    private JButton buttonQuitter;
    public JScrollPane scrollPane;
    public JPanel pannelPrincipal;
    public JLabel labTitre;

    public VueAffichage() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonRetourAcceuil);
        setSize(1200,400);

        buttonRetourAcceuil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControlleurAffichage.onRetourAcceuil();
            }
        });

        buttonQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControlleurAffichage.onQuitter();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ControlleurAffichage.onQuitter();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControlleurAffichage.onQuitter();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
}
