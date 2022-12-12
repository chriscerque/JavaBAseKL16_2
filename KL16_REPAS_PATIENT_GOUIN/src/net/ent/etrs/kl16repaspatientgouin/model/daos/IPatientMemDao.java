package net.ent.etrs.kl16repaspatientgouin.model.daos;

import net.ent.etrs.kl16repaspatientgouin.model.daos.exceptions.DaoException;
import net.ent.etrs.kl16repaspatientgouin.model.entities.Patient;
import net.ent.etrs.kl16repaspatientgouin.model.facades.exceptions.BusinessException;

import java.util.List;

public interface IPatientMemDao {

    Patient create (final Patient patient) throws DaoException;

    Patient read(final String id) throws DaoException;

    Patient update (final Patient patient) throws DaoException;

    void delete(final Patient patient) throws DaoException;

    void deletebyKey(final String id) throws DaoException;

    List<Patient> readAll();

    void init() throws DaoException;

    boolean exist(final Patient patient);
}
