package net.ent.etrs.repaspatient.start;

import net.ent.etrs.repaspatient.presenter.Presenter;
import net.ent.etrs.repaspatient.presenter.PresenterFactory;

public class Lanceur {
    //region ATTRIBUTS
    private static Presenter p = PresenterFactory.fabriquerPresenter();

    //endregion
    //region MÉTHODES
    public static void main(String[] args) {
        p.exec();
    }
    //endregion
}
