package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PatientMemDao implements IPatientMemDao{

    private List<Patient> persistence = new ArrayList<>();

    @Override
    public List<Patient> readAll() {
        return Collections.unmodifiableList(this.persistence);
    }

    @Override
    public Patient create(Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT);
        }
        if (exist(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
        }
        this.persistence.add(patient);
        return this.read(patient.getId());
    }

    @Override
    public void delete(Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_NULL);
        }
        if (!exist(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
        }
        this.persistence.remove(patient);
    }

    @Override
    public void deleteByKey(String id) throws DaoException {
        try {
            Patient p = this.read(id);
            if (!exist(p)) {
                throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT);
            }
            this.persistence.remove(p);
        }catch (DaoException e){
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_NULL);
        }
    }

    @Override
    public boolean exist(Patient patient) throws DaoException {
        try {
            return this.persistence.contains(patient);
        } catch (Exception e) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL, e);
        }
    }

    @Override
    public Patient read(String id) throws DaoException {
        Patient p = null;
        for (Patient patient : this.persistence) {
            if(patient.getId().equals(id)) {
                p = patient;
            }
        }
        return p;
    }

    @Override
    public Patient update(Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_NULL);
        }
        if (!exist(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT);
        }
        try {
            this.persistence.remove(patient);
            this.persistence.add(patient);
        }catch (Exception e) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_INEXISTANT);
        }
        return this.read(patient.getId());
    }

    protected PatientMemDao() {}
}
