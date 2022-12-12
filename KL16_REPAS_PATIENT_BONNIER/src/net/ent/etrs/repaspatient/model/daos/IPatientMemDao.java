package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.List;

public interface IPatientMemDao {

    Patient create(final Patient patient) throws DaoException;

    List<Patient> readAll();

    Patient update(final Patient patient) throws DaoException;

    void delete(final String id) throws DaoException;

    void deleteByKey(final String numSecu) throws DaoException;

    Patient read(final String id);

}
