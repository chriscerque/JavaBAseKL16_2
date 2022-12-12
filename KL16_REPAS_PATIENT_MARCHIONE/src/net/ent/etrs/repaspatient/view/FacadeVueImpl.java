package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.model.entities.references.Patient;
import net.ent.etrs.repaspatient.model.entities.references.Repas;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

import java.util.ArrayList;
import java.util.List;

public class FacadeVueImpl implements FacadeVue{

    //------------------------------------------------
    //          Constructeur
    //------------------------------------------------

    protected FacadeVueImpl() {
    }


    @Override
    public void afficherPatients(List<Patient> lstPatients) {

    }

    @Override
    public Patient saisirPatient() throws ViewException {
        return null;
    }


    @Override
    public void afficherMessage(String msg) {

    }

    @Override
    public void afficherMessageErreur(String msg) {

    }

    /**
     * Permet d'afficher le Menu principal
     */
    @Override
    public int afficherMenuPrincipal() {
        List<String> initMenu = new ArrayList<>(initMenuPrincipal());
        net.ent.etrs.commons.utils.AffichageConsole.afficherMenuSimple(initMenu);
        int choix = net.ent.etrs.commons.utils.LectureConsole.lectureChoixInt(0,initMenu.size());
        return choix;
    }

    @Override
    public void afficherPatient(Patient patient) {

    }


    /**
     * Permet d'initialiser le menu principal
     *
     * @return la liste pour le Menu principal
     */
    @Override
    public List<String> initMenuPrincipal() {
        List<String> retour = new ArrayList<>();
        retour.add("Lister les patiens");
        retour.add("Créer un nouveau patient");
        retour.add("Modifier un patient");
        retour.add("Supprimer un patient");
        retour.add("Ajouter un repas à un patient");
        retour.add("Exit");
        return retour;
    }



    @Override
    public Patient ajouterRepasPatient(List<Patient> lstPatients, List<Repas> listRepas) {
        return null;
    }


    @Override
    public Patient selectionnerPatient(List<Patient> lstPatients) {
        return null;
    }

    @Override
    public List<Repas> selectionnerRepas(List<Repas> lstRepas) {
        return null;
    }

    @Override
    public Patient modifierPatient(Patient patient) throws ViewException {
        return null;
    }

}