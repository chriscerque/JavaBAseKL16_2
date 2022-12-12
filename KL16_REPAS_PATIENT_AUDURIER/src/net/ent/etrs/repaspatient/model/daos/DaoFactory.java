package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.Objects;

public final class DaoFactory {
    private static DaoInter<Patient, String> patientMemDaoInstance;
    private static DaoInter<Repas, String> repasMemDaoInstance;

    private DaoFactory() {
    }

    public static DaoInter<Patient, String> fabriquerPatientDao() {
        return DaoFactory.getPatientInstance();
    }

    private static DaoInter<Patient, String> getPatientInstance() {
        return Objects.isNull(DaoFactory.patientMemDaoInstance) ? patientMemDaoInstance = new PatientMemDao() : patientMemDaoInstance;
    }

    public static DaoInter<Repas, String> fabriquerRepasDao() {
        return DaoFactory.getRepasInstance();
    }

    private static DaoInter<Repas, String> getRepasInstance() {
        return Objects.isNull(DaoFactory.repasMemDaoInstance) ? repasMemDaoInstance = new RepasMemDao() : repasMemDaoInstance;
    }
}
