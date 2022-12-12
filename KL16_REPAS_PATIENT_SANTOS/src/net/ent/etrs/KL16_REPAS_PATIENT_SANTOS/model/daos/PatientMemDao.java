package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.daos;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.daos.exceptions.DaoException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.EntitiesFactory;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.Patient;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.Repas;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.PatientException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.RepasException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.facades.exceptions.BusinessException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references.ConstantesMetier;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references.RegimeAlimentaire;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references.TypeRepas;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class PatientMemDao  implements IPatientMemDao {


    private final List<Patient> persistance = new ArrayList<>();

    @Override
    public Patient create(Patient patient) throws DaoException {
        if (!Objects.isNull(patient)) {
            persistance.add(patient);
            return persistance.get(persistance.indexOf(patient));
        } else {
            throw new DaoException(ConstantesMetier.DAO_PATIENT_IMPOSSIBLE_CREATE);
        }
    }

    @Override
    public List<Patient> readAll() {
        return Collections.unmodifiableList(persistance);
    }

    @Override
    public Patient update(Patient patient) throws DaoException {
        if (!Objects.isNull(patient) && persistance.contains(patient)) {
            persistance.set(persistance.indexOf(patient), patient);
            return persistance.get(persistance.indexOf(patient));
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_NULL);
    }


    @Override
    public void delete(Patient patient) throws DaoException {
        if (!Objects.isNull(patient)) {
            for (Patient patient1 : persistance) {
                persistance.remove(patient);

            }
        } else {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_NULL);
        }
    }

    @Override
    public void deleteByKey(UUID id) throws DaoException {
        if (!Objects.isNull(id)) {
            for (Patient patient : persistance) {
                if (patient.getId().equals(id)) {
                    persistance.remove(patient);

                } else {
                    throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_NULL);
                }
            }
        }
    }

    @Override
    public boolean exist(Patient patient) throws DaoException {

        for (Patient patient1 : persistance) {
            return persistance.contains(patient);
        }

        return false;
    }

    @Override
    public Patient read(String patient) throws DaoException {
//        if (!Objects.isNull(patient)) {
//            for (Patient patient1 : persistance) {
//                if (patient.getId().equals(id)) {
//                    persistance.remove(patient);
//
//                } else {
//                    throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_NULL);
//                }
//            }

        return null;
    }

    @Override
    public void init() throws DaoException, BusinessException, RepasException {

    }


}

