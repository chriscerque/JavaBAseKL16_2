package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public final class EntitiesFactory {

    /**
     * Fabrique un repas.
     * @param dateRepas
     * @param typeRepas
     * @return
     */
    public static Repas fabriquerRepas(final LocalDate dateRepas, final TypeRepas typeRepas){
        return new Repas(dateRepas,typeRepas);
    }

    /**
     * Fabrique un patient.
     * @param id
     * @param numSecu
     * @param nom
     * @param dateEntree
     * @return
     */

    public static Patient fabriquerPatient(final String id, final String numSecu, final String nom, final LocalDate dateEntree){
        return new Patient(id,numSecu,nom,dateEntree);
    }

    /**
     * Constructeur.
     */

    private EntitiesFactory() {
    }
}
