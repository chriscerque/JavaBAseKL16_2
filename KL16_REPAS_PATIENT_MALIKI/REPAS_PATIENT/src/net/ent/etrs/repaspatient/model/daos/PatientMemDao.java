package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

public interface PatientMemDao {
	Patient create(final Patient patient) throws DaoException;
	
	Patient read(final String id) ;
	
	void delete(final String id) throws DaoException;
	
	Patient update(final Patient patient) throws DaoException;
	
	List<Patient> readAll();
	
	boolean exist(final Patient patient) throws DaoException;
	
	void deleteByKey(final String id);
}
