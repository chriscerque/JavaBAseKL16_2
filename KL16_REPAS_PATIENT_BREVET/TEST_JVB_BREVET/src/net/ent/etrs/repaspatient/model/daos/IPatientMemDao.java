package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.List;

/**
 * Interface de la Dao des patients.
 */
public interface IPatientMemDao {
    void create(final Patient patient) throws DaoException;

    Patient read(final String id) throws DaoException;

    Patient update(final Patient patient) throws DaoException;

    void delete(final Patient patient) throws DaoException;

    boolean exist(final Patient patient) throws DaoException;

    List<Patient> readall();

    void deleteByKey(final String id) throws DaoException;

    void init();
}
