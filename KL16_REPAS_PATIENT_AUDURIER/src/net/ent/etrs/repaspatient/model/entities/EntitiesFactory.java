package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public final class EntitiesFactory {
    private EntitiesFactory() {
    }

    public static Patient fabriquerPatient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree)
            throws PatientException {
        return new Patient(numSecu, nom, prenom, dateEntree);
    }

    public static Repas fabriquerRepas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasException {
        return new Repas(dateRepas, typeRepas);
    }
}
