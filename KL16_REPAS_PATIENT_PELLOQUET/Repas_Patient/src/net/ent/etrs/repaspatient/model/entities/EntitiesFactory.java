package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public class EntitiesFactory {

    //Constructeur

    private EntitiesFactory() {
    }

    //autres méthodes

    public static Repas fabriquerRepas(LocalDate date, TypeRepas typeRepas) {
        return new Repas(date, typeRepas);
    }

    public static Patient fabriquerPatient(String NumSec, String nom, String prenom, LocalDate dateEntree) {
        return new Patient(dateEntree, NumSec, nom, prenom);
    }
}
