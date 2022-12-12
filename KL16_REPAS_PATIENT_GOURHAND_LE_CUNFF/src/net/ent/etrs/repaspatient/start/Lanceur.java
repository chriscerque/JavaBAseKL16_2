package net.ent.etrs.repaspatient.start;

import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.presenter.Presenter;
import net.ent.etrs.repaspatient.view.ViewFactory;

public class Lanceur {
    public static void main(String[] args) {
        Presenter p = new Presenter(ViewFactory.fabriquerFacadeVue(), FacadeMetierFactory.fabriquerFacaMetier());
        p.exec();
    }
}
