package net.ent.etrs.repaspatient.model.daos;

public class DaoFactory {
	public static RepasMemDao fabriqueRepasDao(){
		return new RepasMemDaoImpl();
	}
	
	public static PatientMemDao fabriquePatientDao(){
		return new PatientMemDaoImpl();
	}
	
}
