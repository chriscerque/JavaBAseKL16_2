package net.ent.etrs.repaspatient.model.facades;

import net.ent.etrs.repaspatient.model.daos.DaoFactory;
import net.ent.etrs.repaspatient.model.daos.IPatientMemDao;
import net.ent.etrs.repaspatient.model.daos.IRepasMemDao;
import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;

import java.util.Collections;
import java.util.List;

public class FacadeMetierImpl implements FacadeMetier {

    private final IRepasMemDao repasDao;

    private final IPatientMemDao patientDao;

    public FacadeMetierImpl() {
        this.repasDao = DaoFactory.fabriqueRepasDao();
        this.patientDao = DaoFactory.fabriquePatientDao();
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
    public Patient sauvegarderPatient(final Patient patient) throws BusinessException {
        if (patient == null) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_CREATION_EXCEPTION);
        }
        try {
            return patientDao.create(patient);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_CREATION_EXCEPTION);
        }


    }

    @Override
    public void SupprimerPatient(final String numSecu) throws BusinessException {
        try {
            this.patientDao.delete(numSecu);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_SUPPRESSION_EXCEPTION);
        }
    }

    @Override
    public Patient mettreAJourPatient(final Patient patient) throws BusinessException {
        if (patient == null) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_MISE_A_JOUR_EXCEPTION);
        }
        try {
            return patientDao.update(patient);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_MISE_A_JOUR_EXCEPTION);
        }
    }

    @Override
    public void init() throws BusinessException {

    }

    @Override
    public List<Patient> recupererPatientById(final String patientId) {
        return null;
    }
}
