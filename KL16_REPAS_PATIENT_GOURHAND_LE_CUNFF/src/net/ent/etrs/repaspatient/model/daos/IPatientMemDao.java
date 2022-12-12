package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.List;

/**
 * The interface Patient mem dao.
 */
public interface IPatientMemDao {
    /**
     * Read all list.
     *
     * @return the list
     */
    List<Patient> readAll();

    /**
     * Create patient.
     *
     * @param patient the patient
     * @return the patient
     */
    Patient create(Patient patient) throws DaoException;

    /**
     * Delete.
     *
     * @param patient the patient
     */
    void delete(Patient patient) throws DaoException;

    /**
     * Delete by key.
     *
     * @param id the id
     */
    void deleteByKey(String id) throws DaoException;

    /**
     * Exist boolean.
     *
     * @param patient the patient
     * @return the boolean
     */
    boolean exist(Patient patient);

    /**
     * Read patient.
     *
     * @param id the id
     * @return the patient
     */
    Patient read(String id) throws DaoException;

    /**
     * Init.
     */
    void init();

    /**
     * Update patient.
     *
     * @param patient the patient
     * @return the patient
     */
    Patient update(Patient patient) throws DaoException;
}
