package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

/**
 * Interface de la Dao des repas.
 */
public interface IRepasMemDao {
    void create(final Repas repas) throws DaoException;

    Repas read(final String id) throws DaoException;

    Repas update(final Repas repas) throws DaoException;

    void delete(final Repas repas) throws DaoException;

    boolean exist(final Repas repas) throws DaoException;

    List<Repas> readall();

    void init();

    void deleteByKey(final String id) throws DaoException;
}

