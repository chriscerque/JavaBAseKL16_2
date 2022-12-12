package net.ent.etrs.test.model.entities;

import net.ent.etrs.test.model.entities.exception.PatientConstructionException;
import net.ent.etrs.test.model.entities.exception.PatientException;
import net.ent.etrs.test.model.entities.exception.RepasConstructionException;
import net.ent.etrs.test.model.entities.exception.RepasException;
import net.ent.etrs.test.model.entities.references.TypeRepas;

import java.time.LocalDate;

public final class EntitiesFactory {

    private EntitiesFactory() {}

    public static Repas fabriquerRepas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasConstructionException {
        try {
            return new Repas(dateRepas, typeRepas);
        } catch (RepasException e) {
            throw new RepasConstructionException(e.getMessage(), e);
        }
    }

    public static Patient fabriquerPatient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree) throws PatientConstructionException {
        try {
            return new Patient(numSecu, nom, prenom, dateEntree);
        } catch (PatientException e) {
            throw new PatientConstructionException(e.getMessage(), e);
        }
    }


}
