package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.List;

public interface IPatientMemDao {

    Patient create(final Patient patient) throws DaoException;
    Patient update(final Patient Patient) throws DaoException;

    Patient read(final String numSecu);
    void delete(final Patient patient) throws DaoException;
    List<Patient> readAll();


    boolean exist(Patient patient) throws DaoException;
}
