package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.*;

public class Repas {
	
	//ATTRIBUTS
	
	private final String id = UUID.randomUUID().toString();
	private LocalDate dateRepas;
	private TypeRepas typeRepas;
	private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();
	
	
	//constructeurs
	
	protected Repas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasConstructionException {
		try {
			this.setDateRepas(dateRepas);
			this.setTypeRepas(typeRepas);
		} catch (RepasException e) {
			throw new RepasConstructionException(ConstantesMetier.REPAS_CONSTRUCTION_ERROR_EXCEPTION,e);
		}
		
	}
	
	
	//accesseurs
	
	public String getId() {
		return id;
	}
	
	public LocalDate getDateRepas() {
		return dateRepas;
	}
	
	public void setDateRepas(final LocalDate dateRepas) throws RepasException {
		if (Objects.isNull(dateRepas)){
			throw new RepasException(ConstantesMetier.REPAS_DATE_EXCEPTION);
		}
		this.dateRepas = dateRepas;
	}
	
	public TypeRepas getTypeRepas() {
		return typeRepas;
	}
	
	public void setTypeRepas(final TypeRepas typeRepas) throws RepasException {
		if (Objects.isNull(typeRepas)){
			throw new RepasException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
		}
		this.typeRepas = typeRepas;
	}
	
	public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
		return Collections.unmodifiableList(lstRegimeAlimentaire);
	}
	
	
	
	//equals & hashcode
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Repas repas = (Repas) o;
		return Objects.equals(dateRepas, repas.dateRepas) && typeRepas == repas.typeRepas;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dateRepas, typeRepas);
	}
	
	
	//toString
	
	@Override
	public String toString() {
		return "Repas{" +
				"id='" + id + '\'' +
				", dateRepas=" + dateRepas +
				", typeRepas=" + typeRepas +
				'}';
	}
	
	
	//Autres methodes
	
	
	public void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaire) throws RepasException {
		if (Objects.isNull(regimeAlimentaire)){
			throw new RepasException(ConstantesMetier.REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
		}
		if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)){
			throw new RepasException(ConstantesMetier.REPAS_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
		}
		
		this.lstRegimeAlimentaire.add(regimeAlimentaire);
	}
}
