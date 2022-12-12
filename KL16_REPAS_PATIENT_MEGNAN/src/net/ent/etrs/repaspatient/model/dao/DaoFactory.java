package net.ent.etrs.repaspatient.model.dao;

public final class DaoFactory {

    private static RepasMemDao repasMemDaoInstance;
    private static PatientMemDao patientMemDaoInstance;

    private DaoFactory() {
    }

    public static IPatientMemDao fabriquerDaoPatient(){
        return new PatientMemDao();
        //return new patientMemDaoInstance;
    }

    public static IRepasMemDao fabriquerDaoRepas(){
        return new RepasMemDao();
        //return new repasMemDaoInstance;
    }
}
