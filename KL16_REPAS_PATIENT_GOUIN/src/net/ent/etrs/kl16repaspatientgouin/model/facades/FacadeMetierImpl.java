package net.ent.etrs.kl16repaspatientgouin.model.facades;

import net.ent.etrs.kl16repaspatientgouin.model.daos.DaoFactory;
import net.ent.etrs.kl16repaspatientgouin.model.daos.IPatientMemDao;
import net.ent.etrs.kl16repaspatientgouin.model.daos.IRepasMemDao;
import net.ent.etrs.kl16repaspatientgouin.model.daos.exceptions.DaoException;
import net.ent.etrs.kl16repaspatientgouin.model.entities.Patient;
import net.ent.etrs.kl16repaspatientgouin.model.entities.Repas;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.ConstantesMetier;
import net.ent.etrs.kl16repaspatientgouin.model.facades.exceptions.BusinessException;

import java.util.Collections;
import java.util.List;

public class FacadeMetierImpl implements FacadeMetier {

    private IPatientMemDao patientDao;

    private IRepasMemDao repasDao;

    protected FacadeMetierImpl() throws BusinessException {
        this.init();
    }

    @Override
    public List<Patient> listerPatients() {
        return Collections.unmodifiableList(this.patientDao.readAll());
    }

    @Override
    public List<Repas> listerRepas() {
        return Collections.unmodifiableList(this.repasDao.readAll());
    }

    @Override
    public void sauvegarderPatient(Patient patient) throws BusinessException {
        try {
            this.patientDao.create(patient);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_CREATION_EXCEPTION, e);
        }
    }

    @Override
    public void supprimerPatient(Patient patient) throws BusinessException {
        try {
            this.patientDao.delete(patient);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_SUPPRESSION_EXCEPTION,e);
        }
    }

    @Override
    public void mettreAJourPatient(Patient patient) throws BusinessException {
        try {
            this.patientDao.update(patient);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_MISE_A_JOUR_EXCEPTION, e);
        }

    }

    @Override
    public void init() throws BusinessException {
        try {
            patientDao = DaoFactory.fabriquerPatientDao();
            repasDao = DaoFactory.fabriquerRepasDao();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage());
        }


    }

    @Override
    public Patient recupererPatientById(String patientId) throws BusinessException {
        try {
            return this.patientDao.read(patientId);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
