package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.comparator.PatientNomPrenomComparator;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DaoPatientImpl implements DaoPatient {

    private List<Patient> persistance = new ArrayList<>();

    protected DaoPatientImpl() {
    }

    @Override
    public Patient save(final Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        if (exist(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
        }
        this.persistance.add(patient);
        return this.read(patient.getId());
    }

    @Override
    public Patient read(final String id) throws DaoException {
        Patient p = null;
        for (Patient patient : this.persistance) {
            if(patient.getId().equals(id)) {
                p = patient;
            }
        }
        return p;
    }

    @Override
    public List<Patient> readAll() {
        List<Patient> retour = new ArrayList<>(persistance);
        retour.sort(new PatientNomPrenomComparator());
        return  Collections.unmodifiableList(retour);
    }

    @Override
    public Patient update(final Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_NULL);
        }
        if (!exist(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_INEXISTANT);
        }
            this.persistance.remove(patient);
            this.persistance.add(patient);
        return this.read(patient.getId());
    }

    @Override
    public void delete(final Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_NULL);
        }
        if (!exist(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
        }
        this.persistance.remove(patient);
    }

    @Override
    public void deleteByKey(final String id) throws DaoException {
        this.delete(read(id));
    }

    @Override
    public boolean exist(final Patient patient) throws DaoException {
        try {
            return this.persistance.contains(patient);
        } catch (Exception e) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PATIENT_INEXISTANT, e);
        }
    }

    @Override
    public void init() throws DaoException {

    }
}
