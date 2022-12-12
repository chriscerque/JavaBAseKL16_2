package net.ent.etrs.repaspatient.model.entities.references;

import net.ent.etrs.repaspatient.model.entities.exceptions.ConstantesException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;

public enum RegimeAlimentaire {
    // ATTRIBUTES
    VEGAN("végan"),
    VEGETARIEN("Végétarien"),
    MIXE("mixé"),
    SANS_SEL("sans sel"),
    DIABETIQUE("diabétique");

    private String libelle;

    // CONSTRUCTOR
    RegimeAlimentaire(String libelle) {
        this.libelle = libelle;
    }

    public static RegimeAlimentaire getByLibelle(String lib) throws RegimeAlimentaireException {
        for (RegimeAlimentaire regimeAlimentaire : RegimeAlimentaire.values()) {
            if (regimeAlimentaire.getLibelle().equals(lib)) {
                return regimeAlimentaire;
            }
        }
        throw new RegimeAlimentaireException(ConstantesException.REGIME_ALIMENTAIRE_EXCEPTION);
    }

    // GETTER SETTER
    public String getLibelle() {
        return this.libelle;
    }

    // TO STRING
    @Override
    public String toString() {
        return "RegimeAlimentaire{" +
                "libelle='" + libelle + '\'' +
                "} " + super.toString();
    }
}

