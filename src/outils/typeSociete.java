package outils;

/**
 * Énumération des types de sociétés
 */
public enum typeSociete {
    CLIENT("Client"),
    PROSPECT("Prospect");

    private final String nom;

    typeSociete(String nom) {
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
