package net.ent.etrs.repaspatient.model.entities.references;

/**
 * The enum Regime alimentaire.
 */
public enum RegimeAlimentaire {


    /**
     * Vegan regime alimentaire.
     */
    VEGAN("végan"),
    /**
     * Vegetarien regime alimentaire.
     */
    VEGETARIEN("Végétarien"),
    /**
     * Mixe regime alimentaire.
     */
    MIXE("mixé"),
    /**
     * The Sans sel.
     */
    SANS_SEL("sans sel"),
    /**
     * Diabetique regime alimentaire.
     */
    DIABETIQUE("diabétique");

    //------------------------------------------------
    //          ATTRIBUTS
    //------------------------------------------------
    private final String libelle;

    //------------------------------------------------
    //          Constructeur
    //------------------------------------------------
    RegimeAlimentaire(String libelle) {
        this.libelle = libelle;
    }

    //------------------------------------------------
    //          Getter
    //------------------------------------------------

    /**
     * Gets libelle.
     *
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }


}
