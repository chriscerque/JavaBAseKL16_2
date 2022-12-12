package net.ent.etrs.repaspatient.model.daos;
import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.List;

public interface DaoPatient {
    Patient save(Patient patient) throws DaoException;

    Patient read(String numSecu) throws DaoException;

    List<Patient> readAll();

    Patient update(Patient patient) throws DaoException;

    void delete(Patient patient) throws DaoException;

    void deleteByKey(String numSecu) throws DaoException;

    boolean exist(Patient patient) throws DaoException;

    void init() throws DaoException;
}
