package net.ent.etrs.repaspatient.model.daos;

public class DaoFactory {

    private DaoFactory(){
    }

    public static IRepasMemDao fabriquerRepasDao(){
        return new RepasMemDao();
    }

    public static IPatientMemDao fabriquerPatientDao(){
        return new PatientMemDao();
    }

}
