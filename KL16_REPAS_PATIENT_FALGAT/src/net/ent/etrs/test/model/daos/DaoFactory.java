package net.ent.etrs.test.model.daos;

public final class DaoFactory {

    private final static RepasMemDao repasMemDaoInstance = new RepasMemDao();

    private final static PatientMemDao patientMemDaoInstance = new PatientMemDao();

    private DaoFactory() {}

    public static IRepasMemDao fabriquerRepasDao(){
        return repasMemDaoInstance;
    }

    public static IPatientMemDao fabriquerPatientDao(){
        return patientMemDaoInstance;
    }

}
