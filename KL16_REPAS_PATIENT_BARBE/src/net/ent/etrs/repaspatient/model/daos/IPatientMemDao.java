package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.Set;


public interface IPatientMemDao {

    Patient create(final Patient patient) throws DaoException;


    Patient update(final Patient patient) throws DaoException;


    Patient read(final String libelle) throws DaoException;


    void delete(final String libelle) throws DaoException;


    Set<Patient> readAll();


    boolean exist(final Patient patient) throws DaoException;
}