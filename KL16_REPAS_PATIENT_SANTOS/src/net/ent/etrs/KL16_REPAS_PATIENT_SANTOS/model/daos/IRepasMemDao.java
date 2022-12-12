package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.daos;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.daos.exceptions.DaoException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.Repas;

import java.util.List;
import java.util.UUID;

public interface IRepasMemDao {


    public  Repas create(Repas repas) throws DaoException;


    public List<Repas> readAll();

    public Repas update (Repas repas) throws DaoException;

    public void delete (Repas repas) throws DaoException;




    public void deleteByKey (UUID idRepas) throws DaoException;



    public boolean exist (Repas patient) throws DaoException;



    public void read (String patient) throws DaoException;

    public void init () throws DaoException;
}
