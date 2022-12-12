package net.ent.etrs.test.presenter;

import net.ent.etrs.test.model.facades.FacadeMetier;
import net.ent.etrs.test.model.facades.FacadeMetierFactory;
import net.ent.etrs.test.model.facades.exceptions.BusinessException;
import net.ent.etrs.test.view.FacadeViewFactory;
import net.ent.etrs.test.view.FacadeVue;
import net.ent.etrs.test.view.exceptions.ViewException;
import net.ent.etrs.test.view.references.ConstantesVue;

public class Presenter {

    private FacadeVue vue;

    private FacadeMetier metier;

    public Presenter(final FacadeVue vue, final FacadeMetier metier) {
        this.vue = vue;
        this.metier = metier;
    }

    public void exec(){
        try {
            metier.init();
            int choix = 0;
            do{
                choix = vue.afficherMenu();
                traiterOption(choix);
            }while(choix != 0);
        } catch (BusinessException e) {
            vue.afficherMessageErreur(e.getMessage());
        }

    }

    private void traiterOption(int choix) {
        switch(choix){
            case 1 : listerPatient();
                break;
            case 2 : creerPatient();
                break;
            case 3 : modifierPatient();
                break;
            case 4 : supprimerPatient();
                break;
            case 5 : ajouterRepasPatient();
                break;
            case 0 :
                break;
        }
    }

    private void ajouterRepasPatient() {
        try {
            metier.mettreAJourPatient(vue.ajouterRepasPatient(metier.listerPatients(), metier.listerRepas()));
            vue.afficherMessage(ConstantesVue.MSG_PATIENT_MISE_A_JOUR);
        } catch (BusinessException | ViewException e) {
            vue.afficherMessageErreur(e.getMessage());
        }
    }

    private void supprimerPatient() {
        try {
            metier.supprimerPatient(vue.selectionnerPatient(metier.listerPatients()));
            vue.afficherMessage(ConstantesVue.MSG_PATIENT_SUPPRESSION);
        } catch (BusinessException e) {
            vue.afficherMessageErreur(e.getMessage());
        }
    }

    private void modifierPatient() {
        try {
            metier.mettreAJourPatient(vue.modifierPatient(vue.selectionnerPatient(metier.listerPatients())));
            vue.afficherMessage(ConstantesVue.MSG_PATIENT_MISE_A_JOUR);
        } catch (ViewException | BusinessException e) {
            vue.afficherMessageErreur(e.getMessage());
        }
    }

    private void creerPatient() {
        try {
            metier.sauvegarderPatient(vue.saisirPatient());
            vue.afficherMessage(ConstantesVue.MSG_PATIENT_CREATION);
        } catch (ViewException | BusinessException e) {
            vue.afficherMessageErreur(e.getMessage());
        }
    }

    private void listerPatient() {
        try {
            vue.afficherPatients(metier.listerPatients());
        } catch (BusinessException e) {
            vue.afficherMessageErreur(e.getMessage());
        }
    }

}
