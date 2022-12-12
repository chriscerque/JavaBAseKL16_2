package net.ent.etrs.test.model.entities.references;


import net.ent.etrs.test.model.entities.exception.RegimeAlimentaireException;


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


    public String getLibelle() {
        return libelle;
    }


    public static RegimeAlimentaire getByLibelle(final String libelle) throws RegimeAlimentaireException {
        for (RegimeAlimentaire r : RegimeAlimentaire.values()) {
            if (r.getLibelle().equals(libelle)) {
                return r;
            }
        }
        throw new RegimeAlimentaireException(String.format(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION, libelle));
    }

   
}
