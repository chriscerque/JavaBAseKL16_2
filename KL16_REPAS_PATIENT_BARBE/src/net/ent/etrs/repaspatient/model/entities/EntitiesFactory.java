package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.FarbriquerPatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.FarbriquerRepasException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public final class EntitiesFactory {

    private EntitiesFactory() {
    }

    /**
     * Fabrique un repas.
     *
     * @param date      la date du repas
     * @param typeRepas le type de repas
     * @return un nouveau repas avec les paramètres entrés
     */
    public static Repas fabriquerRepas(final LocalDate date, final TypeRepas typeRepas) throws FarbriquerRepasException {
        try {
            return new Repas(date, typeRepas);
        } catch (RepasException e) {
            throw new FarbriquerRepasException(e.getMessage());
        }
    }

    /**
     * Fabrique un patient.
     *
     * @param numSecu    le numero de sécurité sociale
     * @param nom        le nom
     * @param prenom     le prenom
     * @param dateEntree la date d'entré du patient.
     * @return un nouveau patient avec les paramètres entrés
     */
    public static Patient fabriquerPatient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree) throws FarbriquerPatientException {
        try {
            return new Patient(dateEntree, numSecu, nom, prenom);
        } catch (PatientException e) {
            throw new FarbriquerPatientException(e.getMessage());
        }
    }

}
