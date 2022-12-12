package net.ent.etrs.repaspatient.model.facades;

import net.ent.etrs.repaspatient.model.daos.PatientMemDao;
import net.ent.etrs.repaspatient.model.daos.PatientMemDaoImpl;

public class FacadeFactory {
	public static FacadeMetier fabriqueFacadeMetier(){
		return new FacadeMetierImpl();
	}
}
