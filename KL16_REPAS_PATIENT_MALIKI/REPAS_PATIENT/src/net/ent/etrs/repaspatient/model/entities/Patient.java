package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.*;

public class Patient {
	
	//ATTRIBUTS
	private final String id = UUID.randomUUID().toString();
	private LocalDate dateEntree;
	private List<Repas> lstRepas = new ArrayList<>();
	private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();
	private String numSecu;
	private String nom;
	private String prenom;
	
	//CONSTRUCTEURS
	
	protected Patient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree) throws PatientConstructionException {
		try {
			this.setNom(nom);
			this.setPrenom(prenom);
			this.setDateEntree(dateEntree);
			this.setNumSecu(numSecu);
		} catch (PatientException e) {
			throw new PatientConstructionException(ConstantesMetier.PATIENT_CONSTRUCTION_ERROR, e);
		}
		
		
	}
	
	
	//ACCESSEURS
	
	public String getId() {
		return id;
	}
	
	public LocalDate getDateEntree() {
		return dateEntree;
	}
	
	public void setDateEntree(final LocalDate dateEntree) throws PatientException {
		if (Objects.isNull(dateEntree) || dateEntree.isAfter(LocalDate.now())) {
			throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_EXCEPTION);
		}
		this.dateEntree = dateEntree;
	}
	
	public List<Repas> getLstRepas() {
		return Collections.unmodifiableList(lstRepas);
	}
	
	public void setLstRepas(final List<Repas> lstRepas) {
		this.lstRepas = lstRepas;
	}
	
	public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
		return Collections.unmodifiableList(lstRegimeAlimentaire);
	}
	
	public void setLstRegimeAlimentaire(final List<RegimeAlimentaire> lstRegimeAlimentaire) throws PatientException {
		if (this.lstRegimeAlimentaire.contains(lstRegimeAlimentaire)) {
			throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
		}
		this.lstRegimeAlimentaire = lstRegimeAlimentaire;
	}
	
	public String getNumSecu() {
		return numSecu;
	}
	
	public void setNumSecu(final String numSecu) throws PatientException {
		if (Objects.isNull(numSecu) || numSecu.length() < ConstantesMetier.PATIENT_NUM_SECU_TAILLE) {
			throw new PatientException(ConstantesMetier.MSG_PATIENT_NUM_SECU_EXCEPTION);
		}
		this.numSecu = numSecu;
	}
	
	public String getNom() {
		
		return nom;
	}
	
	public void setNom(final String nom) throws PatientException {
		if (Objects.isNull(nom)) {
			throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_EXCEPTION);
		}
		if (nom.length() < ConstantesMetier.PATIENT_NOM_TAILLE_MIN || nom.length() > ConstantesMetier.PATIENT_NOM_TAILLE_MAX) {
			throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_LENGTH_EXCEPTION);
		}
		this.nom = nom;
	}
	
	public String getPrenom() {
		
		return prenom;
	}
	
	public void setPrenom(final String prenom) throws PatientException {
		if (Objects.isNull(prenom)) {
			throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION);
		}
		if (prenom.length() < ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN || prenom.length() > ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX) {
			throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION);
		}
		this.prenom = prenom;
	}
	
	//EQAULS & HASHCODE
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Patient patient = (Patient) o;
		return Objects.equals(numSecu, patient.numSecu);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(numSecu);
	}
	
	
	//TOSTRING
	
	@Override
	public String toString() {
		return "Patient{" +
				"id='" + id + '\'' +
				", dateEntree=" + dateEntree +
				", numSecu='" + numSecu + '\'' +
				", nom='" + nom + '\'' +
				", prenom='" + prenom + '\'' +
				'}';
	}
	
	
	//AUTRES METHODES
	
	public void ajouterRepas(final Repas repas) throws PatientException {
		if (Objects.isNull(repas)) {
			throw new PatientException(ConstantesMetier.PATIENT_REPAS_NULL_EXCEPTION);
		}
		if (this.lstRepas.contains(repas)) {
			throw new PatientException(ConstantesMetier.PATIENT_REPAS_EXIST_EXCEPTION);
		}
		
		this.lstRepas.add(repas);
	}
	
	public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire) throws RepasException, PatientException {
		if (Objects.isNull(regimeAlimentaire)) {
			throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_NULL_EXCEPTION);
		}
		if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)) {
			throw new PatientException(ConstantesMetier.PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
		}
		
	}
	
	private void controlerRegimeAlimentaire(Repas repas) throws RepasException {
		for (Repas r : this.lstRepas) {
			if (r.getTypeRepas().equals(repas.getTypeRepas())) {
				this.lstRepas.add(repas);
			} else {
				throw new RepasException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_NON_COMPATIBLE_EXCEPTION);
			}
			
		}
	}
}