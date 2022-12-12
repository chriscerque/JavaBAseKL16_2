package net.ent.etrs.kl16repaspatientgouin.presenter;

import net.ent.etrs.kl16repaspatientgouin.model.facades.FacadeMetier;
import net.ent.etrs.kl16repaspatientgouin.view.FacadeVue;

public class Presenter {
    private FacadeVue vue;

    private FacadeMetier metier;

    public Presenter(FacadeVue vue, FacadeMetier metier) {
        this.vue = vue;
        this.metier = metier;
    }

    private void modifierPatient(){


    }

    private void supprimerPatient(){

    }

    private void ajouterRepasPatient(){

    }

    private void traiterOption(){

    }

    private void listerPatient(){
        vue.afficherPatients(metier.listerPatients());
    }

    private void creerPatient(){

    }

}
