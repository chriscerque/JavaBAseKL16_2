package net.ent.etrs.repaspatient.model.daos;

public class DaoFactory {
    private static RepasMemDao repasMemDaoInstance;
    private static PatientMemDao patientMemDaoInstance;

    private DaoFactory() {}

    public static IPatientMemDao fabriquerPatientDao() {
        if (patientMemDaoInstance == null) {
            patientMemDaoInstance = new PatientMemDao();
        }
        return patientMemDaoInstance;
    }

    public static IRepasMemDao fabriquerRepasDao() {
        if (patientMemDaoInstance == null) {
            repasMemDaoInstance = new RepasMemDao();
        }
        return repasMemDaoInstance;
    }
}
