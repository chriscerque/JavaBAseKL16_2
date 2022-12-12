package net.ent.etrs.repaspatient.start;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.presenter.Presenter;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

public class Lanceur {

    /**
     * Permet de lancer le projet.
     * @param args
     * @throws BusinessException
     * @throws ViewException
     * @throws DaoException
     * @throws RegimeAlimentaireException
     */
    public static void main(String[] args) throws BusinessException, ViewException, DaoException, RegimeAlimentaireException {
        Presenter presenter = new Presenter();
        presenter.executer();

    }


}
