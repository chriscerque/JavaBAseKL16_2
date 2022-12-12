package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PatientMemDao implements IPatientMemDao{

    protected PatientMemDao() {
    }

    private List<Patient> persistence = new ArrayList<>();

    @Override
    public Patient create(final Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        if (exist(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
        }
        this.persistence.add(patient);
        return this.read(patient.getNumSecu());
    }

    @Override
    public Patient update(final Patient Patient) throws DaoException {
        try {
            this.persistence.remove(Patient);
            this.persistence.add(Patient);
        }catch (Exception e) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_INEXISTANT);
        }
        return this.read(Patient.getNumSecu());
    }

    @Override
    public Patient read(final String numSecu) {
        Patient p = null;
        for (Patient patient : this.persistence) {
            if(patient.getNumSecu().equals(numSecu)) {
                p = patient;
            }
        }
        return p;
    }

    @Override
    public void delete(final Patient patient) throws DaoException {
        Patient b = this.read(String.valueOf(patient));
        if (!exist(b)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
        }
        this.persistence.remove(b);

    }

    @Override
    public List<Patient> readAll() {
        return Collections.unmodifiableList(this.persistence);
    }

    @Override
    public boolean exist(final Patient patient) throws DaoException {
        try {
            return this.persistence.contains(patient);
        } catch (Exception e) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT, e);
        }
    }
}
