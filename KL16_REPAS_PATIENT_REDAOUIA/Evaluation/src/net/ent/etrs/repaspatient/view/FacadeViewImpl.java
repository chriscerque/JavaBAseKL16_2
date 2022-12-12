package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.ArrayList;
import java.util.List;

public class FacadeViewImpl implements FacadeView{
    @Override
    public int afficherMenu() {
        List<String> initMenu = new ArrayList<>(initMenuPrincipal());
        AffichageConsole.afficherMenuSimple(initMenu);
        int choix = LectureConsole.lectureChoixInt(0,initMenu.size());
        return choix;
    }

    @Override
    public void afficherPatients(List<Patient> patients) {
        for (Patient p : patients){
            p.toString();
        }
    }

    @Override
    public Patient selectionnerPatient(List<Patient> lstPatient) {
        return null;
    }

    @Override
    public void afficherMessageErreur(String msg) {
        AffichageConsole.afficherErreur(msg);
    }

    @Override
    public Patient saisirPatient() {
       return null;

    }

    @Override
    public List<Repas> selectionnerRepas(List<Repas> lstRepas) {
        return null;
    }

    @Override
    public Patient ajouterRepasPatient(List<Patient> lstPatient, List<Repas> lstRepas) {
        return null;
    }

    @Override
    public Patient modifierPatient(Patient patient) {
        return null;
    }

    @Override
    public void afficherPatient(Patient patient) {

    }


    public List<String> initMenuPrincipal() {
        List<String> retour = new ArrayList<>();
        retour.add("Enregistrer un patient");
        retour.add("Modifier un patient");
        retour.add("Supprimer un patient");
        retour.add("Lister les patients");
        retour.add("Ajouter un repas à un patient");
        return retour;
    }

    public void afficherMessage(String msg){
        AffichageConsole.afficherMessageAvecSautLigne(msg);
    }




}
