package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.repaspatient.view.ViewFactory;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

import static net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory.fabriquerFacadeMetier;

public class Presenter {

    FacadeVue vue = ViewFactory.fabriquerFacadeVue();

    FacadeMetier metier = FacadeMetierFactory.fabriquerFacadeMetier();

    public void MenuPrincipal(){
        int resultat = 0;
        do {
            resultat = vue.afficherMenu();
            traitementMenuPrincipal(resultat);
        }while (resultat != 0);
    }

    private void traitementMenuPrincipal(int choix) {
        switch (choix) {
            case 1:
                listerPatient();
                break;
            case 2:
                creerPatient();
                break;
            case 3:
                modifierPatient();
                break;
            case 4:
                supprimerPatient();
                break;
            case 5:
                ajouterRepasPatient();
                break;
            default:
                break;
        }

    }

    private void listerPatient(){
        vue.afficherPatient();
    }

    private void creerPatient(){

    }

}
