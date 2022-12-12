package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.repaspatient.view.FacadeVueFactory;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

public class Presenter {

    /**
     * Classe représentant le PRESENTER de l'application
     * selon le paradigme MVP.
     */

        private FacadeVue fv;
        private FacadeMetier fm;

        public Presenter() {
            fv = FacadeVueFactory.fabriquerFacadeVue();
            fm = FacadeMetierFactory.fabriquerFacadeMetier();
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
                case 1 : listerPatient();
                    break;
                case 2 : creerPatient();
                    break;
                case 3 : ;
                    break;
                case 4 : ;
                    break;
                case 5 : ajouterRepas();
                    break;
                default:
                    break;
            }
        }

    private void ajouterRepas() {
        try {
            fv.ajouterRepasPatient(fm.listerPatients(), fm.listerRepas());
        } catch (ViewException e) {
            fv.afficherMessageErreur(e);
        }
    }


    private void creerPatient() {
        try {
            fv.saisirPatient();
        } catch (ViewException e) {
            throw new RuntimeException(e);
        }
    }

    private void listerPatient() {
        try {
            fv.afficherPatients(fm.listerPatients());
        } catch (ViewException e) {
            fv.afficherMessageErreur(e);
        }
    }


}
