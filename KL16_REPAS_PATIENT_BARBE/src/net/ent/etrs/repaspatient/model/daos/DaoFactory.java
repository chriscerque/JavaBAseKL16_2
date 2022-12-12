package net.ent.etrs.repaspatient.model.daos;

public class DaoFactory {

    private static IPatientMemDao patientMemDaoInstance;
    private static IRepasMemDao repasMemDaoInstance;

    private DaoFactory() {
    }


    public static IPatientMemDao fabriquerPatientDao() {
        if (patientMemDaoInstance == null) {
            patientMemDaoInstance = new PatientMemDaoImpl();
        }
        return patientMemDaoInstance;
    }

    public static IRepasMemDao fabriquerRepasDao() {
        if (repasMemDaoInstance == null) {
            repasMemDaoInstance = new RepasMemDaoImpl();
        }
        return repasMemDaoInstance;
    }
}
