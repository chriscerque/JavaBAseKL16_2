package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RepasMemDaoImpl implements RepasMemDao{
	
	private List<Repas> persistance = new ArrayList<>();
	
	
	
	@Override
	public Repas create(final Repas repas) throws DaoException {
		if (!Objects.isNull(repas)){
			persistance.add(repas);
			return persistance.get(persistance.indexOf(repas));
		}
		else {
			throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
		}
	}
	
	@Override
	public Repas read(final String id) {
		Repas r = null;
		for (Repas repas : this.persistance) {
			if(repas.getId().equals(id)) {
				r = repas;
			}
		}
		return r;
	}
	
	@Override
	public void delete(final String id) throws DaoException {
		Repas r = this.read(id);
		if (!exist(r)) {
			throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
		}
		this.persistance.remove(r);
	}
	
	@Override
	public Repas update(final Repas repas) throws DaoException {
		try {
			this.persistance.remove(repas);
			this.persistance.add(repas);
		}catch (Exception e) {
			throw new DaoException(ConstantesMetier.DAO_REPAS_MISE_A_JOUR_IMPOSSIBLE);
		}
		return this.read(repas.getId());
	
	}
	
	@Override
	public List<Repas> readAll() {
		return Collections.unmodifiableList(this.persistance);
	}
	
	@Override
	public boolean exist(final Repas repas) throws DaoException {
		try {
			return this.persistance.contains(repas);
		} catch (Exception e) {
			throw new DaoException(ConstantesMetier.DAO_REPAS_EXIST_NULL_EXCEPTON, e);
		}
	}
	
	@Override
	public void deleteByKey(String id) {
	
	}
}
