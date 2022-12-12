package net.ent.etrs.repaspatient.model.entities.references;


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

    public String getRegime() {
        return libelle;
    }
}
