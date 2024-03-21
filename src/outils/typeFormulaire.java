package outils;

/**
 * Énumération des types de formulaires
 */
public enum typeFormulaire {
    CREATION("Création"),
    MODIFICATION("Modification"),
    SUPPRESSION("Suppression");
    private final String nom;

    typeFormulaire(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return String
     */
    public String getNom() {
        return nom;
    }
}
