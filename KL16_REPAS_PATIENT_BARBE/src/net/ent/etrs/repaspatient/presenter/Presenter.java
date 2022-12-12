package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.facades.FacadeVue;
import net.ent.etrs.repaspatient.view.facades.FacadeVueFactory;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

public class Presenter {

    FacadeVue facadeVue = FacadeVueFactory.fabriquerFacadeVue();
    FacadeMetier facadeMetier = FacadeMetierFactory.fabriquerFacadeMetier();

    public void MenuPrincipal() {
        int result = 0;
        do {
            result = facadeVue.afficherMenu();
            traitementChoix(result);
        } while (result != 0);

    }

    private void traitementChoix(int choix) {
        switch (choix) {
            case 1:
                // Lister les patients
                facadeVue.afficherPatients(facadeMetier.listerPatients());
                break;

            case 2:
                // Créer un nouveau patient
                try {
                    facadeMetier.sauvegarderPatient(facadeVue.saisirPatient());
                } catch (BusinessException | ViewException e) {
                    facadeVue.afficherMessageErreur(e.getMessage());
                }
                break;

            case 3:
                // Modifier un patient
                try {
                    facadeMetier.mettreAJourPatient(facadeVue.selectionnerPatient(facadeMetier.listerPatients()));
                } catch (BusinessException | ViewException e) {
                    facadeVue.afficherMessageErreur(e.getMessage());
                }
                break;

            case 4:
                // Ajouter un repas à un patient
                try {
                    Patient patient = null;
                    patient = facadeVue.ajouterRepasPatient(facadeMetier.listerPatients(), facadeMetier.listerRepas());
                    facadeVue.afficherMessage(ConstantesVue.MSG_REPAS_AJOUTE + patient.getNumSecu());
                } catch (ViewException e) {
                    facadeVue.afficherMessageErreur(e.getMessage());
                }
                break;

            default:
                break;
        }

    }


}
