package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.*;

public class PatientMemDao implements IPatientMemDao{

    private List<Patient> persistance = new ArrayList<>();

    protected PatientMemDao() {
    }


    @Override
    public List<Patient> readAll() {
        return Collections.unmodifiableList(persistance);
    }

    @Override
    public Patient create(Patient p) throws DaoException {
        if (!Objects.isNull(p)){
            persistance.add(p);
            return persistance.get(persistance.indexOf(p));
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
    }

    @Override
    public void delete(String numSecu) {
        if (!Objects.isNull(numSecu)) {
            for (Patient patient : persistance) {
                if (patient.getNumSecu().equals(numSecu)) {
                    persistance.remove(patient);
                }
            }
        }
    }

    @Override
    public void deleteByKey(UUID id) {
    }

    @Override
    public boolean exists(Patient p) {
        if(!Objects.isNull(p) && persistance.contains(p)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Patient read(String numSecu) {
        Patient p = null;
        for (Patient patient : persistance) {
            if (patient.getNumSecu().equals(numSecu)) {
                p = patient;
            }
        }

        return p;
    }


    @Override
    public void init() {

    }

    @Override
    public Patient update(Patient p) throws DaoException {
        if(!Objects.isNull(p) && persistance.contains(p)){
            persistance.set(persistance.indexOf(p), p);
            return persistance.get(persistance.indexOf(p));
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT);
    }
}
