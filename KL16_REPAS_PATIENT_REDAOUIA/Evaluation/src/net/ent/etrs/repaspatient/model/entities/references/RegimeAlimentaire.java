package net.ent.etrs.repaspatient.model.entities.references;


import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;

import java.util.Arrays;

public enum RegimeAlimentaire {

    VEGAN("végan"),
    VEGETARIEN("Végétarien"),
    MIXE("mixé"),
    SANS_SEL("sans sel"),
    DIABETIQUE("diabétique");


    private final String libelle;


    private RegimeAlimentaire(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public RegimeAlimentaire getByLibelle(String libelle) throws RegimeAlimentaireException {
        for (RegimeAlimentaire ra : Arrays.asList(values())){
            if (ra.getLibelle() == libelle){
                return ra;
            }
        }
        throw new RegimeAlimentaireException(ConstantesMetier.REGIME_ALIMENTAIRE_GET_BY_LIBELLE_EXCEPTION);
    }
}
