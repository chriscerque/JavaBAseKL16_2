package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public final class EntitiesFactory {
    private EntitiesFactory() {
    }

    public static Repas fabriquerRepas(final LocalDate daterepas, final TypeRepas typeRepas) throws RepasConstructionException {
        try {
            return new Repas(daterepas, typeRepas);
        } catch (RepasException e) {
            throw new RepasConstructionException(e);
        }
    }

    public static Patient fabriquerPatient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree) throws PatientConstructionException {
        try {
            return new Patient(numSecu, nom, prenom, dateEntree);
        } catch (PatientException e) {
            throw new PatientConstructionException(e);
        }
    }
}
