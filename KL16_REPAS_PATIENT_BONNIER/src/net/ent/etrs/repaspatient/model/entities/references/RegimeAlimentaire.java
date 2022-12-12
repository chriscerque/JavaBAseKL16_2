package net.ent.etrs.repaspatient.model.entities.references;

import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;

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

    /**
     * Permet de récupérer le libelle de chaque enum.
     */

    public static RegimeAlimentaire getByLibelle(final String libelle) throws RegimeAlimentaireException {
        for (RegimeAlimentaire r : RegimeAlimentaire.values()) {
            if (r.getLibelle().equals(libelle)) {
                return r;
            }
        }
        throw new RegimeAlimentaireException(ConstantesMetier.REGIME_ALIMENTAIRE_GET_BY_LIBELLE_EXCEPTION);
    }

    public String getLibelle() {
        return libelle;
    }

}
