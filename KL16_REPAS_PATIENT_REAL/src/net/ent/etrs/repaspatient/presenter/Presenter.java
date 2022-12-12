package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;
import net.ent.etrs.repaspatient.model.facades.FacadeFactory;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.repaspatient.view.FacadeVueFactory;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

import java.util.ArrayList;
import java.util.List;

public class Presenter {

    /**
     * Constantes
     */
    FacadeVue fv = FacadeVueFactory.fabriquerFacadeVue();
    FacadeMetier fm = FacadeFactory.fabriquerFacadeMetier();


    /**
     * Methode qui permet d'executer en mode console un menu et c'est différents choix.
     * @throws BusinessException
     * @throws ViewException
     * @throws DaoException
     * @throws RegimeAlimentaireException
     */
    public void executer() throws BusinessException, ViewException, DaoException, RegimeAlimentaireException {
        fm.init();
        int result = 0;
        do {
            result = fv.afficherMenu();
            traitementChoix(result);
        } while (result != 0);
    }

    /**
     * Permet le traitement de la méthode executer.
     * @param choix
     * @throws ViewException
     * @throws BusinessException
     * @throws DaoException
     * @throws RegimeAlimentaireException
     */

    private void traitementChoix(int choix) throws ViewException, BusinessException, DaoException, RegimeAlimentaireException {
        switch (choix){
            case 1 :
                fv.afficherPatients(fm.listerPatients());
                break;
            case 2 :
                fm.sauvegarderPatient(fv.saisirPatient());
                break;
            case 3 :
                fm.mettreAJourPatient(fv.modifierPatient(fv.selectionnerPatient(fm.listerPatients())));
                break;
            case 4:
                Patient p = fv.selectionnerPatient(fm.listerPatients());
                fm.supprimerPatient(p.getNom());
                fv.afficherMessage(p.getNom() + " Supprimer");
                break;
            case 5 :
                break;
        }
    }



}
