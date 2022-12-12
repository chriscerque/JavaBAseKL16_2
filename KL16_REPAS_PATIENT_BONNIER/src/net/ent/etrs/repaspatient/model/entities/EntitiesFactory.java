package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public final class EntitiesFactory {

    private EntitiesFactory() {
    }

    public static Repas fabriquerRepas(final LocalDate dateRepas,final TypeRepas typeRepas) throws RepasConstructionException {
        try {
            return new Repas(dateRepas, typeRepas);
        } catch (RepasException e) {
            throw new RepasConstructionException(ConstantesMetier.REPAS_CONSTRUCTION_IMPOSSIBLE);
        }
    }

    public static Patient fabriquerPatient (final LocalDate dateEntree, final String numSecu, final String nom, final String prenom) throws PatientConstructionException {
        try {
            return new Patient(dateEntree, numSecu, nom, prenom);
        } catch (PatientException e) {
            throw new PatientConstructionException(ConstantesMetier.PATIENT_CONSTRUCTION_IMPOSSIBLE);
        }
    }


}
