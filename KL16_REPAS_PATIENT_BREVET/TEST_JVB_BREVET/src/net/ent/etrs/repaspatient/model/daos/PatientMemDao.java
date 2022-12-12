package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.NomPrenomComparator;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PatientMemDao implements IPatientMemDao {
    //region ATTRIBUTS
    List<Patient> persistence = new ArrayList<>();

    //endregion
    //region CONSTRUCTEUR(S)

    protected PatientMemDao() {
    }

    //endregion

    /**
     * Créé un patient dans la base de données.
     *
     * @param patient le patient à créer
     * @throws DaoException si le patient existe déjà dans la base de données ou si il est null
     */
    @Override
    public void create(final Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        if (this.exist(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
        }
        this.persistence.add(patient);
    }

    /**
     * Renvoie un patient à partir d'un identifiant associé.
     *
     * @param id l'identifiant du patient
     * @return le patient associé
     * @throws DaoException si le patient n'a pas été trouvé
     */
    @Override
    public Patient read(final String id) throws DaoException {
        if (Objects.isNull(id) || id.isBlank()) {
            throw new DaoException(ConstantesMetier.DAO_ID_NULL);
        }
        for (Patient p : this.persistence) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new DaoException(ConstantesMetier.MSG_PATIENT_INTROUVABLE);
    }

    /**
     * Met à jour un patient dans la BDD.
     *
     * @param patient le patient à mettre à jour
     * @throws DaoException si le repas existe déjà ou est null
     */
    @Override
    public Patient update(final Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_NULL);
        }
        if (!this.exist(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_INEXISTANT);
        }
        this.persistence.set(persistence.indexOf(patient), patient);
        return patient;
    }

    /**
     * Supprime un patient de la BDD.
     *
     * @param patient le patient à supprimer
     * @throws DaoException si le patient est null ou si il n'existe pas
     */
    @Override
    public void delete(final Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_NULL);
        }
        if (!this.exist(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
        }
        this.persistence.remove(patient);
    }

    /**
     * Pour sélectionner la liste des patients enregistrés.
     *
     * @return la liste non-modifiable des patients
     */
    @Override
    public List<Patient> readall() {
        Collections.sort(this.persistence, new NomPrenomComparator());
        return Collections.unmodifiableList(this.persistence);
    }

    @Override
    public void init() {

    }

    @Override
    public void deleteByKey(final String id) throws DaoException {
        if (Objects.isNull(id) || id.isBlank()) {
            throw new DaoException(ConstantesMetier.DAO_ID_NULL);
        }
        if (!this.exist(this.read(id))) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
        }
        Patient patient = this.read(id);
        this.persistence.remove(patient);
    }

    /**
     * Vérifie la présence d'un patient dans la BDD.
     *
     * @param patient le patient à vérifier
     * @return un bolléen exprimant la présence ou non du patient
     * @throws DaoException
     */
    @Override
    public boolean exist(final Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        return this.persistence.contains(patient);
    }
}
