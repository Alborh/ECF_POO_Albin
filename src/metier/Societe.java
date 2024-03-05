package metier;

import exception.ExceptionMetier;
import log.LoggerPoo;

import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * classe abstraite servant de base pour client et prospect
 */
public abstract class Societe {
    private int identifiant;
    private String raisonSociale;
    private String numeroRue;
    private String nomRue;
    private String codePostal;
    private String ville;
    private String telephone;
    private String mail;
    private String commentaire;

    /**
     *
     * @param identifiant
     */
    public void setIdentifiant(int identifiant){
        this.identifiant = identifiant;
    }

    /**
     *
     * @return
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     *
     * @param raisonSociale
     */
    public void setRaisonSociale(String raisonSociale){
        this.raisonSociale = raisonSociale;
    }

    /**
     *
     * @return
     */
    public String getRaisonSociale(){
        return raisonSociale;
    }

    /**
     *
     * @param numeroRue
     */
    public void setNumeroRue(String numeroRue){
        this.numeroRue = numeroRue;
    }

    /**
     *
     * @return
     */
    public String getNumeroRue(){
        return numeroRue;
    }

    /**
     *
     * @param nomRue
     */
    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    /**
     *
     * @return
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     *
     * @param codePostal
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     *
     * @return
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     *
     * @param ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     *
     * @return
     */
    public String getVille() {
        return ville;
    }

    /**
     *
     * @param telephone au moins 10 chiffres
     * @throws Exception
     */
    public void setTelephone(String telephone) throws Exception{
        if (telephone.length()<10){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Métier : Numéro de téléphone trop court : doit avoir au moins 10 chiffres");
            throw (new ExceptionMetier("Numéro de téléphone trop court : doit avoir au moins 10 chiffres"));
        }
        this.telephone = telephone;
    }

    /**
     *
     * @return
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     *
     * @param mail [adresse]@[mail].[domaine]
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     *
     * @return
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param commentaire
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     *
     * @return
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     *
     */
    public Societe(){

    }

    /**
     *
     * @param identifiant
     * @param raisonSociale
     * @param numeroRue
     * @param nomRue
     * @param codePostal
     * @param ville
     * @param telephone
     * @param mail
     * @param commentaire
     * @throws Exception
     */
    public Societe(int identifiant, String raisonSociale, String numeroRue, String nomRue, String codePostal,
                   String ville, String telephone, String mail, String commentaire) throws Exception {
        setIdentifiant(identifiant);
        setRaisonSociale(raisonSociale);
        setNumeroRue(numeroRue);
        setNomRue(nomRue);
        setCodePostal(codePostal);
        setVille(ville);
        setTelephone(telephone);
        setMail(mail);
        setCommentaire(commentaire);
    }
}
