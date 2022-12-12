package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.daos;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.daos.exceptions.DaoException;

public class DaoFactory {
    private DaoFactory() {
    }

    public static IRepasMemDao fabriqueDaoMetierRepas(){
        return new RepasMemdao();
    }

    public static IPatientMemDao fabriqueDaoMetierPatient(){
        return new PatientMemDao();
    }
}
