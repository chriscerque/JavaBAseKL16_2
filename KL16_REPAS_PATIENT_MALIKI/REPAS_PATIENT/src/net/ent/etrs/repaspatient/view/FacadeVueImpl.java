package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

import java.util.ArrayList;
import java.util.List;

public class FacadeVueImpl implements FacadeVue {
	
	/**
	 * Affiche un message.
	 *
	 * @param msg le message à afficher.
	 */
	@Override
	public void afficherMessage(String msg) {
		{
			AffichageConsole.afficherMessageAvecSautLigne(msg);
		}
	
	}
	
	/**
	 * Affiche un message d'erreur.
	 *
	 * @param msg le message à afficher.
	 */
	@Override
	public void afficherMessageErreur(String msg) {
		{
			AffichageConsole.afficherErreur(msg);
		}
	
	}
	
	/**
	 * Affiche le menu principal de l'application.
	 *
	 * @return le choix de l'option choisie.
	 */
	@Override
	public int afficherMenu() {
		List<String> initMenu = new ArrayList<>(initMenuPrincipal());
		AffichageConsole.afficherMenuSimple(initMenu);
		return LectureConsole.lectureChoixInt(0,initMenu.size());
	}
	
	public Object initMenuPrincipal() {
		List<String> retour = new ArrayList<>();
		retour.add("Lister les patients");
		retour.add("Créer un nouveau patient");
		retour.add("modifier un patient");
		retour.add("supprime un patient");
		retour.add("ajouter un repas a un  patient");
		return retour;
	}
	
	/**
	 * Affiche un patient.
	 *
	 * @param patient le patient à afficher.
	 */
	@Override
	public void afficherPatient(Patient patient) {
	
	}
	
	/**
	 * Affiche un ensemble de patients.
	 *
	 * @param lstPatients la liste des patients à afficher.
	 */
	@Override
	public void afficherPatients(List<Patient> lstPatients) throws ViewException {
	
		if(lstPatients.size() == 0){
			throw new ViewException(ConstantesMetier.FACADE_VIEW_LIST_PATIENTS_VIDE);
		}
		List<String> tmp = new ArrayList<>();
		for (Patient p : lstPatients) {
		tmp.add(p.toString());
		}
		
	}
	
	/**
	 * Demande la saisie d'un patient.
	 *
	 * @return le patient saisi.
	 * @throws ViewException si une erreur est survenue durant la saisie.
	 */
	@Override
	public Patient saisirPatient() throws ViewException {
		return null;
	}
	
	/**
	 * Affiche un ensemble de patients et demande la sélection d'un des patients.
	 *
	 * @param lstPatients la liste des patients à afficher.
	 * @return le patient sélectionné.
	 */
	@Override
	public Patient selectionnerPatient(List<Patient> lstPatients) {
		return null;
	}
	
	/**
	 * Affiche un ensemble de repas et demande la sélection d'un des repas.
	 *
	 * @param lstRepas la liste des repas à afficher.
	 * @return le repas sélectionné.
	 */
	@Override
	public List<Repas> selectionnerRepas(List<Repas> lstRepas) {
		return null;
	}
	
	/**
	 * @param patient
	 * @return
	 * @throws ViewException
	 */
	@Override
	public Patient modifierPatient(Patient patient) throws ViewException {
		return null;
	}
	
	/**
	 * @param lstPatients
	 * @param listRepas
	 * @return
	 */
	@Override
	public Patient ajouterRepasPatient(List<Patient> lstPatients, List<Repas> listRepas) {
		return null;
	}
}
