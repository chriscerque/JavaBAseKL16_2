package net.ent.etrs.test.start;

import net.ent.etrs.test.model.facades.FacadeMetierFactory;
import net.ent.etrs.test.presenter.Presenter;
import net.ent.etrs.test.view.FacadeViewFactory;

public class Lanceur {

    public static void main(String[] args) {
        Presenter presenter = new Presenter(FacadeViewFactory.fabriquerFacadeVue(), FacadeMetierFactory.fabriquerFacadeMetier());
        presenter.exec();
    }

}
