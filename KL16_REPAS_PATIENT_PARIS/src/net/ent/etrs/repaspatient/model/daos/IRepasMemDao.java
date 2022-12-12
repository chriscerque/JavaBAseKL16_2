package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

public interface IRepasMemDao {


    public Repas read(String id) throws DaoException;

    public void delete(String id) throws DaoException;

    public List<Repas> readAll();

    public Repas update(Repas repas) throws DaoException;

    boolean exist(Repas repas) throws DaoException;

    void delete(Repas repas) throws DaoException;

    void deleteByKey(String id) throws DaoException;

    public Repas create(Repas repas) throws DaoException;


}
