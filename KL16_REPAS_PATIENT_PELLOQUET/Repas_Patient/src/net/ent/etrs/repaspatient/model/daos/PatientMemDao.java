package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.List;
import java.util.UUID;

public interface PatientMemDao {
    /**
     * Permet de créer un Patient en Base
     *
     * @param patient Patient à créer dans la Base
     */
    public Patient create(Patient patient) throws DaoException;
    /**
     * Permet de selectionner un Patient dans la base
     *
     * @param idPatient identifiant du Patient à selectionner
     */
    public Patient read(String idPatient) throws DaoException;
    /**
     * Permet d'afficher tous les Patients
     */
        public List<Patient> readAll();
    /**
     * Permet de mettre à jour un Patient
     *
     * @param patient Patient à mettre à jour
     */
    public Patient update(Patient patient) throws DaoException;

    /**
     * Permet de supprimer un Patient de la base
     *
     * @param idPatient identifiant du Patient à supprimer
     */
    public void deleteByKey(String idPatient) throws DaoException;

    /**
     * Permet de supprimer un Patient de la base
     *
     * @param patient identifiant du Patient à supprimer
     */
    public void delete(Patient patient) throws DaoException;

    /**
     * vérifie l'existence d'un client dans la base
     * @param patient
     * @return true/false
     */
    public boolean exist(Patient patient);
}
