package net.ent.etrs.repaspatient.model.entities.references;


public enum RegimeAlimentaireBug {
    VEGAN("végan"),
    VEGETARIEN("Végétarien"),
    MIXE("mixé"),
    SANS_SEL("sans sel"),
    DIABETIQUE("diabétique");

    private final String libelle;


    RegimeAlimentaireBug(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle(){
        return libelle;
    }

    @Override
    public String toString() {
        return "RegimeAlimentaire{" +
                "libelle='" + libelle + '\'' +
                "} " + super.toString();
    }


}
