package net.ent.etrs.repaspatient.model.daos;

import java.util.Objects;

public class DaoFactory {
    //region ATTRIBUTS
    private static RepasMemDao repasMemDaoInstance;
    private static PatientMemDao patientMemDaoInstance;
    //endregion
    //region CONSTRUCTEUR(S)

    private DaoFactory() {
    }

    //endregion
    //region MÉTHODES
    public static IPatientMemDao fabriquerPatientDao() {
        if (Objects.isNull(patientMemDaoInstance)) {
            patientMemDaoInstance = new PatientMemDao();
        }
        return patientMemDaoInstance;
    }

    public static IRepasMemDao fabriquerRepasDao() {
        if (Objects.isNull(repasMemDaoInstance)) {
            repasMemDaoInstance = new RepasMemDao();
        }
        return repasMemDaoInstance;
    }
    //endregion
}
