package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

import java.util.List;

public class Presenter {

    private FacadeMetier metier;
    private FacadeVue vue;

    public Presenter(FacadeMetier metier, FacadeVue vue) {
        this.metier = metier;
        this.vue = vue;
    }

    private void modifierPatient(){
        //TODO
    }

    private void supprimerPatient(){
        try {
            metier.supprimerPatient(vue.selectionnerPatient(metier.listerPatients()));
        } catch (BusinessException e) {
            vue.afficherMessage(e.getMessage());
        }
    }

    public void exec(){
        int choix;
        do {
            choix = vue.afficherMenu();
            traiterOption(choix);
        } while (choix != 0);
    }

    private void ajouterRepasPatient() {
        //TODO
        vue.ajouterRepasPatient(metier.listerPatients(), vue.selectionnerRepas(metier.listerRepas()));
    }

    private void traiterOption(int choix) {
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

    private void listerPatient() {
        vue.afficherPatients(metier.listerPatients());
    }

    private void creerPatient() {
        try {
            metier.sauvegarderPatient(vue.saisirPatient());
        } catch (BusinessException | ViewException e) {
            vue.afficherMessage(e.getMessage());
        }
    }
}
