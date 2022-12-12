package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import javax.lang.model.element.TypeElement;
import java.time.LocalDate;

public final class EntitiesFactory {
    private EntitiesFactory() {
    }

    public static Patient fabriquerPatient(LocalDate dateEntree, String nom, String prenom, String numSecu) throws PatientConstructionException {
        try {
            return new Patient(dateEntree, numSecu, nom, prenom);

        } catch (PatientException e) {
            throw new PatientConstructionException(ConstantesMetier.MSG_CONSTRUCTION_PATIENT_EXCEPTION, e);
        }
    }

    public static Repas fabriquerRepas(LocalDate dateRepas, TypeRepas typeRepas) throws RepasConstructionException {
        try {
            return new Repas(dateRepas, typeRepas);

        } catch (RepasException e) {
            throw new RepasConstructionException(ConstantesMetier.MSG_CONSTRUCTION_REPAS_EXCEPTION, e);
        }
    }
}
