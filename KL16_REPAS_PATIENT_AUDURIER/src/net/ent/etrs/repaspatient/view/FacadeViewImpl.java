package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FacadeViewImpl implements FacadeVue {
    protected FacadeViewImpl() {
    }

    private static void ajouterRegimeAlimentaire(final Patient patient) throws ViewException {
        while (LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REGIME_ALIMENTAIRE)) {
            System.out.println(ConstantesVue.MSG_SELECTIONNER_REGIME_ALIMENTAIRE);
            List<String> lstLibelleRegime = new ArrayList<>();
            for (RegimeAlimentaire value : RegimeAlimentaire.values()) {
                if (!patient.getLstRegimeAlimentaire().contains(value)) {
                    lstLibelleRegime.add(value.getLibelle());
                }
            }
            AffichageConsole.afficherMenuSimple(lstLibelleRegime);
            try {
                patient.ajouterRegimeAlimentaire(RegimeAlimentaire.getByLibelle(lstLibelleRegime.get(LectureConsole.lectureChoixInt(1, lstLibelleRegime.size()) - 1)));
            } catch (RegimeAlimentaireException |
                     PatientException e) {
                throw new ViewException(e.getMessage(), e);
            }
        }
    }

    private static void afficherListRegimeAlimentaire(final Patient patient, final String tabulation) {
        if (patient.getLstRegimeAlimentaire().isEmpty()) {
            System.out.println(tabulation + ConstantesVue.AUCUN_REGIME_ALIMENTAIRE);
        } else {
            for (RegimeAlimentaire regimeAlimentaire : patient.getLstRegimeAlimentaire()) {
                System.out.printf("%s %s", tabulation, regimeAlimentaire.getLibelle());
            }
            System.out.println("");
        }
    }

    /**
     * Affiche un message.
     *
     * @param msg le message à afficher.
     */
    @Override
    public void afficherMessage(final String msg) {
        AffichageConsole.afficherMessageAvecSautLigne(msg);
    }

    /**
     * Affiche un message d'erreur.
     *
     * @param msg le message à afficher.
     */
    @Override
    public void afficherMessageErreur(final String msg) {
        AffichageConsole.afficherErreur(msg);
    }

    /**
     * Affiche le menu principal de l'application.
     *
     * @return le choix de l'option choisie.
     */
    @Override
    public int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesVue.MENU), ConstantesVue.MENU_NOM);
        return LectureConsole.lectureChoixInt(0, ConstantesVue.MENU.length);
    }

    /**
     * Affiche un patient.
     *
     * @param patient le patient à afficher.
     */
    @Override
    public void afficherPatient(final Patient patient) throws ViewException {
        paramNullChecker(patient);
        AffichageConsole.afficherMessageSansSautLigne(ConstantesVue.CARACTERE_SEPARATEUR);
        System.out.printf("Patient %s %s (%s) %s", patient.getNumSecu(), patient.getNom(), patient.getPrenom(), System.lineSeparator());
        System.out.println(ConstantesVue.ENTETE_REGIME_ALIMENTAIRE_EXISTANT);
        String tabulation = "\t\t";
        afficherListRegimeAlimentaire(patient, tabulation);
        tabulation += "\t";
        if (patient.getLstRepas().isEmpty()) {
            System.out.println(tabulation + ConstantesVue.MSG_AUCUN_REPAS);
        } else {
            for (Repas repas : patient.getLstRepas()) {
                System.out.printf("%s %s %s %s", tabulation, repas.getDateRepas(), repas.getTypeRepas(), System.lineSeparator());
            }
        }
    }

    /**
     * Affiche un ensemble de patients.
     *
     * @param lstPatients la liste des patients à afficher.
     */
    @Override
    public void afficherPatients(final List<Patient> lstPatients) throws ViewException {
        paramNullChecker(lstPatients);
        if (lstPatients.isEmpty()) {
            System.out.println(ConstantesVue.MSG_AUCUN_PATIENT);
        } else {
            for (Patient patient : lstPatients) {
                this.afficherPatient(patient);
            }
        }

    }

    /**
     * Demande la saisie d'un patient.
     *
     * @return le patient saisi.
     * @throws ViewException si une erreur est survenue durant la saisie.
     */
    @Override
    public Patient saisirPatient() throws ViewException {
        try {
            String numSecu = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_NUM_SECU_MSG);
            String nom = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_NOM_MSG);
            String prenom = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_PRENOM_MSG);
            LocalDate dateEntree = LectureConsole.lectureLocalDate(ConstantesVue.SAISIR_DATE_ENTREE, ConstantesVue.PATTERN_DATE);

            Patient patient = EntitiesFactory.fabriquerPatient(numSecu, nom, prenom, dateEntree);

            ajouterRegimeAlimentaire(patient);
            System.out.printf(ConstantesVue.MSG_PATIENT_CREATION, patient.getNom(), patient.getPrenom());
            return patient;
        } catch (PatientException e) {
            throw new ViewException(e.getMessage(), e);
        }
    }

    /**
     * Affiche un ensemble de patients et demande la sélection d'un des patients.
     *
     * @param lstPatients la liste des patients à afficher.
     * @return le patient sélectionné.
     */
    @Override
    public Patient selectionnerPatient(final List<Patient> lstPatients) {
        List<String> tmpList = new ArrayList<>();
        for (Patient patient : lstPatients) {
            tmpList.add(String.format("%s %s", patient.getNom(), patient.getPrenom()));
        }
        AffichageConsole.afficherMenuSimple(tmpList);
        return lstPatients.get(LectureConsole.lectureChoixInt(1, lstPatients.size()) - 1);
    }

    /**
     * Affiche un ensemble de repas et demande la sélection d'un des repas.
     *
     * @param lstRepas la liste des repas à afficher.
     * @return le repas sélectionné.
     */
    @Override
    public List<Repas> selectionnerRepas(final List<Repas> lstRepas) {
        List<Repas> listTmpRepas = new ArrayList<>();
        List<String> lstRepasTmp = new ArrayList<>();
        for (Repas repas : lstRepas) {
            String st = String.format("%s %s régime alimentaire :%s", repas.getDateRepas(), repas.getTypeRepas(), System.lineSeparator());
            for (RegimeAlimentaire regimeAlimentaire : repas.getLstRegimeAlimentaire()) {
                st += String.format("|%-15s", regimeAlimentaire.getLibelle());
            }
            lstRepasTmp.add(st);
        }
        do {
            AffichageConsole.afficherMenuSimple(lstRepasTmp);
            listTmpRepas.add(lstRepas.get(LectureConsole.lectureChoixInt(1, lstRepas.size()) - 1));
        } while (LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REPAS));
        return listTmpRepas;
    }

    /**
     * @param patient
     * @return
     * @throws ViewException
     */
    @Override
    public Patient modifierPatient(final Patient patient) throws ViewException {
        try {
            System.out.printf(ConstantesVue.MSG_ACTUEL, patient.getNumSecu());
            if (LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION)) {
                patient.setNumSecu(LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_NUM_SECU_MSG));
            }
            System.out.printf(ConstantesVue.MSG_ACTUEL, patient.getNom());
            if (LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION)) {
                patient.setNom(LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_NOM_MSG));
            }
            System.out.printf(ConstantesVue.MSG_ACTUEL, patient.getPrenom());
            if (LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION)) {
                patient.setPrenom(LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_PRENOM_MSG));
            }
            System.out.printf(ConstantesVue.MSG_ACTUEL, patient.getDateEntree());
            if (LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION)) {
                patient.setDateEntree(LectureConsole.lectureLocalDate(ConstantesVue.SAISIR_DATE_ENTREE, ConstantesVue.PATTERN_DATE));
            }
            afficherListRegimeAlimentaire(patient, "\t");
            ajouterRegimeAlimentaire(patient);
            System.out.printf(ConstantesVue.MSG_PATIENT_MISE_A_JOUR + "%s", patient.getNom(), patient.getPrenom(), System.lineSeparator());
        } catch (PatientException e) {
            throw new ViewException(e.getMessage(), e);
        }
        return patient;
    }

    /**
     * @param lstPatients
     * @param listRepas
     * @return
     */
    @Override
    public Patient ajouterRepasPatient(final List<Patient> lstPatients, final List<Repas> listRepas)
            throws ViewException {
        Patient patient = this.selectionnerPatient(lstPatients);
        try {
            for (Repas repas : this.selectionnerRepas(listRepas)) {
                patient.ajouterRepas(repas);
            }
        } catch (PatientException e) {
            throw new ViewException(e.getMessage(), e);
        }
        return patient;
    }

    private void paramNullChecker(Object o) throws ViewException {
        if (Objects.isNull(o)) {
            throw new ViewException(ConstantesVue.NULL_EXCEPTION);
        }
    }
}
