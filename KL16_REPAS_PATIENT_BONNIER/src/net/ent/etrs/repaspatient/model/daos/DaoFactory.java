package net.ent.etrs.repaspatient.model.daos;

public final class DaoFactory {


    private RepasMemDao repasMemDaoInstance;

    private PatientMemDao patientMemDaoInstance;

    private DaoFactory() {
    }

    public static IRepasMemDao fabriqueRepasDao() {
        return new RepasMemDao();
    }

    public static IPatientMemDao fabriquePatientDao() {
        return new PatientMemDao();
    }


}
