package net.ent.etrs.repaspatient.model.daos;


//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

    private static RepasMemDao repasMemDaoInstance;
    private static PatientMemDao patientMemDaoInstance;

    private DaoFactory() {
    }

    /**
     * Fabrique une nouvlee dao de contact.
     *
     * @return une dao de contact
     */
    public static IPatientMemDao fabriquerPatientDao() {
        if (patientMemDaoInstance == null) {
            patientMemDaoInstance = new PatientMemDao();
        }
        return patientMemDaoInstance;
    }

    public static IRepasMemDao fabriquerRepasDao() {
        if (repasMemDaoInstance == null) {
            repasMemDaoInstance = new RepasMemDao();
        }
        return repasMemDaoInstance;
    }
}
