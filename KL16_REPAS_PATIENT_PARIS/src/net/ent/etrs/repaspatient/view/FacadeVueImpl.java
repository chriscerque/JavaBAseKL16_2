package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FacadeVueImpl implements FacadeVue{

    protected FacadeVueImpl(){

    }

    public void afficherPatient(){

    }

    private String formatterRegimeAlimentaireRepas(List<RegimeAlimentaire>){
        return null;
    }

    private RegimeAlimentaire selectionnerRegimeAlimentaireSimple(List<RegimeAlimentaire> listeRegimesAlimentaires) throws ViewException {
        if (listeRegimesAlimentaires.size() == 0){
            throw new ViewException("La liste est vide");
        }
        List<String> liste = new ArrayList<>();
        for (RegimeAlimentaire ra : listeRegimesAlimentaires){
            liste.add(ra.toString());
        }
        AffichageConsole.afficherMenuSimple(liste);
        int choix = LectureConsole.lectureChoixInt(1, liste.size());
        RegimeAlimentaire ra = listeRegimesAlimentaires.get(choix-1);
        return ra;
    }

    public Patient ajouterRepasPatient(List<Patient> listePatients, List<Repas> listeRepas){
        return null;
    }

    public Patient selectionnerPatient(List<Patient> listePatients){
        return null;
    }

    public void afficherMessageErreur(String message){
        AffichageConsole.afficherErreur(message);
    }

    public void afficherPatients(List<Patient> listePatients) throws ViewException{
        if (listePatients.size() == 0){
            throw new ViewException("La liste est vide");
        }
        for (Patient patient : listePatients){
            AffichageConsole.afficherMessageAvecSautLigne("Date d'entrée : " + patient.getDateEntree().toString() + "nom : " + patient.getNom() + "prénom : " + patient.getPrenom() + "ID : " + patient.getId().toString());
        }
    }

    private List<RegimeAlimentaire> selectionnerRegimeAlimentaire(){
        return null;
    }

    public List<Repas> selectionnerRepas(List<Repas> listeRepas){
        return null;
    }

    public void afficherMessage(String message){
        AffichageConsole.afficherMessageAvecSautLigne(message);
    }

    public int afficherMenu(){
        List<String> menu = new ArrayList<>();
        menu.add("Lister les patients");
        menu.add("Créer un nouveau patient");
        menu.add("Modifier un patient");
        menu.add("Supprimer un patient");
        menu.add("Ajouter un repas à un patient");
        AffichageConsole.afficherMenuSimple(menu);
        int choix = LectureConsole.lectureChoixInt(0, menu.size());
        return choix;
    }

    private String saisirString(String s1, String s2, boolean b){
        return LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_TYPE_MSG);
    }

    private LocalDate saisirDateEntree(LocalDate dateEntree, boolean b){
        return LectureConsole.lectureLocalDate(ConstantesVue.SAISIR_DATE_ENTREE);
    }

    public Patient modifierPatient (Patient patient){
        return null;
    }

    public Patient saisirPatient (){
        return null;
    }

    private void afficherLstRegimeAlimentaire(List<RegimeAlimentaire> listeRegimesAlimentaires){

    }







}
