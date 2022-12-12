package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.views.facades;

import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.EntitiesFactory;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.Patient;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.Repas;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.excetions.EntitiesFactoryException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.common.utils.AffichageConsole;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.common.utils.LectureConsole;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.views.exceptions.FacadeViewException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.views.exceptions.ViewException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.views.references.ConstantesView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FacadeViewImpl implements FacadeView {
    @Override
    public void afficherMessage(final String message) {
        AffichageConsole.afficherMessageAvecSautLigne(message);

    }

    @Override
    public void afficherMessageErreur(final String message) {
        AffichageConsole.afficherErreur(message);

    }

    @Override
    public int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesView.MENU), ConstantesView.NOM_MENU);
        return LectureConsole.lectureChoixInt(0, ConstantesView.MENU.length);
    }

    @Override
    public void afficherPatient(final Patient patient) {

    }

    @Override
    public void afficherPatients(final List<Patient> lstPatients) {
        for (Patient patient : lstPatients) {
            AffichageConsole.afficherMessageAvecSautLigne(lstPatients.getClass().toString() + "id : " +
                    patient.getId());
        }

    }

    @Override
    public Patient saisirPatient() throws ViewException {
        try {
            LocalDate dateEntree = LectureConsole.lectureLocalDate(ConstantesView.SAISIR_DATE_ENTREE, ConstantesView.PATTERN_DATE);
            String numSecu = LectureConsole.lectureChaineCaracteres(ConstantesView.AFFICHER_MSG_NUM_SECU);
            String nom = LectureConsole.lectureChaineCaracteres(ConstantesView.AFFICHER_MSG_NOM);
            String prenom = LectureConsole.lectureChaineCaracteres(ConstantesView.AFFICHER_MSG_PRENOM);
            return EntitiesFactory.fabriquerPatient(dateEntree, numSecu, nom, prenom);
        } catch (EntitiesFactoryException e) {
            AffichageConsole.afficherErreur(e.getMessage());
        }
        return null;
    }

    @Override
    public Patient selectionnerPatient(final List<Patient> lstPatients) throws FacadeViewException {
        if (lstPatients.size() == 0) {
            throw new FacadeViewException(ConstantesView.FACADE_VIEW_LIST_PATIENT_VIDE);
        }
        List<String> lstCommande = new ArrayList<>();
        for (Patient patient : lstPatients) {
            lstCommande.add(lstPatients.toString());
        }
        AffichageConsole.afficherMenuSimple(lstCommande);
        int choix = LectureConsole.lectureChoixInt(1, lstCommande.size());
        return lstPatients.get(choix - 1);
    }

    @Override
    public Repas selectionnerRepas(final List<Repas> lstRepas) throws FacadeViewException {
        if (lstRepas.size() == 0) {
            throw new FacadeViewException(ConstantesView.FACADE_VIEW_LIST_REPAS_VIDE);
        }
        List<String> lstCommande = new ArrayList<>();
        for (Repas repas : lstRepas) {
            lstCommande.add(lstRepas.toString());
        }
        AffichageConsole.afficherMenuSimple(lstCommande);
        int choix = LectureConsole.lectureChoixInt(1, lstCommande.size());
        return lstRepas.get(choix - 1);
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
