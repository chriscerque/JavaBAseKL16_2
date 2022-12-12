package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.daos.references.ConstantesDao;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.comparator.PatientNomPrenomComparator;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PatientMemDao implements IPatientMemDao {

    //attribut(s)

    private List<Patient> persistence = new ArrayList<>();

    //constructeur(s)

    protected PatientMemDao() {
    }


    //super(interface) methode(s)


    @Override
    public Patient create(final Patient pPatient) throws DaoException {
        if (Objects.isNull(pPatient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        if (exist(pPatient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
        }
        this.persistence.add(pPatient);
        return pPatient;
    }

    @Override
    public Patient read(final String numSecu) throws DaoException {
        if (Objects.isNull(numSecu)) {
            throw new DaoException(ConstantesDao.MSG_DAO_NUMSECU_NULL);
        }
        for (Patient patient : this.persistence) {
            if (patient.getNumSecu().equals(numSecu)) {
                return patient;
            }
        }
        throw new DaoException(ConstantesDao.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
    }

    @Override
    public void deleteByKey(final String numSecu) throws DaoException {
        if (Objects.isNull(numSecu)) {
            throw new DaoException(ConstantesDao.MSG_DAO_NUMSECU_NULL);
        }
        Patient patient = this.read(numSecu);
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (!exist(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
        }
        this.persistence.remove(patient);

    }

    @Override
    public void delete(final Patient pPatient) throws DaoException {
        if (Objects.isNull(pPatient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        if (!exist(pPatient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
        }
        this.persistence.remove(pPatient);
    }

    @Override
    public Patient update(final Patient pPatient) throws DaoException {
        if (Objects.isNull(pPatient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        try {
            this.persistence.remove(pPatient);
            this.persistence.add(pPatient);
        } catch (Exception e) {
            throw new DaoException(ConstantesMetier.MSG_PATIENT_MISE_A_JOUR_EXCEPTION);
        }
        return this.read(pPatient.getId());
    }


    @Override
    public List<Patient> readAll() {
//        List<Patient> temps = new ArrayList<>(this.persistence);
//        temps.sort(new PatientNomPrenomComparator());
//        this.persistence.forEach(System.out::println);
//        System.out.println("-------------------------------");
//        return Collections.unmodifiableList(temps);
//        this.persistence.forEach(System.out::println);

        //triage ar nom puis prenom de la liste puis envoie des donnees
        Collections.sort(this.persistence, new PatientNomPrenomComparator());
        return Collections.unmodifiableList(this.persistence);
    }

    @Override
    public boolean exist(final Patient pPatient) throws DaoException {
        if (Objects.isNull(pPatient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        return this.persistence.contains(pPatient);
    }


    @Override
    public void init() {

    }
}
