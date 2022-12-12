package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

/**
 * fabrique les object de la classe metier.
 *
 * @author christophe.cerqueira
 */
public final class EntitiesFactory {
    private EntitiesFactory() {
    }

    /**
     * Fabrique un patient.
     *
     * @param numSecu
     * @param nom
     * @param prenom
     * @param dateEntree
     * @return un patient
     * @throws PatientConstructionException
     */
    public static Patient fabriquerPatient(String numSecu, String nom, String prenom, LocalDate dateEntree) throws PatientConstructionException {
        try {
            return new Patient(numSecu, nom, prenom, dateEntree);
        } catch (PatientException e) {
            throw new PatientConstructionException(e.getMessage(), e);
        }
    }

    /**
     * Fabrique un repas.
     *
     * @param dateRepas
     * @param typeRepas
     * @return un repas
     * @throws RepasConstructionException
     */
    public static Repas fabriquerRepas(LocalDate dateRepas, TypeRepas typeRepas) throws RepasConstructionException {
        try {
            return new Repas(dateRepas, typeRepas);
        } catch (RepasException e) {
            throw new RepasConstructionException(e.getMessage(), e);
        }
    }
}
