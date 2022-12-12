package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class IPatientMemDao implements PatientMemDao {

        List<Patient> persistence = new ArrayList<>();

        protected IPatientMemDao() {
        }

        /**
         * Permet de créer un Patient en Base
         *
         * @param patient Patient à créer dans la Base
         */
        @Override
        public Patient create(Patient patient) throws DaoException {
            if (Objects.isNull(patient)) {
                throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
            }
            if (persistence.contains(patient)) {
                throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
            }
            persistence.add(patient);
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT);
            return patient;
        }

        /**
         * Permet de selectionner un Patient dans la base
         *
         * @param idPatient identifiant du Patient à selectionner
         */
        @Override
        public Patient read(String idPatient) throws DaoException {
            if (Objects.isNull(idPatient)) {
                throw new DaoException(ConstantesMetier.MSG_DAO_LECTURE_PATIENT_NULL);
            }
            List<Patient> listePatientTemporaire = new ArrayList<>(persistence);

            for (Patient patient : listePatientTemporaire) {
                if (idPatient.equals(patient.getId())) {
                    return patient;
                }
            }
            throw new DaoException(ConstantesMetier.MSG_DAO_LECTURE_PATIENT_INEXISTANT);
        }

        /**
         * Permet d'afficher tous les Patient
         */
        @Override
        public List<Patient> readAll() {
            return Collections.unmodifiableList(persistence);
        }

        /**
         * Permet de mettre à jour un Patient
         *
         * @param patient Patient à mettre à jour
         */
        @Override
        public Patient update(Patient patient) throws DaoException {
            if (Objects.isNull(patient)) {
                throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_NULL);
            }
            if (!persistence.contains(patient)) {
                throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_INEXISTANT);
            }

            Patient r = persistence.set(persistence.indexOf(patient), patient);
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT);
            return r;
        }

    /**
     * Permet de supprimer un Patient de la base
     *
     * @param idPatient identifiant du Patient à supprimer
     */
    @Override
    public void deleteByKey(String idPatient) throws DaoException {

        if (Objects.isNull(idPatient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_NULL);
        }
        int temp = 0;

        List<Patient> listePatientTemporaire = new ArrayList<>(persistence);

        for (Patient t : listePatientTemporaire) {
            if (t.getId().equals(idPatient)) {
                persistence.remove(t);
            } else {
                temp++;
            }
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT);
            if (temp == persistence.size()) {
                throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
            }
        }
    }
    /**
     * Permet de supprimer un Patient de la base
     *
     * @param patient identifiant du Patient à supprimer
     */
    @Override
    public void delete(Patient patient) throws DaoException {

        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_NULL);
        }
        if (!persistence.contains(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
        }
        persistence.remove(patient);
    }

    /**
     * vérifie l'existence d'un client dans la base
     * @param patient
     * @return true/false
     */
    @Override
    public boolean exist(Patient patient) {
        if (persistence.contains(patient)) {
            return true;
        }
        return false;
    }
}
