package net.ent.etrs.repaspatient.model.facades;

import net.ent.etrs.repaspatient.model.daos.DaoFactory;
import net.ent.etrs.repaspatient.model.daos.DaoPatient;
import net.ent.etrs.repaspatient.model.daos.DaoRepas;
import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;

import java.util.List;

public class FacadeMetierImpl implements FacadeMetier{

    DaoPatient daoPatient;
    DaoRepas daoRepas;

    public FacadeMetierImpl() {
        this.daoPatient = DaoFactory.fabriquerDaoPatient();
        this.daoRepas = DaoFactory.fabriquerDaoRepas();
    }

    /**
     * Renvoi la liste de tous les patients.
     *
     * @return la liste de patients.
     */
    @Override
    public List<Patient> listerPatients() {
        return daoPatient.readAll();
    }

    /**
     * Renvoi la liste de tous les repas.
     *
     * @return la liste des repas.
     */
    @Override
    public List<Repas> listerRepas() {
        return daoRepas.readAll();
    }

    /**
     * Sauvegarge un patient dans l'application.
     *
     * @param patient le patient à sauvegarder.
     * @return
     * @throws BusinessException si le patient existe déjà ou une erreur est levée durant la sauvegarde.
     */
    @Override
    public Patient sauvegarderPatient(final Patient patient) throws BusinessException {
        try {
            daoPatient.save(patient);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_CREATION_EXCEPTION,e);
        }
        return patient;
    }

    /**
     * Supprime un patient dans l'application;
     *
     * @param patient le patient à supprimer
     * @throws BusinessException si le patient n'existe pas ou une erreur est levée durant la suppression.
     */
    @Override
    public void supprimerPatient(final Patient patient) throws BusinessException {
        try {
            daoPatient.delete(patient);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_SUPPRESSION_EXCEPTION ,e);
        }
    }

    /**
     * Met à jour un patient dans l'application.
     *
     * @param patient le patient à mettre à jour.
     * @throws BusinessException si le patient n'existe pas ou une erreur est levée durant la mise à jour.
     */
    @Override
    public void mettreAJourPatient(final Patient patient) throws BusinessException {
        try {
            daoPatient.update(patient);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_MISE_A_JOUR_EXCEPTION ,e);
        }
    }

    @Override
    public void init() throws BusinessException {

    }

    @Override
    public Patient recupererPatientById(final String patientId) throws BusinessException {
        try {
            return daoPatient.read(patientId);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_RECHERCHE_EXCEPTION ,e);
        }
    }
}
