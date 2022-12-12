package net.ent.etrs.repaspatient.model.entities.references;

import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;


public enum RegimeAlimentaire {
    VEGAN("végan"),
    VEGETARIEN("Végétarien"),
    MIXE("mixé"),
    SANS_SEL("sans sel"),
    DIABETIQUE("diabétique");

    private String libelle;

    //Constructeur

    RegimeAlimentaire(String libelle) {
        this.libelle = libelle;
    }

    //Accesseur

    public String getLibelle() {
        return libelle;
    }

    public RegimeAlimentaire getByLibelle(String libelle) throws RegimeAlimentaireException {
        for (RegimeAlimentaire regAl : RegimeAlimentaire.values()) {
            if (libelle.equals(regAl.getLibelle())) {
                return regAl;
            }
        }
        throw new RegimeAlimentaireException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
    }
}
