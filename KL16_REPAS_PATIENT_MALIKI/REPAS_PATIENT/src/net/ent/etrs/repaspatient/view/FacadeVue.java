package net.ent.etrs.repaspatient.view;


import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

import java.util.List;

public interface FacadeVue {

    /**
     * Affiche un message.
     *
     * @param msg le message à afficher.
     */
    void afficherMessage(String msg);

    /**
     * Affiche un message d'erreur.
     *
     * @param msg le message à afficher.
     */
    void afficherMessageErreur(String msg);

    /**
     * Affiche le menu principal de l'application.
     *
     * @return le choix de l'option choisie.
     */
    int afficherMenu();

    /**
     * Affiche un patient.
     *
     * @param patient le patient à afficher.
     */
    void afficherPatient(Patient patient);

    /**
     * Affiche un ensemble de patients.
     *
     * @param lstPatients la liste des patients à afficher.
	 */
    void afficherPatients(List<Patient> lstPatients) throws ViewException;

    /**
     * Demande la saisie d'un patient.
     *
     * @return le patient saisi.
     * @throws ViewException si une erreur est survenue durant la saisie.
     */
    Patient saisirPatient() throws ViewException;

    /**
     * Affiche un ensemble de patients et demande la sélection d'un des patients.
     *
     * @param lstPatients la liste des patients à afficher.
     * @return le patient sélectionné.
     */
    Patient selectionnerPatient(List<Patient> lstPatients);

//    void afficherRepas(Patient c);

    /**
     * Affiche un ensemble de repas et demande la sélection d'un des repas.
     *
     * @param lstRepas la liste des repas à afficher.
     * @return le repas sélectionné.
     */
    List<Repas> selectionnerRepas(List<Repas> lstRepas);

    /**
     * @param patient
     * @return
     * @throws ViewException
     */
    Patient modifierPatient(Patient patient) throws ViewException;

    /**
     * @param lstPatients
     * @param listRepas
     * @return
     */
    Patient ajouterRepasPatient(List<Patient> lstPatients, List<Repas> listRepas);
}
