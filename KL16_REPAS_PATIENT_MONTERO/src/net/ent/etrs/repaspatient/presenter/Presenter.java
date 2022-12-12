package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.presenter.references.ConstantesPresenter;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.repaspatient.view.FacadeVueFactory;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

public class Presenter {

    private static FacadeMetier facadeMetier;
    private static FacadeVue facadeVue;

    public Presenter() {
        facadeMetier = FacadeMetierFactory.fabriquerFacadeMetier();
        facadeVue = FacadeVueFactory.fabriquerFacadeVue();

        //initialisation des donnees
        try {
            facadeMetier.init();
        } catch (BusinessException e) {
            facadeVue.afficherMessageErreur(e.getMessage());
        }
    }

    public void excecuter() {
        int choix = 0;
        do {
            // afficher le menu
            choix = facadeVue.afficherMenu();
            // traiter les options du menu
            traiterOption(choix);

        } while (choix != 0);
    }


    private void traiterOption(final int choix) {
        switch (choix) {
            case 0:
                facadeVue.afficherMessage(ConstantesPresenter.FIN);
                break;
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
            facadeMetier.mettreAJourPatient(facadeVue.ajouterRepasPatient(facadeMetier.listerPatients(), facadeMetier.listerRepas()));
        } catch (BusinessException | ViewException e) {
            facadeVue.afficherMessageErreur(e.getMessage());
        }

    }

    private void supprimerPatient() {
        try {
            //decompose la fonction pour recuperer les donnees du patient afin de lafficher
            Patient p = facadeVue.selectionnerPatient(facadeMetier.listerPatients());
            facadeMetier.supprimerPatient(p);
            //facadeMetier.supprimerPatient(facadeVue.selectionnerPatient(facadeMetier.listerPatients()));
            facadeVue.afficherMessage(String.format(ConstantesVue.MSG_PATIENT_SUPPRESSION, p.getNom(), p.getPrenom()));
        } catch (BusinessException e) {
            facadeVue.afficherMessageErreur(e.getMessage());
        }
    }

    private void modifierPatient() {
        try {
            Patient p = facadeVue.selectionnerPatient(facadeMetier.listerPatients());
            facadeVue.modifierPatient(p);
            facadeMetier.mettreAJourPatient(p);
        } catch (BusinessException | ViewException e) {
            facadeVue.afficherMessageErreur(e.getMessage());
        }
    }

    private void creerPatient() {
        try {
            Patient p = facadeVue.saisirPatient();
            facadeMetier.sauvegarderPatient(p);
            //facadeMetier.sauvegarderPatient(facadeVue.saisirPatient());
            facadeVue.afficherMessage(String.format(ConstantesVue.MSG_PATIENT_CREATION, p.getNom(), p.getPrenom()));
        } catch (BusinessException | ViewException e) {
            facadeVue.afficherMessageErreur(e.getMessage());
        }
    }

    private void listerPatient() {
        facadeVue.afficherPatients(facadeMetier.listerPatients());
    }


}