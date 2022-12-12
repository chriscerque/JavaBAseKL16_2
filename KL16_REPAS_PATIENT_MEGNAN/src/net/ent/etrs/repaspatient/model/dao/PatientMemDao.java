package net.ent.etrs.repaspatient.model.dao;

import net.ent.etrs.repaspatient.model.dao.exception.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientMemDao implements IPatientMemDao{

    //ATTRIBUTS
    List<Patient> persistance = new ArrayList<>();

    //CONSTRUCTEUR
    protected PatientMemDao() {
    }

    //AUTRES METHODES
    @Override
    public void create(Patient patient) throws DaoException {
        for(Patient p : persistance) {
            if (patient.getNumSecu().equals(patient.getNumSecu())){
                throw new DaoException(ConstantesDao.DAO_PATIENT_EXIST_EXCEPTION);
            }
        }
        this.persistance.add(patient);
    }

    @Override
    public void delete(Patient patient) throws DaoException {
        for (Patient p:persistance) {
            if(p.getNumSecu().equals(p.getNumSecu())){
                throw new DaoException(ConstantesDao.DAO_PATIENT_EXIST_PAS_EXCEPTION);
            }
        }
        this.persistance.remove(patient);
    }

    @Override
    public void deleteByKey(String id) throws DaoException {
        for (Patient p:persistance) {
            if(p.getId().equals(id)){
                throw new DaoException(ConstantesDao.DAO_PATIENT_EXIST_PAS_EXCEPTION);
            }
        }
        this.persistance.remove(read(id));
    }

    @Override
    public boolean exist(final Patient patient){
        for (Patient p:persistance) {
            patient.getNumSecu().equals(patient.getNumSecu());
            return true;
        }
        return false;
    }

    @Override
    public Patient read(final String id) throws DaoException {
        for (Patient p : persistance) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new DaoException(ConstantesDao.DAO_PATIENT_EXIST_PAS_EXCEPTION);
    }

    @Override
    public List<Patient> readAll(){
        return this.persistance;
    }

    @Override
    public void update(final Patient patient) throws DaoException {
        if(patient == null){
            throw new DaoException(ConstantesDao.DAO_PATIENT_EXIST_NULL_EXCEPTION);
        }
        int indexClient = this.persistance.indexOf(patient);
        this.persistance.set(indexClient, patient);
    }
}
