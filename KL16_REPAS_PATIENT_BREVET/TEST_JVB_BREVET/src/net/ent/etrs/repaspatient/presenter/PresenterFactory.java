package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.view.FacadeVueFactory;

public class PresenterFactory {
    private PresenterFactory() {
    }

    //region MÉTHODES
    public static Presenter fabriquerPresenter() {
        return new Presenter(FacadeVueFactory.fabriquerFacadeVue(), FacadeMetierFactory.fabriquerFacadeMetier());
    }
    //endregion
}
