package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.List;

public interface IPatientMemDao {

    Patient create(Patient patient) throws DaoException;

    Patient read(String id) throws DaoException;

    void delete(Patient patient) throws DaoException;

    void deleteByKey(String id) throws DaoException;

    Patient update(Patient patient) throws DaoException;

    List<Patient> readAll();

    boolean exist(Patient patient) throws DaoException;

    default void init() {
        throw new UnsupportedOperationException();
    }
}
