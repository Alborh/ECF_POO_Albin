package metier;

import exception.ExceptionMetier;
import log.LoggerPoo;

import java.util.logging.Level;

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
     * setter de l'indentifiant
     * @param identifiant int
     */
    public void setIdentifiant(int identifiant){
        this.identifiant = identifiant;
    }

    /**
     * getter de l'identifiant
     * @return int identifiant
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * setter de la raison sociale
     * @param raisonSociale String
     */
    public void setRaisonSociale(String raisonSociale){
        this.raisonSociale = raisonSociale;
    }

    /**
     * getter de la raison sociale
     * @return String raison sociale
     */
    public String getRaisonSociale(){
        return raisonSociale;
    }

    /**
     * setter du numéro de rue
     * @param numeroRue String
     */
    public void setNumeroRue(String numeroRue){
        this.numeroRue = numeroRue;
    }

    /**
     * getter du numéro de rue
     * @return String numéro de rue
     */
    public String getNumeroRue(){
        return numeroRue;
    }

    /**
     * setter du nom de rue
     * @param nomRue String
     */
    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    /**
     * getter du nom de rue
     * @return String nom de rue
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * setter du code postal
     * @param codePostal String
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * getter du code postal
     * @return String code postal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * setter de la ville
     * @param ville String
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * getter de la ville
     * @return String ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * setter du numéro de téléphone
     * @param telephone String
     * @throws Exception si moins de 10 caractères
     */
    public void setTelephone(String telephone) throws Exception{
        if (telephone.length()<10){
            LoggerPoo.LOGGER.log(Level.WARNING,"Erreur Métier : Numéro de téléphone trop court : doit avoir au moins 10 chiffres");
            throw (new ExceptionMetier("Numéro de téléphone trop court : doit avoir au moins 10 chiffres"));
        }
        this.telephone = telephone;
    }

    /**
     * getter du numéro de téléphone
     * @return String numéro de téléphone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * setter de l'addresse mail
     * @param mail String
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * getter de l'adresse mail
     * @return String adresse mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * setter des commentaires
     * @param commentaire String
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * getter des commentaires
     * @return String commentaires
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Constructeur par défaut
     */
    public Societe(){

    }

    /**
     * Constructeur Parametré
     * @param identifiant int
     * @param raisonSociale String
     * @param numeroRue String
     * @param nomRue String
     * @param codePostal String
     * @param ville String
     * @param telephone String
     * @param mail String
     * @param commentaire String
     * @throws Exception remonte les exceptions
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
