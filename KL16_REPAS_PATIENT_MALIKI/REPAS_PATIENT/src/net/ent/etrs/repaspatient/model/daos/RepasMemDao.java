package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

public interface RepasMemDao {
	
	Repas create(final Repas repas) throws DaoException;
	
	Repas read(final String id) ;
	
	void delete(final String id) throws DaoException;
	
	Repas update(final Repas repas) throws DaoException;
	
	List<Repas> readAll();
	
	boolean exist(final Repas repas) throws DaoException;
	
	void deleteByKey(final String id);
}
