package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FacadeVueImpl implements FacadeVue {
    private static LocalDate saisirDatEntree() {
        return LectureConsole.lectureLocalDate(ConstantesVue.SAISIR_DATE_ENTREE, ConstantesVue.PATTERN_DATE);
    }

    private static String saisirNom() {
        return LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIE_NOM);
    }

    private static String saisirPrenom() {
        return LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIE_PRENOM);
    }

    private static String saisirNumSecu() {
        return LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIE_NUM_SECU);
    }

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
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesVue.MENU), ConstantesVue.NOM_MENU);
        return LectureConsole.lectureChoixInt(0, ConstantesVue.MENU.length);
    }

    @Override
    public void afficherPatient(final Patient patient) {
        AffichageConsole.afficherMessageAvecSautLigne(patient.toString());
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.CARACTERE_SEPARATEUR);
        //TODO plus sofistiqué
    }

    @Override
    public void afficherPatients(final List<Patient> lstPatients) {
        if (lstPatients.size() == 0) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.MSG_AUCUN_PATIENT);
        }
        for (Patient p : lstPatients) {
            afficherPatient(p);
        }
    }

    @Override
    public Patient saisirPatient() throws ViewException {
        try {
            String numSecu = saisirNumSecu();
            String prenom = saisirPrenom();
            String nom = saisirNom();
            LocalDate dateEntree = saisirDatEntree();
            Patient patient = EntitiesFactory.fabriquerPatient(numSecu, nom, prenom, dateEntree);
            AffichageConsole.afficherMessageAvecSautLigne(String.format(ConstantesVue.MSG_PATIENT_CREATION, patient.getPrenom(), patient.getNom()));
            return patient;
        } catch (PatientConstructionException e) {
            throw new ViewException(e.getMessage(), e);
        }
    }

    @Override
    public Patient selectionnerPatient(final List<Patient> lstPatients) {
        List<String> lstStrPatients = new ArrayList<>();
        for (Patient p : lstPatients) {
            lstStrPatients.add(p.toString());
            //TODO plus sofistiqué
        }
        AffichageConsole.afficherMenuSimple(lstStrPatients);
        int choix = LectureConsole.lectureChoixInt(1, lstStrPatients.size());
        return lstPatients.get(choix - 1);
    }

    @Override
    public List<Repas> selectionnerRepas(final List<Repas> lstRepas) {
        List<String> lstStrRepas = new ArrayList<>();
        List<Repas> lstRepasSelectionner = new ArrayList<>();
        for (Repas r : lstRepas) {
            lstStrRepas.add(r.toString());
            //TODO plus sofistiqué
        }
        AffichageConsole.afficherMenuSimple(lstStrRepas);
        int choix = LectureConsole.lectureChoixInt(1, lstStrRepas.size());
        lstRepasSelectionner.add(lstRepas.get(choix - 1));
        boolean nouveauRepas = LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REPAS);
        while (nouveauRepas) {
            AffichageConsole.afficherMenuSimple(lstStrRepas);
            choix = LectureConsole.lectureChoixInt(1, lstStrRepas.size());
            lstRepasSelectionner.add(lstRepas.get(choix - 1));
            nouveauRepas = LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REPAS);
        }
        return Collections.unmodifiableList(lstRepasSelectionner);
    }

    @Override
    public Patient modifierPatient(final Patient patient) throws ViewException {
        try {
            boolean choixNumSecu = LectureConsole.lectureBoolean(String.format(ConstantesVue.MSG_ACTUEL, patient.getNumSecu()));
            String newNumSecu = patient.getNumSecu();
            if (choixNumSecu) {
                newNumSecu = saisirNumSecu();
            }
            boolean choixPrenom = LectureConsole.lectureBoolean(String.format(ConstantesVue.MSG_ACTUEL, patient.getPrenom()));
            String newPrenom = patient.getPrenom();
            if (choixPrenom) {
                newPrenom = saisirPrenom();
            }
            boolean choixNom = LectureConsole.lectureBoolean(String.format(ConstantesVue.MSG_ACTUEL, patient.getNom()));
            String newNom = patient.getNom();
            if (choixNom) {
                newNom = saisirNom();
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ConstantesVue.PATTERN_DATE);
            boolean choixDateEntree = LectureConsole.lectureBoolean(String.format(ConstantesVue.MSG_ACTUEL, patient.getDateEntree().format(formatter)));
            LocalDate newDateEntree = patient.getDateEntree();
            if (choixDateEntree) {
                newDateEntree = saisirDatEntree();
            }
            AffichageConsole.afficherMessageSansSautLigne(ConstantesVue.ENTETE_REGIME_ALIMENTAIRE_EXISTANT);
            if (patient.getLstRegimeAlimentaire().size() == 0) {
                AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.AUCUN_REGIME_ALIMENTAIRE);
            } else {
                for (RegimeAlimentaire regimeAlimentaire : patient.getLstRegimeAlimentaire()) {
                    AffichageConsole.afficherMessageSansSautLigne(regimeAlimentaire.getLibelle());
                }
            }
            boolean choixRegimeAlimentaire = LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REGIME_ALIMENTAIRE);
            List<RegimeAlimentaire> newRegimeAlimentaire = new ArrayList<>(patient.getLstRegimeAlimentaire());
            if (choixRegimeAlimentaire) {
                do {
                    List<String> lstStrRegimeAlimentaire = new ArrayList<>();
                    for (RegimeAlimentaire regimeAlimentaire : RegimeAlimentaire.values()) {
                        if (!patient.getLstRegimeAlimentaire().contains(regimeAlimentaire)) {
                            lstStrRegimeAlimentaire.add(regimeAlimentaire.getLibelle());
                        }
                    }
                    AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.MSG_SELECTIONNER_REGIME_ALIMENTAIRE);
                    AffichageConsole.afficherMenuSimple(lstStrRegimeAlimentaire);
                    int choixnewRegimeAlimentaire = LectureConsole.lectureChoixInt(1, lstStrRegimeAlimentaire.size());
                    newRegimeAlimentaire.add(Arrays.asList(RegimeAlimentaire.values()).get(choixnewRegimeAlimentaire - 1));
                    choixRegimeAlimentaire = LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REGIME_ALIMENTAIRE);
                } while (choixRegimeAlimentaire);
            }
            Patient newPatient = EntitiesFactory.fabriquerPatient(newNumSecu, newNom, newPrenom, newDateEntree);
            for (RegimeAlimentaire r : newRegimeAlimentaire) {
                newPatient.ajouterRegimeAlimentaire(r);
            }
            AffichageConsole.afficherMessageAvecSautLigne(String.format(ConstantesVue.MSG_PATIENT_MISE_A_JOUR, newPatient.getPrenom(), newPatient.getNom()));
            return newPatient;
        } catch (PatientConstructionException | PatientException e) {
            throw new ViewException(e.getMessage(), e);
        }
    }

    @Override
    public Patient ajouterRepasPatient(final List<Patient> lstPatients, final List<Repas> listRepas) throws ViewException {
        try {
            Patient patient = selectionnerPatient(lstPatients);
            List<Repas> newlstRepas = selectionnerRepas(listRepas);
            for (Repas r : newlstRepas) {
                patient.ajouterRepas(r);
            }
            return patient;
        } catch (PatientException e) {
            throw new ViewException(e.getMessage(), e);
        }
    }
}
