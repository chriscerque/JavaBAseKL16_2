package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public final class EntitiesFactory {

    public EntitiesFactory() {
    }

    public static Repas fabriquerRepas(LocalDate dateRepas, TypeRepas typeRepas){
            return new Repas(dateRepas, typeRepas);

    }

    public static Patient fabriquerPatient(String numSecu, String nom, String prenom, LocalDate dateEntree) throws PatientConstructionException {
        Patient p = new Patient(numSecu, nom, prenom, dateEntree);
        return p;
    }
}
