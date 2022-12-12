package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.view.FacadeViewFactory;
import net.ent.etrs.repaspatient.view.FacadeVue;

/**
 * The type Presenter.
 */
public class Presenter {

    /**
     * The Fv.
     */
    FacadeVue fv = FacadeViewFactory.fabriquerFacadeView();

    private FacadeVue vue;

    private FacadeMetier metier;


    /**
     * Menu principal.
     */
    public void MenuPrincipal() {
        int result = 0;
        do {
            result = fv.afficherMenuPrincipal();
            traitementChoix(result);
        } while (result != 6);
    }


    private void traitementChoix(int choix) {
        switch (choix) {

            case 1:

            case 2:

            case 3:

            case 4:

            case 5:
                try {
                    ajouterRepasPatient();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void modifierPatient() {

    }

    private void supprimerPatient() {

    }

    /**
     * Exec.
     */
    public void exec() {

    }

    private void ajouterRepasPatient() {

    }

    private void traiterOption(int option) {

    }

    private void listerPatient() {

    }

    private void creerPatient() {

    }


}
