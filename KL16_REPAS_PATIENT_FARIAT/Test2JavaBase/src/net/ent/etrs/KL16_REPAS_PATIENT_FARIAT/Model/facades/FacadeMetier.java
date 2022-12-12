/**
 *
 */
package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.facades;


import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.excetions.PatientMemDaoException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.Patient;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.Repas;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.facades.exceptions.BusinessException;

import java.util.List;

/**
 * Façade métier proposant les opérations utiles
 * à l'application.
 *
 * @author christophe.cerqueira
 */
public interface FacadeMetier {

    /**
     * Renvoi la liste de tous les patients.
     *
     * @return la liste de patients.
     */
    List<Patient> listerPatients();

    /**
     * Renvoi la liste de tous les repas.
     *
     * @return la liste des repas.
     */
    List<Repas> listerRepas();

    /**
     * Sauvegarge un patient dans l'application.
     *
     * @param patient le patient à sauvegarder.
     * @throws BusinessException si le patient existe déjà ou une erreur est levée durant la sauvegarde.
     */
    void sauvegarderPatient(Patient patient) throws BusinessException, PatientMemDaoException;

    /**
     * Supprime un patient dans l'application;
     *
     * @param patient le patient à supprimer
     * @throws BusinessException si le patient n'existe pas ou une erreur est levée durant la suppression.
     */
    void supprimerPatient(Patient patient) throws BusinessException, PatientMemDaoException;

    /**
     * Met à jour un patient dans l'application.
     *
     * @param patient le patient à mettre à jour.
     * @throws BusinessException si le patient n'existe pas ou une erreur est levée durant la mise à jour.
     */
    void mettreAJourPatient(Patient patient) throws BusinessException, PatientMemDaoException;

    void init() throws BusinessException;

    Patient recupererPatientById(String patientId) throws BusinessException, PatientMemDaoException;
}
