package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PatientMemDaoImpl  implements PatientMemDao{
	
	private List<Patient> persistance = new ArrayList<>();
	
	
	
	@Override
	public Patient create(final Patient patient) throws DaoException {
		if (!Objects.isNull(patient)){
			persistance.add(patient);
			return persistance.get(persistance.indexOf(patient));
		}
		else {
			throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
		}
	}
	
	@Override
	public Patient read(final String id) {
		Patient p = null;
		for (Patient patient : this.persistance) {
			if(patient.getId().equals(id)) {
				p = patient;
			}
		}
		return p;
	}
	
	@Override
	public void delete(final String id) throws DaoException {
		Patient p = this.read(id);
		if (!exist(p)) {
			throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
		}
		this.persistance.remove(p);
	}
	
	@Override
	public Patient update(final Patient patient) throws DaoException {
		try {
			this.persistance.remove(patient);
			this.persistance.add(patient);
		}catch (Exception e) {
			throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_INEXISTANT);
		}
		return this.read(patient.getId());
	}
	
	@Override
	public List<Patient> readAll() {
		return Collections.unmodifiableList(this.persistance);
	}
	
	@Override
	public boolean exist(final Patient patient) throws DaoException {
		try {
			return this.persistance.contains(patient);
		} catch (Exception e) {
			throw new DaoException(ConstantesMetier.DAO_PATIENT_EXIST_NULL_EXCEPTON, e);
		}
	}
	
	@Override
	public void deleteByKey(String id) {
	
	}
}
