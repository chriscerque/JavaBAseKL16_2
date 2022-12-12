package net.ent.etrs.repaspatient.model.entities.references;

import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;


public enum RegimeAlimentaire {
    VEGAN("végan"),
    VEGETARIEN("Végétarien"),
    MIXE("mixé"),
    SANS_SEL("sans sel"),
    DIABETIQUE("diabétique");

    private String libelle;

    RegimeAlimentaire(final String libelle) {
        this.libelle = libelle;
    }

    public static RegimeAlimentaire getByLibelle(String libelle) throws RegimeAlimentaireException {
        if (libelle == null) {
            throw new RegimeAlimentaireException(ConstantesMetier.REGIME_ALIMENTAIRE_GET_BY_LIBELLE_EXCEPTION);
        }
        for (RegimeAlimentaire value : RegimeAlimentaire.values()) {
            if (value.getLibelle().equals(libelle)) {
                return value;
            }
        }
        throw new RegimeAlimentaireException(ConstantesMetier.REGIME_ALIMENTAIRE_GET_BY_LIBELLE_EXCEPTION);
    }

    public String getLibelle() {
        return this.libelle;
    }
}
