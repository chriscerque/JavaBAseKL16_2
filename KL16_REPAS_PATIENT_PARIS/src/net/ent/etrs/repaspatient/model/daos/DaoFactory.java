package net.ent.etrs.repaspatient.model.daos;

import java.util.List;

public final class DaoFactory {

    private DaoFactory(){

    }

    private static RepasMemDao repasMemDaoInstance;

    private static PatientMemDao patientMemDaoInstance;

    public static IPatientMemDao fabriquerPatientDao(){
        return new PatientMemDao();
    }

    public static IRepasMemDao fabriquerRepasDao(){
        return new RepasMemDao();
    }

}
