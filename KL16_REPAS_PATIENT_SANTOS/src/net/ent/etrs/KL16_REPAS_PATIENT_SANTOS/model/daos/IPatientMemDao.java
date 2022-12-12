package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.daos;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.daos.exceptions.DaoException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.Patient;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.RepasException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.facades.exceptions.BusinessException;

import java.util.List;
import java.util.UUID;

public interface IPatientMemDao {
    public Patient create (Patient patient) throws DaoException;


    public List<Patient> readAll();

    public Patient update (Patient patient) throws DaoException;

    public void delete (Patient patient) throws DaoException;






    void deleteByKey(UUID id) throws DaoException;

    public boolean exist (Patient patient) throws DaoException;



    public Patient read (String patient) throws DaoException;

    public void init () throws DaoException, BusinessException, RepasException;
















}


