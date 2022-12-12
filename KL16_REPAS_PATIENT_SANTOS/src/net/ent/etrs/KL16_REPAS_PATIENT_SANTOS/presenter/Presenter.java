package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.presenter;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.commons.utils.AffichageConsole;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.commons.utils.LectureConsole;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.EntitiesFactory;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.Patient;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.facades.FacadeMetierFactory;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.facades.exceptions.BusinessException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.facades.exceptions.FacadeMetier;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.view.facades.FacadeViewFactory;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.view.facades.FacadeVue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Presenter {

    FacadeVue fv = FacadeViewFactory.fabriquerFacadeView();
    static FacadeMetier fm = FacadeMetierFactory.fabriquerFacadeMetier();

    private static void traitementOption(int choix) {
        switch (choix) {
            case 1:
                listerPatient();
                break;
            case 2:
                creerPatient();
                break;
            case 3:
                modifierPatient();
                break;
            case 4:
                supprimerPatient();
                break;
            case 5:
                ajouterRepasPatient();
                break;

            default:
                break;
        }

    }

    private static void ajouterRepasPatient() {
        listerPatient();
        AffichageConsole.afficherMessageAvecSautLigne("Saisir l'id d'un patient pour ajouter repas");
        String id = String.valueOf(LectureConsole.lectureChaineCaracteres());




    }

    private static void supprimerPatient() {
        listerPatient();
        AffichageConsole.afficherMessageAvecSautLigne("Saisir id patient a supprimer");
        try {
            fm.supprimerPatient(LectureConsole.lectureChaineCaracteress());
            AffichageConsole.afficherMessageAvecSautLigne("Patient Supprimé");
        } catch ( BusinessException e) {
            AffichageConsole.afficherMessageAvecSautLigne(e.getMessage());
        }
    }


    private static void modifierPatient() {
    }

    private static void creerPatient() {
        String nom = LectureConsole.lectureChaineCaracteres("Saisir nom : ");
        String prenom = LectureConsole.lectureChaineCaracteres("Saisir prenom: ");
        String numSecu = LectureConsole.lectureChaineCaracteres("Saisir num secu: ");
        LocalDate dateEntre = LectureConsole.lectureLocalDate("saisir date entrer : ","");


        EntitiesFactory.fabriquerPatient(nom, prenom, numSecu, dateEntre);

    }


    private static void listerPatient() {
        List<String> afficherLesPatient = new ArrayList<>();
        for (Patient patient :fm.listerPatients()) {
            afficherLesPatient.add(String.format("Patient  : %s", patient.getNumSecu()));
        }
        AffichageConsole.afficherMenuSimple(afficherLesPatient);
    }
    public void MenuPrincipal() {
        int result = 0;
        do {
            result = fv.afficherMenu();
            traitementOption(result);
        } while (result != 0);

    }
    }



