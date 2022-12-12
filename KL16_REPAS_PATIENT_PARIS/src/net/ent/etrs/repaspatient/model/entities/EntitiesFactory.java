package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public final class EntitiesFactory {

    private EntitiesFactory(){

    }

    public static Patient fabriquerPatient(LocalDate dateEntree, String numSecu, String nom, String prenom){
        Patient patient = new Patient(dateEntree, numSecu, nom, prenom);
        return patient;
    }

    public static Repas fabriquerRepas(LocalDate dateRepas, TypeRepas typeRepas){
        Repas repas = new Repas(dateRepas, typeRepas);
        return repas;
    }

}
