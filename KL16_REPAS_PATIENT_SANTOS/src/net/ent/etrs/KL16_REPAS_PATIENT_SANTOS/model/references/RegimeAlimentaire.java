package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.RegimeAlimentaireException;

public enum RegimeAlimentaire {
    VEGAN("végan"),
    VEGETARIEN("Végétarien"),
    MIXE("mixé"),
    SANS_SEL("sans sel"),
    DIABETIQUE("diabétique");

    private final String libelle;
    RegimeAlimentaire(String libelle) {
        this.libelle = libelle;

    }

    public static RegimeAlimentaire getRegimeAlimentaireByLibelle(final String libelle) throws RegimeAlimentaireException {
        for (RegimeAlimentaire g : RegimeAlimentaire.values()) {
            if (g.getLibelle().equals(libelle)) {
                return g;
            }
        }
        throw new RegimeAlimentaireException(String.format(ConstantesMetier.REGIME_ALIMENTAIRE_GET_BY_LIBELLE_EXCEPTION, libelle));
    }

    public String getLibelle() {
        return this.libelle;
    }


}
