package net.ent.etrs.kl16repaspatientgouin.model.entities.references;


import net.ent.etrs.kl16repaspatientgouin.model.entities.exceptions.RegimeAlimentaireException;



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
        return this.libelle;
    }

    public static RegimeAlimentaire getBylibelle(String libelle) throws RegimeAlimentaireException {
        if(libelle == null || libelle.isBlank()){
            throw new RegimeAlimentaireException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }

        for (RegimeAlimentaire regime : RegimeAlimentaire.values()){
            if(regime.libelle.equals(libelle)){
                return regime;
            }
        }
        throw new RegimeAlimentaireException(ConstantesMetier.REGIME_ALIMENTAIRE_GET_BY_LIBELLE_EXCEPTION);
    }
}
