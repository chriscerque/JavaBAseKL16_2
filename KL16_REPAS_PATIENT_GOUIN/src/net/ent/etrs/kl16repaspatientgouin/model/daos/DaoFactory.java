package net.ent.etrs.kl16repaspatientgouin.model.daos;

import net.ent.etrs.kl16repaspatientgouin.model.daos.exceptions.DaoException;
import net.ent.etrs.kl16repaspatientgouin.model.facades.exceptions.BusinessException;

public class DaoFactory {

    private static RepasMemDao repasMemDaoInstance;

    private static PatientMemDao patientMemDaoInstance;

    public static RepasMemDao fabriquerRepasDao() throws DaoException {
        if (repasMemDaoInstance == null){
            repasMemDaoInstance = new RepasMemDao();
        }
        return repasMemDaoInstance;
    }

    public static PatientMemDao fabriquerPatientDao() throws DaoException{
        if (patientMemDaoInstance == null){
            patientMemDaoInstance = new PatientMemDao();
        }
        return patientMemDaoInstance;
    }
}
