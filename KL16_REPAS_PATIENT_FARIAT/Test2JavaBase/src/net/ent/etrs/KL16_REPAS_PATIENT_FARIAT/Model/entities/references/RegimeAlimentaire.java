package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.references;

import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.excetions.RegimeAlimentaireException;

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

    public static RegimeAlimentaire getByLibelle(final String lib) throws RegimeAlimentaireException {
        for (RegimeAlimentaire t : RegimeAlimentaire.values()) {
            if (t.getLibelle().equals(lib)) {
                return t;
            }
        }


        throw new RegimeAlimentaireException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
    }


    public String getLibelle() {
        return this.libelle;
    }


}
