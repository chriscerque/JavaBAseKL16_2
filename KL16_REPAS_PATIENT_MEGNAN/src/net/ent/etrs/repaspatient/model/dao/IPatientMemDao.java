package net.ent.etrs.repaspatient.model.dao;

import net.ent.etrs.repaspatient.model.dao.exception.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.List;

public interface IPatientMemDao {
    //AUTRES METHODES
    void create(Patient patient) throws DaoException;

    void delete(Patient patient) throws DaoException;

    void deleteByKey(String numSecu) throws DaoException;

    boolean exist(Patient patient);

    Patient read(String id) throws DaoException;

    List<Patient> readAll();

    void update(Patient patient) throws DaoException;
}
