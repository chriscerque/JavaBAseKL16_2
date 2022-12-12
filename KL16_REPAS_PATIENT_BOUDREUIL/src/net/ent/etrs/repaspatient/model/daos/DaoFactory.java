package net.ent.etrs.repaspatient.model.daos;
import java.util.Objects;

public final class DaoFactory {

    private static DaoPatientImpl daoPatient;
    private static DaoRepas daoRepas;

    private DaoFactory() {
    }

    public static DaoPatientImpl fabriquerDaoPatient() {
        return DaoFactory.getInstancePatient();
    }

    public static DaoRepas fabriquerDaoRepas() {
        return DaoFactory.getInstanceRepas();
    }

    private static DaoPatientImpl getInstancePatient() {
        return Objects.isNull(DaoFactory.daoPatient) ? daoPatient = new DaoPatientImpl() : daoPatient;
    }

    private static DaoRepas getInstanceRepas() {
        return Objects.isNull(DaoFactory.daoRepas) ? daoRepas = new DaoRepasImpl() : daoRepas;
    }
}
