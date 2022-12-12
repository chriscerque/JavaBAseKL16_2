package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FacadeVueImpl implements FacadeVue{
    @Override
    public void afficherMessage(final String msg) {
        AffichageConsole.afficherMessageAvecSautLigne(msg);
    }

    @Override
    public void afficherMessageErreur(final String msg) {
        AffichageConsole.afficherErreur(msg);

    }

    @Override
    public int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesVue.MENU),"Menu");
        int choix = LectureConsole.lectureChoixInt(0,ConstantesVue.MENU.length);
        return choix;
    }

    @Override
    public void afficherPatient(final Patient patient) {

    }

    @Override
    public void afficherPatients(final List<Patient> lstPatients) {
        for (Patient p : lstPatients){
            AffichageConsole.afficherMessageAvecSautLigne("Num Sécu : "+p.getNumSecu() + "Nom :"+p.getNom()+ "Prenom : "+ p.getPrenom()+ "Date Entree : "+ p.getDateEntree());
        }

    }

    @Override
    public Patient saisirPatient() throws ViewException, PatientConstructionException {
       String numSecu =LectureConsole.lectureChaineCaracteres("Saisir votre numéro de sécu : ");
       String nom =LectureConsole.lectureChaineCaracteres("Saisir votre nom : ");
       String prenom =LectureConsole.lectureChaineCaracteres("Saisir votre prenom : ");
       LocalDate dateEntree = LectureConsole.lectureLocalDate("Veuillez saisir la date d'entrée : ") ;

       return EntitiesFactory.fabriquerPatient(numSecu,nom,prenom,dateEntree);
    }

    @Override
    public Patient selectionnerPatient(final List<Patient> lstPatients) {
        return null;
    }

    @Override
    public List<Repas> selectionnerRepas(final List<Repas> lstRepas) {
        return null;
    }

    @Override
    public Patient modifierPatient(final Patient patient) throws ViewException {
        return null;
    }

    @Override
    public Patient ajouterRepasPatient(final List<Patient> lstPatients, final List<Repas> listRepas) {
        return null;
    }

}
