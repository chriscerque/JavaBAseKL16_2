package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

public interface FacadeView {
    int afficherMenu();

    void afficherPatients(List<Patient> patient);

    Patient selectionnerPatient(List<Patient> lstPatient);

    void afficherMessageErreur(String msg);

    Patient saisirPatient();

    List<Repas> selectionnerRepas(List<Repas> lstRepas);

    Patient ajouterRepasPatient(List<Patient> lstPatient, List<Repas> lstRepas);

    Patient modifierPatient(Patient patient);

    void afficherPatient(Patient patient);


    void afficherMessage(String msg);
}
