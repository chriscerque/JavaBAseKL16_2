package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao;

public final class DaoFactory {
    private DaoFactory() {
    }

    public static PatientMemDao fabriqueDaoPatient() {
        return new PatientMemDaoImpl();
    }

    public static RepasMemDao fabriqueDaoRepas() {
        return new RepasMemDaoImpl();
    }

}
