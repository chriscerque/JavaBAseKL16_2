package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

public class Presenter {
    private FacadeVue vue;
    private FacadeMetier metier;
    //region CONSTRUCTEUR(S)

    protected Presenter(final FacadeVue vue, final FacadeMetier metier) {
        this.vue = vue;
        this.metier = metier;
    }

    //endregion
    //region MÉTHODES
    public void exec() {
        try {
            metier.init();
            int choix;
            do {
                choix = vue.afficherMenu();
                traiterOption(choix);
            } while (choix != 0);
        } catch (BusinessException e) {
            vue.afficherMessageErreur(e.getMessage());
        }
    }

    private void traiterOption(final int afficherMenu) {
        switch (afficherMenu) {
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
                vue.afficherMessageErreur(ConstantesVue.ANOMALIE);
        }
    }

    private void ajouterRepasPatient() {
        try {
            vue.ajouterRepasPatient(metier.listerPatients(), metier.listerRepas());
        } catch (ViewException e) {
            vue.afficherMessageErreur(e.getMessage());
        }
    }

    private void supprimerPatient() {
        try {
            vue.afficherMessage(ConstantesVue.SUPPRESSION_PATIENT);
            metier.supprimerPatient(vue.selectionnerPatient(metier.listerPatients()));
        } catch (BusinessException e) {
            vue.afficherMessageErreur(e.getMessage());
        }
    }

    private void modifierPatient() {
        try {
            metier.mettreAJourPatient(vue.modifierPatient(vue.selectionnerPatient(metier.listerPatients())));
        } catch (BusinessException | ViewException e) {
            vue.afficherMessageErreur(e.getMessage());
        }
    }

    private void creerPatient() {
        try {
            metier.sauvegarderPatient(vue.saisirPatient());
        } catch (BusinessException | ViewException e) {
            vue.afficherMessageErreur(e.getMessage());
        }
    }

    private void listerPatient() {
        vue.afficherPatients(metier.listerPatients());
    }
    //endregion
}
