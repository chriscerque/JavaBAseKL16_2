package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.PatientNomPrenomComparator;
import net.ent.etrs.repaspatient.model.entities.exceptions.ConstantesException;

import java.util.*;

public class PatientMemDaoImpl implements IPatientMemDao {

    private Set<Patient> persistance = new LinkedHashSet<>();

    @Override
    public Patient create(final Patient patient) throws DaoException {
        if (!Objects.isNull(patient) || persistance.add(patient)) {
            return patient;
        } else throw new DaoException(ConstantesException.DAO_PATIENT_IMPOSSIBLE_CREATE);

    }

    @Override
    public Patient update(final Patient patient) throws DaoException {
        if (!Objects.isNull(patient) || persistance.contains(patient)) {
            persistance.remove(patient);
            persistance.add(patient);
            return patient;
        }
        throw new DaoException(ConstantesException.DAO_PATIENT_IMPOSSIBLE_UPDATE);
    }

    /**
     * Return le patient en fonction de son numéro de sécurité sociale.
     *
     * @param libelle le numéro de sécurité sociale
     * @return le patient
     */
    @Override
    public Patient read(final String libelle) throws DaoException {
        Set<Patient> patientSetTemporaire = new HashSet<>(persistance);
        if (!Objects.isNull(libelle)) {
            for (Patient item : patientSetTemporaire) {
                if (item.getNumSecu().equals(libelle)) {
                    return item;
                }
            }
        }
        throw new DaoException(ConstantesException.DAO_PATIENT_NULL);
    }

    /**
     * Supprime le patient en donnant son numéro de sécurité sociale
     *
     * @param libelle le numéro de sécurité sociale du patient
     */
    @Override
    public void delete(final String libelle) throws DaoException {
        Set<Patient> patientSetTemporaire = new HashSet<>(persistance);
        if (!Objects.isNull(libelle)) {
            for (Patient item : patientSetTemporaire) {
                if (item.getNumSecu().equals(libelle)) {
                    persistance.remove(item);
                }
            }
        } else {
            throw new DaoException(ConstantesException.DAO_PATIENT_NULL);
        }
    }

    /**
     * Retourne une liste de tous les patients triés.
     *
     * @return Set<patient> liste de patients
     */
    @Override
    public Set<Patient> readAll() {
        List<Patient> lstTemp = new ArrayList<>(persistance);
        lstTemp.sort(new PatientNomPrenomComparator());
        Set<Patient> setTemp = new HashSet<>(lstTemp);
        return Collections.unmodifiableSet(setTemp);
    }

    @Override
    public boolean exist(final Patient patient) throws DaoException {
        try {
            return this.persistance.contains(patient);
        } catch (Exception e) {
            throw new DaoException(ConstantesException.DAO_PATIENT_NULL);
        }
    }

    // TO STRING

    @Override
    public String toString() {
        return "PatientDaoImpl{" +
                "persistance=" + persistance +
                '}';
    }
}
