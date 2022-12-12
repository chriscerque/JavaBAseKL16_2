package net.ent.etrs.repaspatient.view.facades;


import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

import java.util.List;

public interface FacadeVue {


    void afficherMessage(String msg);


    void afficherMessageErreur(String msg);


    int afficherMenu();


    void afficherPatient(Patient patient);


    void afficherPatients(List<Patient> lstPatients);


    Patient saisirPatient() throws ViewException;


    Patient selectionnerPatient(List<Patient> lstPatients) throws ViewException;

//    void afficherRepas(Patient c);


    List<Repas> selectionnerRepas(List<Repas> lstRepas) throws ViewException;

    Patient modifierPatient(Patient patient) throws ViewException;


    Patient ajouterRepasPatient(List<Patient> lstPatients, List<Repas> listRepas) throws ViewException;
}
