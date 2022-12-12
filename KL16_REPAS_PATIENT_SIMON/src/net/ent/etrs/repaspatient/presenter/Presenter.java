package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.repaspatient.view.Viewfactory;

import java.util.List;

public class Presenter {

    private static FacadeMetier metier;
    private static FacadeVue vue;

    public Presenter() {
        metier = FacadeMetierFactory.fabriquerFacadeMetier();
        vue = Viewfactory.fabriquerFacadeVue();
    }

    public void excecuter() {
        int result = 0;
        do {
            result = vue.afficherMenu();
            traiterChoix(result);
        }while (result != 0);
    }

    private void traiterChoix(final int result) {
        switch (result){
            case 1 :
                    vue.afficherPatients(metier.listerPatients());

                    break;
            case 2 :

                    //creerNouveauPatients();
                    break;
            case 3 :
                    //modifierPatient();
                    break;
            case 4 :
                    //supprimerPatient();
                    break;
            case 5 :
                    //ajouterRepasPatient();
                    break;
            default:
                break;
        }
    }




}
