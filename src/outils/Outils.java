package outils;

import javax.swing.*;
import java.util.regex.Pattern;

/**
 * Classe d'utilitaires
 */
public class Outils {
    /**
     * Facilite l'appel d'un JOptionPane
     * @param titre String
     * @param message String
     */
    public static void fenetrePopUp(String titre, String message){
        JOptionPane.showMessageDialog(null,message,titre,JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Vérifie que le String rentré correspond bien à une adresse mail
     * @param mail String
     * @return boolean
     */
    public static boolean regexMail(String mail){
        Pattern patternMail = Pattern.compile("^(.*)@(.*)[.](.*)$");
        return patternMail.matcher(mail).matches();
    }
}
