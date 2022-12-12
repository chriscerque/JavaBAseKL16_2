package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao;

import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.excetions.PatientMemDaoException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.Patient;

import java.util.List;

public interface PatientMemDao {
    /**
     * Permet de sauvegarder un patient.
     *
     * @param patient patient à sauvegarder
     * @return le patient sauvegarder
     * @throws PatientMemDaoException si le patient est null
     */
    public Patient create(Patient patient) throws PatientMemDaoException;

    /**
     * Selectionne tous les patient
     *
     * @return la liste des patient
     */
    public List<Patient> readAll();

    /**
     * Permet la mise à jour d'un patient.
     *
     * @param patient le repas à mettre à jour
     * @return le patient mise à jour
     * @throws PatientMemDaoException si le patient est null ou qu'il n'est pas connue
     */
    public Patient update(Patient patient) throws PatientMemDaoException;

    /**
     * Permet de supprimer un patient avec son id.
     *
     * @param numSecu identifiant du patient à supprimer
     * @throws PatientMemDaoException si l'id est null ou non connu
     */
    public void deleteByKey(String numSecu) throws PatientMemDaoException;

    /**
     * Selectionne tous les véhicule.
     *
     * @return la liste des repas
     */

    Patient read(String id) throws PatientMemDaoException;

    /**
     * Permet de supprimer un repas .
     *
     * @param patient identifiant du patient à supprimer
     * @throws PatientMemDaoException
     */


    public void delete(Patient patient) throws PatientMemDaoException;


    /**
     * revoie si un patient existe ou non
     *
     * @param patient
     * @return boolean
     * @throws PatientMemDaoException
     */
    boolean exist(Patient patient) throws PatientMemDaoException;

}
