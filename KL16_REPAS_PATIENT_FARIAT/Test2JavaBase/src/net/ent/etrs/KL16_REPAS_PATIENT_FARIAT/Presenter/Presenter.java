package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Presenter;

import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.excetions.PatientMemDaoException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.Patient;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.facades.FacadeMetier;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.facades.FacadeMetierFactory;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.facades.exceptions.BusinessException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.views.exceptions.FacadeViewException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.views.exceptions.ViewException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.views.facades.FacadeView;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.views.facades.FacadeViewFactory;

import java.util.List;

public class Presenter {
    FacadeView facadeView = FacadeViewFactory.fabriquerFacadeView();
    FacadeMetier facadeMetier = FacadeMetierFactory.fabriquerFacadeMetier();

    public void MenuPrincipal() throws BusinessException, PatientMemDaoException, ViewException {
        int result = 0;
        do {
            result = facadeView.afficherMenu();
            traitementChoix(result);
        } while (result != 0);

    }

    private void traitementChoix(int choix) throws ViewException, BusinessException, PatientMemDaoException, FacadeViewException {
        switch (choix) {
            case 1://Lister patient
                try {
                    facadeView.afficherPatients(facadeMetier.listerPatients());
                } catch (Exception e) {
                    throw new FacadeViewException()Exception(e);
                }
                break;
            case 2://Creer patient
                facadeMetier.sauvegarderPatient(facadeView.saisirPatient());
                break;
            case 3://Modifier patient
                facadeMetier.mettreAJourPatient(facadeView.modifierPatient());
                break;
            case 4://Supprimer patient
                List<Patient> lst = facadeMetier.listerPatients();
                if (lst.size() != 0) {
                    //demander vehicule
                    String id = facadeView.selectionnerPatient(lst);
                    //supprimer vehicule
                    facadeMetier.supprimerPatient(id);
                    //suppression succes
                    facadeView.succesOperation();
                } else {
                    facadeMetier.afficherPatient();
                }


        }
    }
}
