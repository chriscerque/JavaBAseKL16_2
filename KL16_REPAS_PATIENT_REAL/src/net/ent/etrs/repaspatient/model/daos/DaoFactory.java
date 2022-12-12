package net.ent.etrs.repaspatient.model.daos;

public class DaoFactory {

    private static RepasMemDao repasMemDaoInstance;
    private static PatientMemDao patientMemDaoInstance;

    public static IPatientMemDao fabriquerPatientDao(){
            return new PatientMemDao();
    }

    public static IRepasMemsDao fabriquerRepasDao(){
        return new RepasMemDao();
    }






}
