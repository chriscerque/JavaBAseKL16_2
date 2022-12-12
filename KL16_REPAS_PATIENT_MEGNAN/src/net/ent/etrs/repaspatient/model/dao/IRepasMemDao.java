package net.ent.etrs.repaspatient.model.dao;

import net.ent.etrs.repaspatient.model.dao.exception.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

public interface IRepasMemDao {
    //AUTRES METHODES
    void create(Repas repas) throws DaoException;

    void delete(Repas repas) throws DaoException;

    void deleteByKey(String id) throws DaoException;

    boolean exist(Repas repas);

    Repas read(Repas repas);

    List<Repas> readAll();

    void update(Repas repas) throws DaoException;
}
