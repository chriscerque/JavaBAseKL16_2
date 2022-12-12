package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatientMemDao implements IPatientMemDao{

    private List<Patient> persistence = new ArrayList<>();

    protected PatientMemDao() {
    }

    @Override
    public Patient create(Patient patient) throws DaoException {
        if (patient == null){
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        if (persistence.contains(patient)){
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
        }
        persistence.add(patient);
        return patient;
    }

    @Override
    public Patient read(String id) throws DaoException {
        if (id == null || id.isEmpty() || id.isBlank()){
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        List<Patient> tempPatient = new ArrayList<>(persistence);
        for (Patient p : tempPatient){
            if (p.getId() == id){
                return p;
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
    }

    @Override
    public List<Patient> readAll() {
        return Collections.unmodifiableList(persistence);
    }

    @Override
    public Patient update(Patient patient) throws DaoException {
        if (patient == null){
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        if (!persistence.contains(patient)){
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        Patient p = persistence.set(persistence.indexOf(patient), patient);
        return p;
    }

    @Override
    public void delete(Patient patient) throws DaoException {
        if (patient == null){
            throw new DaoException(ConstantesMetier.DAO_PATIENT_INEXISTANT);
        }
        if (!persistence.contains(patient)){
            throw new DaoException(ConstantesMetier.DAO_PATIENT_INEXISTANT);
        }

        persistence.remove(patient);
    }

    @Override
    public void deleteByKey(String key) throws DaoException {
        if (key == null){
            throw new DaoException(ConstantesMetier.DAO_PATIENT_INEXISTANT);
        }
        List<Patient> tempPatient = new ArrayList<>(persistence);
        for (Patient p : tempPatient){
            if (key == p.getId()){
                persistence.remove(p);
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
    }

    @Override
    public void init() {

    }
}
