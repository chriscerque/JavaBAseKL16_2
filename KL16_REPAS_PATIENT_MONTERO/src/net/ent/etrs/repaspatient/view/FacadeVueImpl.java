package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FacadeVueImpl implements FacadeVue {


    private static void setNumSecu(final Patient patient) throws ViewException {
        AffichageConsole.afficherMessageAvecSautLigne(String.format(ConstantesVue.MSG_ACTUEL, patient.getNumSecu()));
        boolean choix = LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION);
        if (choix) {
            try {
                patient.setNumSecu(LectureConsole.lectureChaineCaracteres(String.format(ConstantesVue.SAISIR_TYPE_MSG, "nouveau numero securite")));
            } catch (PatientException e) {
                throw new ViewException(e.getMessage(), e.getCause());
            }
        }
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
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesVue.MENU), ConstantesVue.MENU_NAME);
        return LectureConsole.lectureChoixInt(0, ConstantesVue.MENU.length);
    }

    @Override
    public void afficherPatient(final Patient patient) {
        AffichageConsole.afficherMessageAvecSautLigne(String.format("%-10s | %10s | %-10s | %-10s |", "numSecu", "nom", "prenom", "dateEntree"));
        AffichageConsole.afficherMessageAvecSautLigne(String.format("%-10s | %10s | %-10s | %-10s |", patient.getNumSecu(), patient.getNom(), patient.getPrenom(), patient.getDateEntree()));
    }

    @Override
    public void afficherPatients(final List<Patient> lstPatients) {
        List<String> affichage = new ArrayList<>();
        affichage.add(String.format("%-10s | %10s | %-10s | %-10s |", "numSecu", "nom", "prenom", "dateEntree"));
        StringBuilder sb = new StringBuilder();
        for (Patient patient : lstPatients) {
            sb.append(String.format("%-10s | %10s | %-10s | %-10s |", patient.getNumSecu(), patient.getNom(), patient.getPrenom(), patient.getDateEntree()));
            sb.append(System.lineSeparator());
            sb.append(listerRegimeLibelle(patient.getLstRegimeAlimentaire()));
            sb.append(System.lineSeparator());
            sb.append(listerRepas(patient.getLstRepas()));
            sb.append(System.lineSeparator());
            affichage.add(sb.toString());
            sb.delete(0, sb.length());
        }
        AffichageConsole.afficherMenuSimple(affichage);
    }

    private String listerRepas(final List<Repas> lstRepas) {

        if (lstRepas.size() == 0) {
            return "aucun repas a afficher";
        } else {
            String res = " ";

            for (Repas repas : lstRepas) {
                res = res + repas.getDateRepas() + " " + repas.getTypeRepas() + " ";
            }

            return res;
        }

    }

    @Override
    public Patient saisirPatient() throws ViewException {
        String numSecu = LectureConsole.lectureChaineCaracteres(String.format(ConstantesVue.SAISIR_TYPE_MSG, "numéro de sécurité : "));
        String nom = LectureConsole.lectureChaineCaracteres(String.format(ConstantesVue.SAISIR_TYPE_MSG, "nom : "));
        String prenom = LectureConsole.lectureChaineCaracteres(String.format(ConstantesVue.SAISIR_TYPE_MSG, "prenom : "));
        LocalDate dateEntree = LectureConsole.lectureDate(String.format(ConstantesVue.SAISIR_TYPE_MSG, "date dentree format jj/mm/aaaa : "), ConstantesVue.PATTERN_DATE);

        boolean regimeAlimentaire = LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REGIME_ALIMENTAIRE);
        if (regimeAlimentaire) {
            try {
                Patient p = EntitiesFactory.fabriquerPatient(numSecu, nom, prenom, dateEntree);
                do {
//                    RegimeAlimentaire regimeAlimentaire1 = selectionnerRegimeAlimentaire();
                    RegimeAlimentaire regimeAlimentaire1 = selectionnerRegimeAlimentaireByPatient(p);
                    p.ajouterRegimeAlimentaire(regimeAlimentaire1);
                    regimeAlimentaire = LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REGIME_ALIMENTAIRE);
                } while (regimeAlimentaire);
                return p;
            } catch (PatientConstructionException | PatientException e) {
                throw new ViewException(e.getMessage(), e.getCause());
            }
        } else {
            try {
                return EntitiesFactory.fabriquerPatient(numSecu, nom, prenom, dateEntree);
            } catch (PatientConstructionException e) {
                throw new ViewException(e.getMessage(), e.getCause());
            }
        }
    }

    /**
     * Permet de selectionner un regime alimentaire parmis TOUS les regimes existant.
     *
     * @return
     * @throws ViewException
     */
    private RegimeAlimentaire selectionnerRegimeAlimentaire() throws ViewException {
        List<String> affichage = new ArrayList<>();
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.ENTETE_REGIME_ALIMENTAIRE_EXISTANT);
        for (RegimeAlimentaire regimeAlimentaire : RegimeAlimentaire.values()) {
            affichage.add(regimeAlimentaire.getLibelle());
        }
        AffichageConsole.afficherMenuSimple(affichage);
        int index = LectureConsole.lectureChoixInt(1, affichage.size());
        try {
            return RegimeAlimentaire.getByLibelle(affichage.get(index - 1));
        } catch (RegimeAlimentaireException e) {
            throw new ViewException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Retire les regimes alimentaire a selectionner quand le patient la deja.
     *
     * @param patient
     * @return
     * @throws ViewException
     */
    private RegimeAlimentaire selectionnerRegimeAlimentaireByPatient(final Patient patient) throws ViewException {
        List<String> affichage = new ArrayList<>();
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.ENTETE_REGIME_ALIMENTAIRE_EXISTANT);
        for (RegimeAlimentaire regimeAlimentaire : RegimeAlimentaire.values()) {
            if (!patient.getLstRegimeAlimentaire().contains(regimeAlimentaire)) {
                affichage.add(regimeAlimentaire.getLibelle());
            }
        }
        AffichageConsole.afficherMenuSimple(affichage);
        int index = LectureConsole.lectureChoixInt(1, affichage.size());
        try {
            return RegimeAlimentaire.getByLibelle(affichage.get(index - 1));
        } catch (RegimeAlimentaireException e) {
            throw new ViewException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Patient selectionnerPatient(final List<Patient> lstPatients) {
        List<String> affichage = new ArrayList<>();
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.PATIENT_CHOIX);
        for (Patient patient : lstPatients) {
            affichage.add(String.format("%-10s | %10s | %-10s | %-10s |", patient.getNumSecu(), patient.getNom(), patient.getPrenom(), patient.getDateEntree()));
        }
        AffichageConsole.afficherMenuSimple(affichage);
        int index = LectureConsole.lectureChoixInt(1, affichage.size());
        return lstPatients.get(index - 1);
    }

    @Override
    public List<Repas> selectionnerRepas(final List<Repas> lstRepas) {
        List<String> affichage = new ArrayList<>();
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.REPAS_CHOIX);

        StringBuilder sb = new StringBuilder();
        for (Repas repas : lstRepas) {

            sb.append(String.format("%-10s", repas.getTypeRepas()));
            sb.append(System.lineSeparator());
            sb.append(listerRegimeLibelle(repas.getLstRegimeAlimentaires()));
            sb.append(System.lineSeparator());

            affichage.add(sb.toString());
            sb.delete(0, sb.length());
        }

        AffichageConsole.afficherMenuSimple(affichage);
        List<Repas> temps = new ArrayList<>();
        boolean continuer = true;
        do {
            int index = LectureConsole.lectureChoixInt(1, affichage.size());
            temps.add(lstRepas.get(index - 1));
            continuer = LectureConsole.lectureBoolean("Continuer de choisir un repas ? ");
        } while (continuer);
        return temps;
    }

    private String listerRegimeLibelle(final List<RegimeAlimentaire> lstRegimeAlimentaires) {

        if (lstRegimeAlimentaires.size() == 0) {
            return "aucun regime alimentaire a afficher";
        } else {
            String res = " ";

            for (RegimeAlimentaire regimeAlimentaire : lstRegimeAlimentaires) {
                res = res + regimeAlimentaire.getLibelle() + " ";
            }
            return res;
        }

    }

    @Override
    public Patient modifierPatient(final Patient patient) throws ViewException {


        setNumSecu(patient);
        setNom(patient);
        setPrenom(patient);
        setDateEntree(patient);
        setRegime(patient);


        return null;

    }

    private void setRegime(final Patient patient) throws ViewException {

        if (patient.getLstRegimeAlimentaire().size() == 0) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.AUCUN_REGIME_ALIMENTAIRE);
        } else {
            afficherRegimePatient(patient);
        }
        boolean choix = LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION);
        if (choix) {
            try {
                do {
                    patient.ajouterRegimeAlimentaire(selectionnerRegimeAlimentaireByPatient(patient));
                    choix = LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REGIME_ALIMENTAIRE);
                } while (choix);
            } catch (PatientException | ViewException e) {
                throw new ViewException(e.getMessage(), e.getCause());
            }
        }

    }

    private void afficherRegimePatient(final Patient patient) {

        for (RegimeAlimentaire regimeAlimentaire : patient.getLstRegimeAlimentaire()) {
            AffichageConsole.afficherMessageAvecSautLigne(regimeAlimentaire.getLibelle());
        }

    }

    private void setDateEntree(final Patient patient) throws ViewException {

        AffichageConsole.afficherMessageAvecSautLigne(String.format(ConstantesVue.MSG_ACTUEL, patient.getDateEntree()));
        boolean choix = LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION);
        if (choix) {
            try {
                patient.setDateEntree(LectureConsole.lectureDate(String.format(ConstantesVue.SAISIR_TYPE_MSG, "nouvelle date dentree "), ConstantesVue.PATTERN_DATE));
            } catch (PatientException e) {
                throw new ViewException(e.getMessage(), e.getCause());
            }
        }

    }

    private void setPrenom(final Patient patient) throws ViewException {

        AffichageConsole.afficherMessageAvecSautLigne(String.format(ConstantesVue.MSG_ACTUEL, patient.getPrenom()));
        boolean choix = LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION);
        if (choix) {
            try {
                patient.setPrenom(LectureConsole.lectureChaineCaracteres(String.format(ConstantesVue.SAISIR_TYPE_MSG, "nouveau prenom ")));
            } catch (PatientException e) {
                throw new ViewException(e.getMessage(), e.getCause());
            }
        }
    }

    private void setNom(final Patient patient) throws ViewException {

        AffichageConsole.afficherMessageAvecSautLigne(String.format(ConstantesVue.MSG_ACTUEL, patient.getNom()));
        boolean choix = LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION);
        if (choix) {
            try {
                patient.setNom(LectureConsole.lectureChaineCaracteres(String.format(ConstantesVue.SAISIR_TYPE_MSG, "nouveau nom ")));
            } catch (PatientException e) {
                throw new ViewException(e.getMessage(), e.getCause());
            }
        }
    }

    @Override
    public Patient ajouterRepasPatient(final List<Patient> lstPatients, final List<Repas> listRepas) throws ViewException {

        Patient p = selectionnerPatient(lstPatients);
        Repas r = selectionnerRepasSimple(listRepas);

        try {
            p.ajouterRepas(r);
        } catch (PatientException e) {
            throw new ViewException(e.getMessage(), e.getCause());
        }

        return p;
    }

    public Repas selectionnerRepasSimple(final List<Repas> lstRepas) {
        List<String> affichage = new ArrayList<>();
        AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.REPAS_CHOIX);
        StringBuilder sb = new StringBuilder();

        for (Repas repas : lstRepas) {

            sb.append(String.format("%-10s", repas.getTypeRepas()));
            sb.append(System.lineSeparator());
            sb.append(listerRegimeLibelle(repas.getLstRegimeAlimentaires()));
            sb.append(System.lineSeparator());

            affichage.add(sb.toString());
            sb.delete(0, sb.length());
        }

        AffichageConsole.afficherMenuSimple(affichage);
        int index = LectureConsole.lectureChoixInt(1, affichage.size());
        return lstRepas.get(index - 1);
    }
}