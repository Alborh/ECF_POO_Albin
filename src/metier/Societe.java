package metier;

import exception.ExceptionMetier;

import static outils.Outils.regexMail;

/**
 * Classe abstraite servant de base pour client et prospect
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
     * Setter de la raison sociale
     * @param raisonSociale String
     */
    public void setRaisonSociale(String raisonSociale) throws Exception {
        if (raisonSociale.isEmpty()){
            throw (new ExceptionMetier("Erreur : raison sociale ne dois pas être vide"));
        }else if (raisonSociale.length() > 50) {
            throw (new ExceptionMetier("Erreur : raison sociale ne dois pas faire plus de 50 caractères"));
        }
        this.raisonSociale = raisonSociale;
    }

    /**
     * Getter de la raison sociale
     * @return String raison sociale
     */
    public String getRaisonSociale(){
        return raisonSociale;
    }

    /**
     * Setter du numéro de rue
     * @param numeroRue String
     */
    public void setNumeroRue(String numeroRue) throws Exception {
        if (numeroRue.isEmpty()){
            throw (new ExceptionMetier("Erreur : numéro rue ne dois pas être vide"));
        } else if (numeroRue.length() > 10) {
            throw (new ExceptionMetier("Erreur : numéro rue ne dois pas faire plus de 10 cacatères"));
        }
        this.numeroRue = numeroRue;
    }

    /**
     * Getter du numéro de rue
     * @return String numéro de rue
     */
    public String getNumeroRue(){
        return numeroRue;
    }

    /**
     * Setter du nom de rue
     * @param nomRue String
     */
    public void setNomRue(String nomRue) throws Exception {
        if(nomRue.isEmpty()){
            throw (new ExceptionMetier("Erreur : nom rue ne dois pas être vide"));
        } else if (nomRue.length() > 30) {
            throw (new ExceptionMetier("Erreur : nom rue ne dois pas faire plus de 30 caractères"));
        }
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
    public void setCodePostal(String codePostal) throws Exception {
        if (codePostal.isEmpty()){
            throw (new ExceptionMetier("Erreur : code postal ne dois pas être vide"));
        } else if (codePostal.length() > 5) {
            throw (new ExceptionMetier("Erreur : code postal ne dois pas faire plus de 5 caractères"));
        }
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
    public void setVille(String ville) throws Exception {
        if (ville.isEmpty()){
            throw (new ExceptionMetier("Erreur : ville ne dois pas être vide"));
        } else if (ville.length() > 20) {
            throw (new ExceptionMetier("Erreur : ville ne dois pas faire plus de 20 caractères"));
        }
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
     * Setter du numéro de téléphone
     * @param telephone String
     * @throws Exception si moins de 10 caractères
     */
    public void setTelephone(String telephone) throws Exception{
        if (telephone.isEmpty()){
            throw (new ExceptionMetier("Erreur : telephone ne dois pas être vide"));
        } else if (telephone.length() > 15) {
            throw (new ExceptionMetier("Erreur : telephone ne dois pas faire plus de 15 caractères"));
        } else if (telephone.length()<10){
            throw (new ExceptionMetier("Numéro de téléphone trop court : doit avoir au moins 10 chiffres"));
        }
        this.telephone = telephone;
    }

    /**
     * Getter du numéro de téléphone
     * @return String numéro de téléphone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Setter de l'addresse mail
     * @param mail String
     */
    public void setMail(String mail) throws Exception {
        if (mail.isEmpty()){
            throw (new ExceptionMetier("Erreur : mail ne dois pas être vide"));
        } else if (mail.length() > 50) {
            throw (new ExceptionMetier("Erreur : mail ne dois pas plus de 50 caractères"));
        }
        if (!regexMail(mail)){
            throw (new ExceptionMetier("Email invalide : doit être au format [adresse]@[mail].[domaine]"));
        }
        this.mail = mail;
    }

    /**
     * Getter de l'adresse mail
     * @return String adresse mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Setter des commentaires
     * @param commentaire String
     */
    public void setCommentaire(String commentaire) throws Exception {
        if (commentaire.length()>100){
            throw (new ExceptionMetier("Erreur : commentaire ne dois pas faire plus de 100 caractères"));
        }
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

    /**
     * fonction toString
     * @return String décrivant la société
     */
    public String toString(){
        return getIdentifiant()+" "+getRaisonSociale()+" "+getNumeroRue()+" "+getNomRue()+", "+getCodePostal()+" "
                +getVille()+" "+getTelephone()+" "+getMail()+"\n"+getCommentaire();
    }
}
