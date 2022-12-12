package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

public interface IRepasMemDao {

    public Repas create (Repas repas) throws DaoException;

    public Repas read (String id) throws DaoException;

    public List<Repas> readAll();

    public Repas update (Repas repas) throws DaoException;

    public void delete (Repas repas) throws DaoException;

    public void deleteByKey(String key) throws DaoException;

    public void init();


}
