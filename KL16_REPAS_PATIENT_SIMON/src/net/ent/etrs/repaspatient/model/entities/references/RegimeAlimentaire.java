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

    public String getLibelle() {
        return libelle;
    }
    public static RegimeAlimentaire getByLibelles(String lib) throws RegimeAlimentaireException {
        for (RegimeAlimentaire ra : RegimeAlimentaire.values()){
            if (ra.getLibelle().equals(lib)){
                return ra;
            }
        }
        throw new RegimeAlimentaireException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
    }

}
