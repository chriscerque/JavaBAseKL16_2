package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.entities.references.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Patient mem dao.
 */
public class PatientMemDao {

    private List<Patient> persistence = new ArrayList<>();


    /**
     * Instantiates a new Patient mem dao.
     */
    protected PatientMemDao() {
    }

    /**
     * Read all list.
     *
     * @return the list
     */
    public List<Patient> readAll() {

        return null;
    }

    /**
     * Update patient.
     *
     * @param patient the patient
     * @return the patient
     */
    public Patient update(Patient patient) {

        return patient;
    }

    /**
     * Delete.
     *
     * @param patient the patient
     */
    public void delete(Patient patient) {

    }

    /**
     * Delete by key.
     *
     * @param s the s
     */
    public void deleteByKey(String s) {

    }

    /**
     * Create patient.
     *
     * @param patient the patient
     * @return the patient
     */
    public Patient create (Patient patient) {

        return patient;
    }

    /**
     * Exist boolean.
     *
     * @param patient the patient
     * @return the boolean
     */
    public boolean exist(Patient patient) {

        return false;
    }

    /**
     * Repas patient.
     *
     * @param s the s
     * @return the patient
     */
    public Patient repas (String s) {

        return null;
    }
}
