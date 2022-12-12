package net.ent.etrs.test.model.daos;

import net.ent.etrs.test.model.daos.exception.DaoException;
import net.ent.etrs.test.model.entities.Patient;

import java.util.List;
import java.util.UUID;

public interface IPatientMemDao {

    /**
     * Renvoie la liste des patients triés en fonction du nom puis du prénom.
     * @return
     * @throws DaoException
     */
    public List<Patient> readAll() throws DaoException;

    /**
     * Crée un patient dans la DAO.
     * @param patient
     * @return
     * @throws DaoException
     */
    public Patient create (final Patient patient) throws DaoException;

    /**
     * Supprime un patient an prenant un patient.
     * @param patient
     * @throws DaoException
     */
    public void delete (final Patient patient) throws DaoException;

    /**
     * Supprime un patient en prenant son id.
     * @param id
     * @throws DaoException
     */
    public void deleteByKey(final String id) throws DaoException;

    /**
     * Renvoie si un patient existe ou non.
     * @param patient
     * @return
     * @throws DaoException
     */
    public boolean exist(final Patient patient) throws DaoException;

    /**
     * Renvoi un patient en fonction de son id.
     * @param id
     * @return
     * @throws DaoException
     */
    public Patient read(final String id) throws DaoException;

    public void init();

    /**
     * Met à jour un patient dans la dao.
     * @param patient
     * @return
     * @throws DaoException
     */
    public Patient update (final Patient patient) throws DaoException;





}
