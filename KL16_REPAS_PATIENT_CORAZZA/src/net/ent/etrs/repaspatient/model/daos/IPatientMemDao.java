package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.List;

public interface IPatientMemDao {

    List<Patient> readAll();

    Patient create(Patient patient) throws DaoException;

    void delete(Patient patient) throws DaoException;

    void deleteByKey(String id) throws DaoException;

    boolean exist(Patient patient) throws DaoException;

    Patient read(String id) throws DaoException;

    default void init() {
        throw new UnsupportedOperationException();
    }

    Patient update(Patient patient) throws DaoException;






}
