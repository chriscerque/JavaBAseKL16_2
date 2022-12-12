package net.ent.etrs.repaspatient.model.daos;



public final class DaoFactory {

    private static final RepasMemDao repasMemDaoInstance = new RepasMemDao();
    private static final PatientMemDao patientMemDaoInstance = new PatientMemDao();

    public static IPatientMemDao fabriquerPatientDao() {
        return new PatientMemDao();
    }
    public static IRepasMemDao fabriquerRepasDao() { return new RepasMemDao(); }


    private DaoFactory() {}
}
