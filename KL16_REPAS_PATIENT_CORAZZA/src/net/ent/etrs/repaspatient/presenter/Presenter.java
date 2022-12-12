package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierImpl;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.repaspatient.view.FacadeVueImpl;
import net.ent.etrs.repaspatient.view.ViewFactory;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

import java.lang.reflect.Modifier;

public class Presenter {
    private FacadeMetier metier;
    private FacadeVue vue;

    public Presenter(FacadeMetier metier, FacadeVue vue) {
        this.metier = metier;
        this.vue = vue;
    }

    public void exec() throws BusinessException, PatientException, ViewException {
        int choix = vue.afficherMenu();
        traiterOption(choix);
    }

    private void traiterOption(int choix) throws BusinessException, PatientException, ViewException {
        switch (choix){
            case 1 :
                listerPatient();
                break;
            case 2 :
                creerPatient();
                break;
            case 3 :
                modifierPatient();
                break;
            case 4 :
                supprimerPatient();
                break;
            case 5 :
                ajouterRepasPatient();
                break;
            default:
        }


    }

    private void modifierPatient() throws ViewException, BusinessException, PatientException {
        vue.afficherPatients(metier.listerPatients());
        Patient p = vue.saisirPatient();
        metier.mettreAJourPatient(p);
    }

    private void supprimerPatient() throws BusinessException {
        Patient p =  vue.selectionnerPatient(metier.listerPatients());
        metier.supprimerPatient(p);
    }

    private void ajouterRepasPatient(){
        vue.ajouterRepasPatient(metier.listerPatients(),metier.listerRepas());
    }

    private void listerPatient(){
        vue.afficherPatients(metier.listerPatients());
    }

    private void creerPatient() throws ViewException, BusinessException, PatientException {
        Patient p = vue.saisirPatient();
        metier.sauvegarderPatient(p);
    }



}
