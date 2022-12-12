package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao;

import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.excetions.PatientMemDaoException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.references.ConstantesDao;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.Patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PatientMemDaoImpl implements PatientMemDao {

    private List<Patient> persistence = new ArrayList<>();


    @Override
    public Patient create(final Patient patient) throws PatientMemDaoException {
        if (Objects.isNull(patient)) {
            throw new PatientMemDaoException(ConstantesDao.DAO_PATIENT_CREATION_EXCEPTION);
        }
        if (exist(patient)) {
            throw new PatientMemDaoException(ConstantesDao.DAO_PATIENT_EXIST_EXCEPTION);
        }
        this.persistence.add(patient);
        return patient;

    }

    @Override
    public List<Patient> readAll() {
        return Collections.unmodifiableList(persistence);


    }

    @Override
    public Patient update(final Patient patient) throws PatientMemDaoException {
        if (!Objects.isNull(patient) || persistence.contains(patient)) {
            persistence.remove(patient);
            persistence.add(patient);
            return patient;
        }
        throw new PatientMemDaoException(ConstantesDao.DAO_PATIENT_IMPOSSIBLE_UPDATE);

    }

    @Override
    public void deleteByKey(final String numSecu) throws PatientMemDaoException {
        if (Objects.isNull(numSecu)) {
            throw new PatientMemDaoException(ConstantesDao.PATIENT_DAO_IDENTIFICATION_NULL);
        }

        List<Patient> listeRafaleTemporaire = new ArrayList<>(persistence);

        for (Patient r : listeRafaleTemporaire) {
            if (r.getNumSecu().equals(numSecu)) {
                persistence.remove(r);
            }
        }

    }


    @Override
    public Patient read(String id) throws PatientMemDaoException {
        if (Objects.isNull(id)) {
            throw new PatientMemDaoException(ConstantesDao.PATIENT_DAO_IDENTIFICATION_NULL);
        }
        List<Patient> listePatientTemporaire = new ArrayList<>(persistence);

        for (Patient patient : listePatientTemporaire) {
            if (id.equals(patient.getId())) {
                return patient;
            }
        }
        throw new PatientMemDaoException(ConstantesDao.DAO_PATIENT_EXIST_PAS_EXCEPTION);

    }


    @Override
    public void delete(final Patient patient) throws PatientMemDaoException {
        if (Objects.isNull(patient)) {
            throw new PatientMemDaoException(ConstantesDao.PATIENT_DAO_IDENTIFICATION_NULL);
        }

        List<Patient> listePatientTemporaire = new ArrayList<>(persistence);

        for (Patient p : listePatientTemporaire) {
            if (p.equals(patient)) {
                persistence.remove(p);
            }
        }

    }


    @Override
    public boolean exist(final Patient patient) throws PatientMemDaoException {
        return false;
    }
}
