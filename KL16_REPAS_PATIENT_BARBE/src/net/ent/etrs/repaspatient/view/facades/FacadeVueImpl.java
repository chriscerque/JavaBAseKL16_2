package net.ent.etrs.repaspatient.view.facades;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.FarbriquerPatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.time.LocalDate;
import java.util.*;

public class FacadeVueImpl implements FacadeVue {

    static FacadeMetier facadeMetier = FacadeMetierFactory.fabriquerFacadeMetier();

    // OVERRIDE METHODS
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
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesVue.MENU), ConstantesVue.MENU_NOM);
        return LectureConsole.lectureChoixInt(0, ConstantesVue.MENU.length);
    }


    @Override
    public void afficherPatient(Patient patient) {
        AffichageConsole.afficherMenuSimple(Collections.singletonList(patient.getNumSecu()));
    }

    @Override
    public void afficherPatients(List<Patient> lstPatients) {
        List<String> tmpPatient = new ArrayList<>();
        for (Patient p : lstPatients) {
            tmpPatient.add(p.getNumSecu());
        }
        AffichageConsole.afficherMenuSimple(tmpPatient);
    }

    @Override
    public Patient saisirPatient() throws ViewException {
        try {
            String numSecu = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_CLIENT);
            String nom = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_NOM);
            String prenom = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_PRENOM);
            LocalDate dateEntree = LectureConsole.lectureLocalDate(ConstantesVue.SAISIR_DATE_ENTREE, ConstantesVue.PATTERN_DATE);

            return EntitiesFactory.fabriquerPatient(numSecu, numSecu, prenom, dateEntree);
        } catch (FarbriquerPatientException e) {
            throw new ViewException(e.getMessage());
        }

    }

    @Override
    public Patient selectionnerPatient(List<Patient> lstPatients) throws ViewException {
        if (Objects.isNull(lstPatients) || lstPatients.size() == 0) {
            throw new ViewException(ConstantesVue.MSG_AUCUN_PATIENT);
        }
        afficherPatients(lstPatients);
        String numSecu = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_CLIENT);

        for (Patient patient : lstPatients) {
            if (patient.getNumSecu().equals(numSecu)) {
                return patient;
            }
        }
        throw new ViewException(ConstantesVue.MSG_AUCUN_PATIENT);

    }

    @Override
    public List<Repas> selectionnerRepas(List<Repas> lstRepas) throws ViewException {
        if (Objects.isNull(lstRepas) || lstRepas.size() == 0) {
            throw new ViewException(ConstantesVue.MSG_AUCUN_REPAS);
        }

        List<String> tempRepas = new ArrayList<>();
        for (Repas repas : lstRepas) {
            tempRepas.add(repas.getId().toString());
        }
        AffichageConsole.afficherMenuSimple(tempRepas);
        String id = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_REPAS);


        List<Repas> repasList = new ArrayList<>();
        for (Repas repas : lstRepas) {
            if (repas.getId().toString().equals(id)) {
                repasList.add(repas);
            }
        }
        if (repasList.size() == 0) {
            throw new ViewException(ConstantesVue.MSG_AUCUN_REPAS);
        }
        return repasList;
    }

    @Override
    public Patient modifierPatient(Patient patient) throws ViewException {
        try {
            facadeMetier.mettreAJourPatient(patient);
        } catch (BusinessException e) {
            throw new ViewException(e.getMessage());
        }
        return null;
    }

    @Override
    public Patient ajouterRepasPatient(List<Patient> lstPatients, List<Repas> listRepas) throws ViewException {
        try {
            Patient p = selectionnerPatient(lstPatients);
            for (Repas repas : selectionnerRepas(listRepas)) {
                p.ajouterRepas(repas);
            }
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.MSG_REPAS_AJOUTE);
            return p;
        } catch (PatientException | ViewException e) {
            afficherMessageErreur(e.getMessage());
        }
        throw new ViewException(ConstantesVue.MSG_AUCUN_PATIENT);
    }


}
