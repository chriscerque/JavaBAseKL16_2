package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PatientMemDao implements IPatientMemDao {
    private final List<Patient> persistance = new ArrayList<>();


    @Override
    public Patient create(Patient patient) throws DaoException {
        if (!Objects.isNull(patient) || persistance.add(patient)) {
            return patient;
        } else {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
        }
    }

    @Override
    public List<Patient> readAll() {
        return Collections.unmodifiableList(this.persistance);
    }

    @Override
    public Patient update(Patient patient) throws DaoException {
        if (!Objects.isNull(patient) || persistance.contains(patient)) {
            persistance.remove(patient);
            persistance.add(patient);
            return patient;
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_INEXISTANT);
    }

    @Override
    public void delete(String id) throws DaoException {
        List<Patient> patientLstTemporaire = new ArrayList<>(persistance);
        if (!Objects.isNull(id)) {
            for (Patient r : patientLstTemporaire) {
                if (r.getId().equals(id)) {
                    persistance.remove(r);
                }
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT);
    }

    @Override
    public void deleteByKey(String numSecu) throws DaoException {
        List<Patient> repasLstTemporaire = new ArrayList<>(persistance);
        if (!Objects.isNull(numSecu)) {
            for (Patient r : repasLstTemporaire) {
                if (r.getNumSecu().equals(numSecu)) {
                    persistance.remove(r);
                }
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
    }

    @Override
    public Patient read(String numSecu) {
        Patient p = null;
        for (Patient patient : this.persistance) {
            if (patient.getNumSecu().equals(numSecu)) {
                p = patient;
            }
        }
        return p;

    }
}

