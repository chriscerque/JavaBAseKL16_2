package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public final class EntitiesFactory {
	private EntitiesFactory() {
	}
	
	
	public static Repas fabriquerRepas (final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasConstructionException {
		
		try {
			return  new Repas (dateRepas,typeRepas);
		} catch (RepasConstructionException e) {
			throw new RepasConstructionException(e.getMessage());
		}
		
	}
	
	public static Patient fabriquerPatient (final String numSecu , final String nom , final String prenom ,
											final LocalDate dateEntree) throws PatientConstructionException {
		try {
			return  new Patient(numSecu,nom,prenom,dateEntree);
		} catch (PatientConstructionException e) {
			throw new PatientConstructionException(e.getMessage());
		}
	}
}
