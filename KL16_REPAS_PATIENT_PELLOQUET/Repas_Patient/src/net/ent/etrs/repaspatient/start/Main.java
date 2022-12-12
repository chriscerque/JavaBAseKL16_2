package net.ent.etrs.repaspatient.start;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.presenter.Presenter;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.repaspatient.view.ViewFactory;

public class Main {
    public static void main(String[] args) {
        FacadeMetier metier = FacadeMetierFactory.fabriquerFacadeMetier();
        FacadeVue vue = ViewFactory.fabriquerFacadeVue();
        try {
            metier.init();
        } catch (BusinessException e) {
            AffichageConsole.afficherErreur(e.getMessage());
        }
        Presenter lanceur = new Presenter(metier, vue);
        lanceur.exec();
    }
}