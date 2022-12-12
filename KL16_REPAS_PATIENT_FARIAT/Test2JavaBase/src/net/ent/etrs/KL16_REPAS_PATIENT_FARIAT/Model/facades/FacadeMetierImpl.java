package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.facades;

import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.DaoFactory;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.PatientMemDao;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.RepasMemDao;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.excetions.PatientMemDaoException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.Patient;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.Repas;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.facades.exceptions.BusinessException;

import java.util.Collections;
import java.util.List;

public class FacadeMetierImpl implements FacadeMetier {
    private RepasMemDao repasDao = DaoFactory.fabriqueDaoRepas();
    private PatientMemDao patientDao = DaoFactory.fabriqueDaoPatient();

    @Override
    public List<Patient> listerPatients() {
        return Collections.unmodifiableList(patientDao.readAll());
    }

    @Override
    public List<Repas> listerRepas() {
        return Collections.unmodifiableList(repasDao.readAll());
    }

    @Override
    public void sauvegarderPatient(final Patient patient) throws BusinessException, PatientMemDaoException {
        try {
            patientDao.create(patient);
        } catch (PatientMemDaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }


    }

    @Override
    public void supprimerPatient(final Patient patient) throws BusinessException, PatientMemDaoException {
        try {
            patientDao.delete(patient);
        } catch (PatientMemDaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    @Override
    public void mettreAJourPatient(final Patient patient) throws BusinessException, PatientMemDaoException {
        try {
            patientDao.update(patient);
        } catch (PatientMemDaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    @Override
    public void init() throws BusinessException {

    }

    @Override
    public Patient recupererPatientById(final String patientId) throws BusinessException, PatientMemDaoException {
        try {
            return patientDao.read(patientId);
        } catch (PatientMemDaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
