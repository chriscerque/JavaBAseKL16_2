package net.ent.etrs.test.model.daos;

import net.ent.etrs.test.model.daos.exception.DaoException;
import net.ent.etrs.test.model.entities.Repas;

import java.util.List;

public interface IRepasMemDao {

    /**
     * Renvoie la liste de tous les repas.
     * @return
     * @throws DaoException
     */
    public List<Repas> readAll() throws DaoException;

    /**
     * Crée un repas dans la DAO.
     * @param repas
     * @return
     * @throws DaoException
     */
    public Repas create (final Repas repas) throws DaoException;

    /**
     * Supprime un repas en prenant un repas.
     * @param repas
     * @throws DaoException
     */
    public void delete (final Repas repas) throws DaoException;

    /**
     * Supprime un repas en prenant son id.
     * @param id
     * @throws DaoException
     */
    public void deleteByKey(final String id) throws DaoException;

    /**
     * Renvoie si un repas existe ou non.
     * @param repas
     * @return
     * @throws DaoException
     */
    public boolean exist(final Repas repas) throws DaoException;

    /**
     * Renvoie un repas en fonction de son id.
     * @param id
     * @return
     * @throws DaoException
     */
    public Repas read(final String id) throws DaoException;

    public void init();

    /**
     * Met à jour un repas dans la DAO
     * @param repas
     * @return
     * @throws DaoException
     */
    public Repas update (final Repas repas) throws DaoException;
}
