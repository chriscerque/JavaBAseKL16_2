package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.PatientNomPrenomComparator;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.*;

public class PatientMemDao implements IPatientMemDao{
    private List<Patient> persistance = new ArrayList<>();
    /**
     * Read all list.
     *
     * @return the list
     */
    @Override
    public List<Patient> readAll() {
        Collections.sort(persistance, new PatientNomPrenomComparator());
        return Collections.unmodifiableList(persistance);
    }

    /**
     * Create patient.
     *
     * @param patient the patient
     * @return the patient
     */
    @Override
    public Patient create(Patient patient) throws DaoException {
        if (!Objects.isNull(patient)){
            if (persistance.contains(patient)) {
                throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
            }
            persistance.add(patient);
            return persistance.get(persistance.indexOf(patient));
        }
        else {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
    }

    /**
     * Delete.
     *
     * @param patient the patient
     */
    @Override
    public void delete(Patient patient) throws DaoException {
        if(!Objects.isNull(patient))
        {
            deleteByKey(patient.getId());
        }else {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_NULL);
        }
    }

    /**
     * Delete by key.
     *
     * @param id the id
     */
    @Override
    public void deleteByKey(String id) throws DaoException {
        if(!Objects.isNull(id))
        {
            boolean supp = false;
            for (Patient p : persistance){
                if (p.getId().equals(id)){
                    persistance.remove(p);
                    supp = true;
                }
            }
            if (!supp) {
                throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
            }
        }else {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_NULL);
        }
    }

    /**
     * Exist boolean.
     *
     * @param patient the patient
     * @return the boolean
     */
    @Override
    public boolean exist(Patient patient) {
        if (patient == null) {
            return false;
        }
        if (persistance.contains(patient)) {
            return true;
        }
        return false;
    }

    /**
     * Read patient.
     *
     * @param id the id
     * @return the patient
     */
    @Override
    public Patient read(String id) throws DaoException {
        Set<Patient> patientSetTemporaire = new HashSet<>(persistance);
        if (!Objects.isNull(id)){
            for (Patient p: patientSetTemporaire) {
                if (p.getId().equals(id)){
                    return p;
                }
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
    }

    /**
     * Init.
     */
    @Override
    public void init() {

    }

    /**
     * Update patient.
     *
     * @param patient the patient
     * @return the patient
     */
    @Override
    public Patient update(Patient patient) throws DaoException {
        if(Objects.isNull(patient)){
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_NULL);
        }
        if (!persistance.contains(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_INEXISTANT);
        }
        persistance.remove(patient);
        persistance.add(patient);
        return patient;
    }
}
