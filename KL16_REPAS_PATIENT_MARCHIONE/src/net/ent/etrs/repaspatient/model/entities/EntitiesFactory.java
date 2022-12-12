package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

/**
 * The type Entities factory.
 */
public final class EntitiesFactory {
    private EntitiesFactory() {
    }


    /**
     * Fabriquer repas.
     *
     * @param dateRepas la date repas
     * @param typeRepas the type repas
     */
    public static void fabriquerRepas(LocalDate dateRepas, TypeRepas typeRepas) {

    }



//    public static void fabriquerPatient(String nom, String numSecu, UUID id, LocalDate dateEntree, Integer nouveauMalade) {
//            if (nouveauMalade > 0) {
//                try {
//                    Patient patient = new Patient (nom, numSecu, id, dateEntree);
//                } catch (PatientConstructionException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//    }

}
