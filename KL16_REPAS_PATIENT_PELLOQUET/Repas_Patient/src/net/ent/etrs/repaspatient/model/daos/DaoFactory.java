package net.ent.etrs.repaspatient.model.daos;

public class DaoFactory {

    private static RepasMemDao repasMemDaoInstance;
    private static PatientMemDao patientMemDaoInstance;

    //Contructeur

    public DaoFactory() {

    }

    public static IPatientMemDao fabriquerPatientDao() {
        return new IPatientMemDao();
    }

    public static IRepasMemDao fabriquerRepasDao() {
        return new IRepasMemDao();
    }
}
