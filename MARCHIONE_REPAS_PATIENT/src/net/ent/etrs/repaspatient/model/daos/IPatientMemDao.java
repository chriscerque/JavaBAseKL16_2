package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.entities.references.Patient;
import net.ent.etrs.repaspatient.model.entities.references.Repas;

/**
 * The interface Patient mem dao.
 */
public interface IPatientMemDao {


    /**
     * Read patient.
     *
     * @param numSecu the num secu
     * @return the patient
     */
    public abstract Patient read(String numSecu);

    /**
     * Delete by key.
     *
     * @param s the s
     */
    public abstract void deleteByKey(String s);

    /**
     * Delete.
     *
     * @param patient the patient
     */
    public abstract void delete(Patient patient);


    /**
     * Update repas.
     *
     * @param patient the patient
     * @return the repas
     */
    public abstract Repas update(Patient patient);

    /**
     * Exist boolean.
     *
     * @param patient the patient
     * @return the boolean
     */
    public abstract boolean exist(Patient patient);

    /**
     * Init.
     */
    public void init();

    /**
     * Create patient.
     *
     * @param patient the patient
     * @return the patient
     */
    public abstract Patient create(Patient patient);
}
