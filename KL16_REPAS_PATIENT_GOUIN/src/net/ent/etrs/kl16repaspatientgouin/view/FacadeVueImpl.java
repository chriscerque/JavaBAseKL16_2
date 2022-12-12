package net.ent.etrs.kl16repaspatientgouin.view;

import net.ent.etrs.kl16repaspatientgouin.commons.utils.AffichageConsole;
import net.ent.etrs.kl16repaspatientgouin.commons.utils.LectureConsole;
import net.ent.etrs.kl16repaspatientgouin.model.entities.Patient;
import net.ent.etrs.kl16repaspatientgouin.model.entities.Repas;
import net.ent.etrs.kl16repaspatientgouin.view.exceptions.ViewException;
import net.ent.etrs.kl16repaspatientgouin.view.references.ConstantesVue;


import java.util.Arrays;
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
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesVue.MENU), "Repas Patient");
        return LectureConsole.lectureChoixInt(0,ConstantesVue.MENU.length);
    }

    @Override
    public void afficherPatient(Patient patient) {
        AffichageConsole.afficherMessageAvecSautLigne(String.format("Patient : %s %s %s ", patient.getNumSecu(), patient.getNom(), patient.getPrenom()));
    }

    @Override
    public void afficherPatients(List<Patient> lstPatients) {
        for(Patient patient : lstPatients){
            afficherPatient(patient);
            if(patient.getLstRegimeAlimentaire().size() > 0){

            }
        }
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
