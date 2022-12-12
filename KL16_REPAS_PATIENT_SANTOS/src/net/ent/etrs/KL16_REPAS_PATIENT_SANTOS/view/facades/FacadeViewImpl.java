package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.view.facades;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.commons.utils.AffichageConsole;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.commons.utils.LectureConsole;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.Patient;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.Repas;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references.ConstantesMetier;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.view.facades.exception.ViewException;

import java.util.ArrayList;
import java.util.List;

public class FacadeViewImpl implements  FacadeVue{
    @Override
    public int afficherMenu() {
        List<String> initMenu = new ArrayList<>(initMenuPrincipal());
        AffichageConsole.afficherMenuSimple(initMenu);
        int choix = LectureConsole.lectureChoixInt(0,initMenu.size());
        return choix;
    }

    @Override
    public List<String> initMenuPrincipal() {
        List<String> retour = new ArrayList<>();
        retour.add("Lister les Patient ");
        retour.add("Céer un nouveau patient");
        retour.add("modifier un patient");
        retour.add("Supprimer un patient");
        retour.add("Ajouter un repas");

        return retour;

    }

    @Override
    public Patient selectionnerPatient(List<Patient> patients) throws ViewException {

        if(patients.size() == 0){
            throw new ViewException(ConstantesMetier.FACADE_VIEW_PATIENT_NULL);
        }
        List<String> pattient = new ArrayList<>();
        for (Patient patient : patients) {
            pattient.add(patient.toString());
        }
        AffichageConsole.afficherMenuSimple(pattient);
        int choix = LectureConsole.lectureChoixInt(1,pattient.size());
        Patient patient = patients.get(choix-1);

        return patient;
    }

    @Override
    public Repas selectionnerRepas(List<Repas> repas) throws ViewException {
        if(repas.size() == 0){
            throw new ViewException(ConstantesMetier.FACADE_VIEW_REPAS);
        }
        List<String> reppas = new ArrayList<>();
        for (Repas repas1 : repas) {
            reppas.add(repas1.toString());
        }
        AffichageConsole.afficherMenuSimple(reppas);
        int choix = LectureConsole.lectureChoixInt(1,reppas.size());
        Repas repas1 = repas.get(choix-1);

        return repas1;


    }

    @Override
    public Patient afficherPatient(List<Patient> patients) throws ViewException {
        return null;
    }

    @Override
    public void afficherErreur(String message) {
        AffichageConsole.afficherErreur(message);

    }

    @Override
    public void afficherMessage(String msg) {
        AffichageConsole.afficherMessageAvecSautLigne(msg);

    }
}
