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


    public String getLibelle(){
        return libelle;
    }


    public static RegimeAlimentaire getLibelle(String libelle){
        for (RegimeAlimentaire regimeAlimentaire : RegimeAlimentaire.values()){
            if (regimeAlimentaire.getLibelle().equals(libelle)){
                return regimeAlimentaire;
            }
        }
        return null;
    }
}
