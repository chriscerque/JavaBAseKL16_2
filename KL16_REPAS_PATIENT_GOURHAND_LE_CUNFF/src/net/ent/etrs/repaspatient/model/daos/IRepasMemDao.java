package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

/**
 * The interface Repas mem dao.
 */
public interface IRepasMemDao {
    /**
     * Read repas.
     *
     * @param id the id
     * @return the repas
     */
    Repas read(String id) throws DaoException;

    /**
     * Delete by key.
     *
     * @param id the id
     */
    void deleteByKey(String id) throws DaoException;

    /**
     * Delete.
     *
     * @param repas the repas
     */
    void delete(Repas repas) throws DaoException;

    /**
     * Read all list.
     *
     * @return the list
     */
    List<Repas> readAll();

    /**
     * Update repas.
     *
     * @param repas the repas
     * @return the repas
     */
    Repas update(Repas repas) throws DaoException;

    /**
     * Exist boolean.
     *
     * @param repas the repas
     * @return the boolean
     */
    boolean exist(Repas repas);

    /**
     * Init.
     */
    void init();

    /**
     * Create repas.
     *
     * @param repas the repas
     * @return the repas
     */
    Repas create(Repas repas) throws DaoException;
}
