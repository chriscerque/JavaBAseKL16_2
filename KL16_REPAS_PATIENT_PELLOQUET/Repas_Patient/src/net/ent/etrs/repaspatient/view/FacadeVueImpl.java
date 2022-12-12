package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FacadeVueImpl implements FacadeVue{
    @Override
    public void afficherMessage(String msg) {
        AffichageConsole.afficherErreur(msg);
    }

    @Override
    public void afficherMessageErreur(String msg) {

    }

    @Override
    public int afficherMenu() {
        List<String> menuPrincipale = List.of(ConstantesMetier.OPTIONS_MENU_PRINCIPAL);
        int choix = 0;

        AffichageConsole.afficherMenuEntoureAvecOptionSortie(menuPrincipale, ConstantesMetier.TITRE_MENU_PRINCIPAL);
        choix = LectureConsole.lectureEntier(ConstantesMetier.CHOIX_OPTION_MENU_PRINCIPAL);
        return choix;
    }

    @Override
    public void afficherPatient(Patient patient) {

    }

    @Override
    public void afficherPatients(List<Patient> lstPatients) {
        List<String> liste = new ArrayList<>();
        for (Patient p : lstPatients) {
            String str = ("NSS: " + p.getNumSecu() + "    Nom: " + p.getNom() + "    Prenom: " + p.getPrenom() + "    Date d'arrivée: " + p.getDateEntree().toString());
            liste.add(str);
        }
        AffichageConsole.afficherMenuSimple(liste);
    }
    @Override
    public void afficherRepas(List<Repas> lstRepas) {
        List<String> liste = new ArrayList<>();
        for (Repas r : lstRepas) {
            String str = ("Date du repas: " + r.getDateRepas().toString() + "   Type de repas: " + r.getTypeRepas().toString() + "   Régime alimentaire: " + r.getLstRegimeRepas().toString());
            liste.add(str);
        }
        AffichageConsole.afficherMenuSimple(liste);
    }

    @Override
    public Patient saisirPatient() throws ViewException {
        String choixNSS = LectureConsole.lectureChaineCaracteres(ConstantesMetier.VUE_MSG_SAISIE_NSS);
        String choixNom = LectureConsole.lectureChaineCaracteres(ConstantesMetier.VUE_MSG_SAISIE_NOM);
        String choixPrenom = LectureConsole.lectureChaineCaracteres(ConstantesMetier.VUE_MSG_SAISIE_PRENOM);

        Patient patient = EntitiesFactory.fabriquerPatient(choixNSS, choixNom, choixPrenom, LocalDate.now());

        return patient;
    }

    @Override
    public Patient selectionnerPatient(List<Patient> lstPatients) {
        int choix;
        afficherPatients(lstPatients);
            choix = LectureConsole.lectureEntier() - 1;
        return lstPatients.get(choix);
    }

    @Override
    public List<Repas> selectionnerRepas(List<Repas> lstRepas) {
        int choix;
        List<Repas> lstRepasAjoute= new ArrayList<>();
        boolean continuer = false;
        do {
            afficherRepas(lstRepas);
            choix = LectureConsole.lectureEntier(ConstantesMetier.VUE_MSG_SAISIE_REPAS) - 1;
            lstRepasAjoute.add(lstRepas.get(choix));
            continuer = LectureConsole.lectureBoolean(ConstantesMetier.VUE_MSG_SAISIE_REPAS_SUIVANT);
        } while(continuer);
        return lstRepasAjoute;
    }

    @Override
    public Patient modifierPatient(Patient patient) throws ViewException {
        return null;
    }

    @Override
    public Patient ajouterRepasPatient(List<Patient> lstPatients, List<Repas> listRepas) {
        Patient patient = selectionnerPatient(lstPatients);
        try {
            for (Repas r : listRepas) {
                patient.ajouterRepas(r);
            }
        } catch (PatientConstructionException e) {
            afficherMessage(e.getMessage());
        }
        return null;
    }
}
