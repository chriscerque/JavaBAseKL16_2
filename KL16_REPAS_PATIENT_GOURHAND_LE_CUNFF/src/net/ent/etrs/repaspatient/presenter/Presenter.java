package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

import java.util.List;

public class Presenter {
    private FacadeVue vue;
    private FacadeMetier metier;

    public Presenter(FacadeVue vue, FacadeMetier metier) {
        this.vue = vue;
        this.metier = metier;
    }

    private void modifierPatient() {
        try {
            metier.mettreAJourPatient(vue.selectionnerPatient(metier.listerPatients()));
        } catch (BusinessException | ViewException e) {
            AffichageConsole.afficherErreur(e.getMessage());
        }
    }

    private void supprimerPatient(){
        try {
            metier.supprimerPatient(vue.selectionnerPatient(metier.listerPatients()));
        } catch (BusinessException | ViewException e) {
            AffichageConsole.afficherErreur(e.getMessage());
        }
    }

    public void exec() {
        try {
            metier.init();
            int option = 0;
            do {
                option = vue.afficherMenu();
                taiterOption(option);
            } while (option != 0);
        } catch (BusinessException e) {
            AffichageConsole.afficherErreur(e.getMessage());
        }

    }

    private void ajouterRepasPatient() {
        try {
            Patient patient = vue.selectionnerPatient(metier.listerPatients());
            List<Repas> lstRepas = vue.selectionnerRepas(metier.listerRepas());
            for (Repas repas :
                    lstRepas) {
                patient.ajouterRepas(repas);
            }
            metier.mettreAJourPatient(patient);
        } catch (BusinessException | ViewException | PatientException e) {
            AffichageConsole.afficherErreur(e.getMessage());
        }
    }

    private void taiterOption(int option) {
        switch (option) {
            case 1:
                listerPatients();
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

    private void listerPatients() {
        vue.afficherPatients(metier.listerPatients());
    }

    private void creerPatient() {
        try {
            metier.sauvegarderPatient(vue.selectionnerPatient(metier.listerPatients()));
        } catch (BusinessException | ViewException e) {
            AffichageConsole.afficherErreur(e.getMessage());
        }
    }
}
