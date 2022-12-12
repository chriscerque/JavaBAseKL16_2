package net.ent.etrs.test.view;

import net.ent.etrs.test.commons.utils.AffichageConsole;
import net.ent.etrs.test.commons.utils.LectureConsole;
import net.ent.etrs.test.model.entities.EntitiesFactory;
import net.ent.etrs.test.model.entities.Patient;
import net.ent.etrs.test.model.entities.Repas;
import net.ent.etrs.test.model.entities.exception.PatientConstructionException;
import net.ent.etrs.test.model.entities.exception.PatientException;
import net.ent.etrs.test.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.test.view.exceptions.ViewException;
import net.ent.etrs.test.view.references.ConstantesVue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FacadeViewImpl implements FacadeVue{


    protected FacadeViewImpl() {}

    @Override
    public void afficherMessage(String msg) {
        AffichageConsole.afficherMessageAvecSautLigne(msg);
    }

    @Override
    public void afficherMessageErreur(String msg) {
        AffichageConsole.afficherErreur(msg);
    }

    @Override
    public int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesVue.MENU), ConstantesVue.ENTETE_MENU);
        return LectureConsole.lectureChoixInt(0, ConstantesVue.MENU.length);
    }

    @Override
    public void afficherPatient(final Patient patient) {
        afficherMessage(String.format("Patient %s %s (%s)", patient.getNumSecu(), patient.getNom(), patient.getPrenom()));
        afficherLstRegimeAlimentaire(patient);
        afficherLstRepas(patient);
    }

    /**
     * Permet d'afficher les repas d'un patient.
     * @param patient
     */
    private void afficherLstRepas(final Patient patient) {
        List<Repas> lst = patient.getLstRepas();
        if(lst.size() == 0){
            afficherMessage(ConstantesVue.MSG_AUCUN_REPAS);
        } else {
            afficherMessage(ConstantesVue.ENTETE_LST_REPAS);
            for(Repas r : lst){
                afficherMessage(String.format("%s %s", r.getDateRepas(), r.getTypeRepas()));
            }
        }
    }

    /**
     * Permet d'afficher les régimes alimentaires d'un patient.
     * @param patient
     */
    private void afficherLstRegimeAlimentaire(Patient patient) {
        List<RegimeAlimentaire> lst = patient.getLstRegimeAlimentaire();
        if(lst.size() == 0){
            afficherMessage(ConstantesVue.AUCUN_REGIME_ALIMENTAIRE);
        } else {
            afficherMessage(ConstantesVue.ENTETE_REGIME_ALIMENTAIRE_EXISTANT);
            for(RegimeAlimentaire r : lst){
                afficherMessage(r.getLibelle());
            }
        }
    }

    @Override
    public void afficherPatients(List<Patient> lstPatients) {
        if(lstPatients.size() == 0) {
            afficherMessage(ConstantesVue.MSG_AUCUN_PATIENT);
        } else {
            for (Patient p : lstPatients) {
                afficherMessage(ConstantesVue.CARACTERE_SEPARATEUR);
                afficherPatient(p);
            }
        }
    }

    @Override
    public Patient saisirPatient() throws ViewException {

        try {
            //saisie num sécu sociale
            String numSecu = LectureConsole.lectureChaineCaracteres(ConstantesVue.PATIENT_SAISIE_SECU_SOCIALE);
            //Saisie nom
            String nom = LectureConsole.lectureChaineCaracteres(ConstantesVue.PATIENT_SAISIE_NOM);
            //Saisie prenom
            String prenom = LectureConsole.lectureChaineCaracteres(ConstantesVue.PATIENT_SAISIE_PRENOM);
            //Saisie date
            LocalDate date = LectureConsole.lectureLocalDate(ConstantesVue.SAISIR_DATE_ENTREE, ConstantesVue.PATTERN_DATE);
            //Creation patient
            Patient p = EntitiesFactory.fabriquerPatient(numSecu, nom, prenom, date);
            //demande si ajout de regime
            while (LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REGIME_ALIMENTAIRE)){
                RegimeAlimentaire regime = selectionnerRegimeAlimentaire(Arrays.asList(RegimeAlimentaire.values()));
                p.ajouterRegimeAlimentaire(regime);
            }
            return p;
        } catch (PatientConstructionException | PatientException e) {
            throw new ViewException(e.getMessage(), e);
        }
    }

    /**
     * Permet à l'utilisateur de sélectionner un régime alimentaire à ajouter.
     * @param lst
     * @return
     */
    private RegimeAlimentaire selectionnerRegimeAlimentaire(List<RegimeAlimentaire> lst) {
        List<String> lstLibelle = new ArrayList<>();
        for(RegimeAlimentaire r : lst){
            lstLibelle.add(r.getLibelle());
        }
        afficherMessage(ConstantesVue.MSG_SELECTIONNER_REGIME_ALIMENTAIRE);
        AffichageConsole.afficherMenuSimple(lstLibelle);
        int choix = LectureConsole.lectureChoixInt(1, lst.size());
        return lst.get(choix - 1);
    }

    @Override
    public Patient selectionnerPatient(List<Patient> lstPatients) {
        List<String> lst = new ArrayList<>();
        for(Patient p : lstPatients){
            lst.add(String.format("%s %s", p.getNom(), p.getPrenom()));
        }
        AffichageConsole.afficherMenuSimpleAvecOptionSortie(lst, ConstantesVue.PATIENT_SELECTION);
        return lstPatients.get(LectureConsole.lectureChoixInt(1, lstPatients.size()) - 1);
    }

    @Override
    public List<Repas> selectionnerRepas(List<Repas> lstRepas) {
        List<Repas> lstSelection = new ArrayList<>();
        do{
            for(int i = 0; i < lstRepas.size(); i ++){
                afficherMessage(String.format("%s - %s %s regime alimentaire : ", i + 1, lstRepas.get(i).getDateRepas(), lstRepas.get(i).getTypeRepas()));
                afficherMessage(formatterRegimeAlimentaire(lstRepas.get(i).getLstRegimeAlimentaire()));
            }
            lstSelection.add(lstRepas.get(LectureConsole.lectureChoixInt(1, lstRepas.size()) - 1));
        }while(LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REPAS));
        return lstSelection;
    }

    /**
     * Renvoi en une ligne les régimes alimentaires d'un repas.
     * @param lstRegimeAlimentaire
     * @return
     */
    private String formatterRegimeAlimentaire(List<RegimeAlimentaire> lstRegimeAlimentaire) {
        StringBuilder sb = new StringBuilder();
        for(RegimeAlimentaire r : lstRegimeAlimentaire){
            sb.append(r.getLibelle());
            sb.append(" | ");
        }
        return sb.toString();
    }


    @Override
    public Patient modifierPatient(Patient patient) throws ViewException {
        try {
            //modifier num sécu
            afficherMessage(String.format(ConstantesVue.MSG_ACTUEL, patient.getNumSecu()));
            if(LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION)){
                patient.setNumSecu(LectureConsole.lectureChaineCaracteres(ConstantesVue.MSG_NOUVEAU));
            }
            //modifier nom
            afficherMessage(String.format(ConstantesVue.MSG_ACTUEL, patient.getNom()));
            if(LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION)){
                patient.setNom(LectureConsole.lectureChaineCaracteres(ConstantesVue.MSG_NOUVEAU));
            }
            //modifier prenom
            afficherMessage(String.format(ConstantesVue.MSG_ACTUEL, patient.getPrenom()));
            if(LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION)){
                patient.setPrenom(LectureConsole.lectureChaineCaracteres(ConstantesVue.MSG_NOUVEAU));
            }
            //modifier date d'entrée
            afficherMessage(String.format(ConstantesVue.MSG_ACTUEL, patient.getDateEntree()));
            if(LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION)) {
                patient.setDateEntree(LectureConsole.lectureLocalDate(ConstantesVue.MSG_NOUVEAU, ConstantesVue.PATTERN_DATE));
            }
            //modifier régimes alimentaire
            afficherLstRegimeAlimentaire(patient);
            while (LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REGIME_ALIMENTAIRE)){
                RegimeAlimentaire regime = selectionnerRegimeAlimentaire(Arrays.asList(RegimeAlimentaire.values()));
                patient.ajouterRegimeAlimentaire(regime);
            }
            return patient;
        } catch (PatientException e) {
            throw new ViewException(e.getMessage(), e);
        }
    }

    @Override
    public Patient ajouterRepasPatient(List<Patient> lstPatients, List<Repas> listRepas) throws ViewException {
        try {
            Patient patient = selectionnerPatient(lstPatients);
            if(listRepas.size() == 0){
                afficherMessage(ConstantesVue.MSG_LST_REPAS_VIDE);
            } else {
                List<Repas> repasSelection = selectionnerRepas(listRepas);
                for(Repas rep : repasSelection) {
                    patient.ajouterRepas(rep);
                }
            }
            return patient;
        } catch (PatientException e) {
            throw new ViewException(e.getMessage(), e);
        }
    }
}
