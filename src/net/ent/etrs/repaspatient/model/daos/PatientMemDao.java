package net.ent.etrs.repaspatient.model.daos;


//@Log4j2(topic = "LoggerDAO")

import net.ent.etrs.repaspatient.model.comparator.PatientNomPrenomComparator;
import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Implementation memoire de la dao.
 *
 * @author christophe.cerqueira
 */
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PatientMemDao implements IPatientMemDao {
    //    private static PatientMemDao INSTANCE;
    private List<Patient> persistence = new ArrayList<>();

    protected PatientMemDao() {
    }

    //    public static PatientMemDao getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new PatientMemDao();
//        }
//        return INSTANCE;
//    }


    @Override
    public Patient create(final Patient patient) throws DaoException {
        if (this.exist(patient)) {
//			log.warn(C.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
        }
        this.persistence.add(patient);
        return this.read(patient.getId());
//		log.info(C.MSG_DAO_PERSITANCE_PATIENT);

    }

    @Override
    public void delete(final Patient patient) throws DaoException {
        if (!this.exist(patient)) {
//			log.warn(C.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
        }
        this.persistence.remove(patient);
//		log.info(C.MSG_DAO_SUPPRESSION_PATIENT);

    }

    @Override
    public void deleteByKey(final String id) throws DaoException {
        this.delete(this.read(id));
    }

    @Override
    public boolean exist(final Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
//			log.warn(C.MSG_DAO_PERSITANCE_PATIENT_NULL);
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        return this.persistence.contains(patient);
    }

    @Override
    public Patient read(final String id) throws DaoException {
        for (Patient contact : persistence) {
            if (contact.getId().equals(id)) {
                return contact;
            }
        }
        throw new DaoException("Contact inexistant");
    }

    @Override
    public List<Patient> readAll() {
        Collections.sort(this.persistence, new PatientNomPrenomComparator());
        return Collections.unmodifiableList(this.persistence);
    }

    @Override
    public Patient update(final Patient patient) throws DaoException {
        if (!this.exist(patient)) {
//			log.warn(C.MSG_DAO_MISE_A_JOUR_PATIENT_INEXISTANT);
        }

//		log.info(C.MSG_DAO_MISE_A_JOUR_PATIENT);
        this.persistence.set(this.persistence.indexOf(patient), patient);
        return this.read(patient.getId());
    }

}
