package net.ent.etrs.repaspatient.model.daos;

/**
 * The type Dao factory.
 */
public final class DaoFactory {



    private DaoFactory() {
    }



    private static RepasMemDao repasMemDaoInstance;

    private static PatientMemDao patientMemDaoInstance;


    /**
     * Fabriquer patient dao patient mem dao.
     *
     * @return the patient mem dao
     */
    public static IPatientMemDao fabriquerPatientDao() {

        return null;
    }

    /**
     * Fabriquer repas dao repas mem dao.
     *
     * @return the repas mem dao
     */
    public static IRepasMemDao fabriquerRepasDao() {

        return null;
    }



}
