package net.ent.etrs.repaspatient.model.daos;

import java.util.Objects;

public final class DaoFactory {

    private static PatientMemDao patientMemDaoInstance;
    private static RepasMemDao repasMemDaoInstance;

    
    private DaoFactory() {
    }

    /**
     * Fabrique une DAO pour la gestion des entités.
     *
     * @return l'instance de {@link PatientMemDao}
     */

    public static IPatientMemDao fabriquerPatientDao() {
        if (Objects.isNull(patientMemDaoInstance)) {
            return new PatientMemDao();
        }
        return patientMemDaoInstance;
    }

    /**
     * Fabrique une DAO pour la gestion des entités.
     *
     * @return l'instance de {@link RepasMemDao}
     */
    public static IRepasMemDao fabriquerRepasDao() {
        if (Objects.isNull(repasMemDaoInstance)) {
            return new RepasMemDao();
        }
        return repasMemDaoInstance;
    }

}