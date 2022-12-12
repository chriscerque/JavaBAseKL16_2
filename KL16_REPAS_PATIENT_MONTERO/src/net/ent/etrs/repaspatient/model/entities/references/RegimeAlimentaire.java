package net.ent.etrs.repaspatient.model.entities.references;


import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;

public enum RegimeAlimentaire {
    VEGAN("végan"),
    VEGETARIEN("Végétarien"),
    MIXE("mixé"),
    SANS_SEL("sans sel"),
    DIABETIQUE("diabétique");

    private final String libelle;

    RegimeAlimentaire(final String libelle) {
        this.libelle = libelle;
    }

    /**
     * Retourne la RegimeAlimentaire en fonction de son libelle.
     *
     * @param lib le libellé de la RegimeAlimentaire
     * @return le RegimeAlimentaire trouvée.
     * @throws RegimeAlimentaireException si aucune FamilleTypePiece n'est trouvée.
     */
    public static RegimeAlimentaire getByLibelle(String lib) throws RegimeAlimentaireException {
        for (RegimeAlimentaire regimeAlimentaire : RegimeAlimentaire.values()) {
            if (regimeAlimentaire.getLibelle().equals(lib)) {
                return regimeAlimentaire;
            }
        }
        throw new RegimeAlimentaireException(ConstantesMetier.REGIME_ALIMENTAIRE_GET_BY_LIBELLE_EXCEPTION);
    }

    public String getLibelle() {
        return libelle;
    }
}
