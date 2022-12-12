package net.ent.etrs.repaspatient.start;

import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.presenter.Presenter;
import net.ent.etrs.repaspatient.view.FacadeViewFactory;

public class Lanceur {
    public static void main(String[] args) {
        Presenter p = new Presenter(FacadeViewFactory.fabriquerFacadeView(), FacadeMetierFactory.fabriquerFacadeMetier());
        p.MenuPrincipal();
    }
}
