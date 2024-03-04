package outils;

import javax.swing.*;

public class Outils {
    public static void fenetrePopUp(String titre, String message){
        JOptionPane.showMessageDialog(null,message,titre,JOptionPane.PLAIN_MESSAGE);
    }
}
