package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;


import java.util.List;

public interface IPatientMemDao {

    public Patient create (Patient patient) throws DaoException;

    public Patient read (String id) throws DaoException;

    public List<Patient> readAll();

    public Patient update (Patient patient) throws DaoException;

    public void delete (Patient patient) throws DaoException;

    public void deleteByKey(String key) throws DaoException;

    public void init();


}
