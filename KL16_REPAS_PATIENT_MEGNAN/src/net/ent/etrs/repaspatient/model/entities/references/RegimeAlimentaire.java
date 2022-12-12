package net.ent.etrs.repaspatient.model.entities.references;

import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;


public enum RegimeAlimentaire {
    VEGAN("végan"),
    VEGETARIEN("Végétarien"),
    MIXE("mixé"),
    SANS_SEL("sans sel"),
    DIABETIQUE("diabétique");

   private String libelle;

    private RegimeAlimentaire(final String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return this.libelle;
    }
}
