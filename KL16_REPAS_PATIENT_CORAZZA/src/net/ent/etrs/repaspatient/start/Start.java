package net.ent.etrs.repaspatient.start;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.presenter.Presenter;
import net.ent.etrs.repaspatient.view.ViewFactory;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

public class Start {

    public static void main(String[] args) throws BusinessException, PatientException, ViewException {
        Presenter p = new Presenter(FacadeMetierFactory.fabriquerFacadeMetier(), ViewFactory.fabriquerFacadeVue());
        p.exec();
    }
}
