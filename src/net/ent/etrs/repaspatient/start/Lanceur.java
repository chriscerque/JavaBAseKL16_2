package net.ent.etrs.repaspatient.start;

import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.presenter.Presenter;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.repaspatient.view.Viewfactory;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Lanceur {

    private Lanceur() {
    }

    public static void main(String[] args) {
        try {
            FacadeMetier metier = FacadeMetierFactory.fabriquerFacadeMetier();
            FacadeVue vue = Viewfactory.fabriquerFacadeVue();
            metier.init();
            Presenter presenter = new Presenter(metier, vue);
            presenter.exec();
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }

}
