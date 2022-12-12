package net.ent.etrs.repaspatient.model.daos;

public final class DaoFactory {

    private DaoFactory() {
    }

    private RepasMemDao repasMemeDaoInstance;

    private PatientMemDao patientMemDaoInstance;

    public static IRepasMemDao fabriquerRepasDao(){
        return new RepasMemDao();
    }

    public static IPatientMemDao fabriquerPatientDao(){return new PatientMemDao();}
}
