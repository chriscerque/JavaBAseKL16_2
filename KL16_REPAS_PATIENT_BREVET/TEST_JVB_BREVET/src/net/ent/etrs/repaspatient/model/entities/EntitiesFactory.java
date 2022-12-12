package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public final class EntitiesFactory {
    //region CONSTRUCTEUR(S)

    private EntitiesFactory() {
    }

    //endregion
    //region MÉTHODES

    /**
     * Fabrique un Repas.
     *
     * @param dateRepas le date du repas
     * @param typeRepas le type de repastin, midi, soir
     * @return le repas créé
     * @throws RepasConstructionException si une exception à été lever lors de la construction de l'objet
     */
    public static Repas fabriquerRepas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasConstructionException {
        try {
            return new Repas(dateRepas, typeRepas);
        } catch (RepasException e) {
            throw new RepasConstructionException(e.getMessage(), e);
        }
    }

    /**
     * Fabrique un patient.
     *
     * @param numSecu    le numéro de sécurité social du patient
     * @param nom        le nom du patient
     * @param prenom     le prénom du patient
     * @param dateEntree la date d'entrée du patient
     * @return le patient créé
     * @throws PatientConstructionException si une eception àn été lever à la création
     */
    public static Patient fabriquerPatient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree) throws PatientConstructionException {
        try {
            return new Patient(dateEntree, numSecu, nom, prenom);
        } catch (PatientException e) {
            throw new PatientConstructionException(e.getMessage(), e);
        }
    }
    //endregion
}
