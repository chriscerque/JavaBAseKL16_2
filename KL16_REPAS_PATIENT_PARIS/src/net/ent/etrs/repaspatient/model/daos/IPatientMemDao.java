package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;

import java.util.List;

public interface IPatientMemDao {


    Patient create(Patient patient) throws DaoException;

    boolean exist(Patient patient) throws DaoException;

    public Patient read(String id) throws DaoException;

    public List<Patient> readAll();

    public Patient update(Patient patient) throws DaoException;

    void deleteByKey(String id) throws DaoException;

    void init() throws BusinessException;

    void delete(Patient patient) throws DaoException;
}
