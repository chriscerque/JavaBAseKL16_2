package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.view.FacadeView;
import net.ent.etrs.repaspatient.view.FacadeViewFactory;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.util.ArrayList;
import java.util.List;

public class Presenter {

    private static FacadeMetier fm;

    private static FacadeView fv;

    public static void MenuPrincipal() {

        fm = FacadeMetierFactory.fabriquerFacadeMetier();

        fv = FacadeViewFactory.fabriquerFacadeView();

        int result;
        do {
            result = fv.AffichageMenu();
            traitementChoix(result);
        } while (result != 0);

    }

    private static void traitementChoix(int choix) {
        switch (choix) {
            case 1:
                listerPatient();
                break;
            case 2:
                CreerPatient();
                break;
            case 3:

                break;
            case 4:
                SupprimerPatient();
                break;
            case 5:

                break;
            default:
                break;
        }

    }

    private static void SupprimerPatient() {
        List<Patient> lstVoiture = getLstPatient();
        try {
            if (lstVoiture.size() != 0) {
                fm.SupprimerPatient(selectionnerVoiture(lstVoiture));
                fv.AffichageConstante(ConstantesVue.MSG_PATIENT_SUPPRESSION);
            } else {
                fv.AffichageConstante(ConstantesVue.MSG_PATIENT_NON_SUPPRIMEE);
            }
        } catch (BusinessException e) {
            fv.AfficherErreur(e.getMessage());
        }
    }

    private static String selectionnerVoiture(List<Patient> lstPatient) {
        List<String> lstPatientAffichage = new ArrayList<>();
        for (Patient p : lstPatient) {
            lstPatientAffichage.add(String.format("%-20s | %-20s | %-15s | %s", p.getDateEntree(), p.getNumSecu(), p.getNom(), p.getPrenom()));
        }
        fv.AffichageEnteteListePatient();
        return lstPatient.get(LectureConsole.lectureChoixInt(0, lstPatientAffichage.size()) - 1).getNumSecu();
    }

    private static void CreerPatient() {
        fv.SaisirInfoPatient();
        //validerCreerPatient();
    }

//    private static void validerCreerPatient(){
//        try {
//            Patient patient =  fm.sauvegarderPatient();
//
//            fv.AffichageListePatient();
//        } catch (BusinessException e) {
//            fv.AfficherErreur(e.getMessage());
//        }
//    }

    private static void listerPatient() {
        List<Patient> lstPatient = getLstPatient();
        if (lstPatient.size() != 0) {
            fv.AffichageEnteteListePatient();
            for (Patient p : lstPatient) {
                afficherPatient(p);
            }
        } else {
            fv.AffichageConstante(ConstantesVue.MSG_AUCUN_PATIENT);
        }
    }

    private static void afficherPatient(Patient p) {
        fv.AffichageListePatient(p);

    }

    private static List<Patient> getLstPatient() {
        return fm.listerPatients();
    }


}
