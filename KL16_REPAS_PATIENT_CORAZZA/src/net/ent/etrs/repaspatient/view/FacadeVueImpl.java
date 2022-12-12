package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

import java.util.List;

public class FacadeVueImpl implements FacadeVue{
    @Override
    public void afficherMessage(String msg) {

    }

    @Override
    public void afficherMessageErreur(String msg) {

    }

    @Override
    public int afficherMenu() {
        return 0;
    }

    @Override
    public void afficherPatient(Patient patient) {

    }

    @Override
    public void afficherPatients(List<Patient> lstPatients) {

    }

    @Override
    public Patient saisirPatient() throws ViewException {
        return null;
    }

    @Override
    public Patient selectionnerPatient(List<Patient> lstPatients) {
        return null;
    }

    @Override
    public List<Repas> selectionnerRepas(List<Repas> lstRepas) {
        return null;
    }

    @Override
    public Patient modifierPatient(Patient patient) throws ViewException {
        return null;
    }

    @Override
    public Patient ajouterRepasPatient(List<Patient> lstPatients, List<Repas> listRepas) {
        return null;
    }
}
