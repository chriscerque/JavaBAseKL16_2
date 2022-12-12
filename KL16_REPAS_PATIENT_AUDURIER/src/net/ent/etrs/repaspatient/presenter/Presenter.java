package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

/**
 * Classe représentant le PRESENTER de l'application
 * selon le paradigme MVP.
 */

public class Presenter {
    FacadeVue fv;
    FacadeMetier fm;

    public Presenter(final FacadeVue fv, final FacadeMetier fm) {
        this.fv = fv;
        this.fm = fm;
        try {
            fm.init();
        } catch (BusinessException e) {
            fv.afficherMessageErreur(e.getMessage());
        }
    }

    public void MenuPrincipal() {
        int result = 0;
        do {
            result = fv.afficherMenu();
            traitementChoix(result);
        } while (result != 0);
    }

    private void traitementChoix(final int choix) {
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

    private void ajouterRepasPatient() {
        try {
            fm.sauvegarderPatient(fv.ajouterRepasPatient(fm.listerPatients(), fm.listerRepas()));
        } catch (BusinessException |
                 ViewException e) {
            fv.afficherMessageErreur(e.getMessage());
        }
    }

    private void supprimerPatient() {
        try {
            Patient patient = fv.selectionnerPatient(fm.listerPatients());
            fm.supprimerPatient(patient);
            fv.afficherMessage(String.format(ConstantesVue.MSG_PATIENT_SUPPRESSION, patient.getNom(), patient.getPrenom()));
        } catch (BusinessException e) {
            fv.afficherMessageErreur(e.getMessage());
        }
    }

    private void modifierPatient() {
        try {
            fm.sauvegarderPatient(fv.modifierPatient(fv.selectionnerPatient(fm.listerPatients())));
        } catch (BusinessException |
                 ViewException e) {
            fv.afficherMessageErreur(e.getMessage());
        }
    }

    private void creerPatient() {
        try {
            fm.sauvegarderPatient(fv.saisirPatient());
        } catch (BusinessException |
                 ViewException e) {
            fv.afficherMessageErreur(e.getMessage());
        }
    }

    private void listerPatient() {
        try {
            fv.afficherPatients(fm.listerPatients());
        } catch (ViewException e) {
            fv.afficherMessageErreur(e.getMessage());
        }
    }
}
