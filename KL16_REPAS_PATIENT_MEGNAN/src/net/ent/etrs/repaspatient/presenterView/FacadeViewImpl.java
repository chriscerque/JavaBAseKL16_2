package net.ent.etrs.repaspatient.presenterView;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.facade.exceptions.BusinessException;
import net.ent.etrs.repaspatient.presenterView.exceptions.ViewException;
import net.ent.etrs.repaspatient.presenterView.references.ConstantesVue;

import java.time.LocalDate;
import java.util.List;

public class FacadeViewImpl implements FacadeView {

    protected FacadeViewImpl() {
    }


    @Override
    public void afficherMessage(String msg) {
        AffichageConsole.afficherMessageAvecSautLigne(msg);
    }

    @Override
    public void afficherMessageErreur(String msg) {
        AffichageConsole.afficherErreur(msg);
    }

    @Override
    public String[] afficherMenu() {
        return ConstantesVue.MENU;
    }

    @Override
    public void afficherPatient(Patient patient) {
        patient.toString();
    }

    @Override
    public void afficherPatients(List<Patient> lstPatients) throws ViewException {
        if (lstPatients.isEmpty()){
            throw new ViewException(ConstantesVue.MSG_AUCUN_PATIENT);
        }
        for (Patient p : lstPatients){
            AffichageConsole.afficherMessageAvecSautLigne(p.toString());
        }
    }

    @Override
    public Patient saisirPatient() throws ViewException {

        String nom = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_TYPE_MSG);
        String prenom = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_TYPE_MSG);
        String numSecu = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_TYPE_MSG);
        LocalDate dateEntre = LectureConsole.lectureLocalDate(ConstantesVue.SAISIR_TYPE_MSG);

        try {
            Patient p = EntitiesFactory.fabriquerPatient(numSecu, nom, prenom, dateEntre);
            return p;
        } catch (PatientException e) {
            throw new ViewException(e);
        }
    }

    @Override
    public Patient selectionnerPatient(List<Patient> lstPatients) throws ViewException {
        LectureConsole.lectureChaineCaracteres();
        for (Patient p : lstPatients){
            afficherPatient(p);
        }
        throw new ViewException(ConstantesVue.MSG_AUCUN_PATIENT);
    }

    @Override
    public List<Repas> selectionnerRepas(List<Repas> lstRepas) throws ViewException {
        LectureConsole.lectureChaineCaracteres();
        for (Repas r: lstRepas) {
            AffichageConsole.afficherMessageAvecSautLigne(r.toString());
        }
        try {
            throw new BusinessException(ConstantesVue.MSG_AUCUN_REPAS);
        } catch (BusinessException e) {
            throw new ViewException(e);
        }
    }

    @Override
    public Patient modifierPatient(Patient patient) throws ViewException {
        String modif = LectureConsole.lectureChaineCaracteres(ConstantesVue.MSG_MODIF_QUESTION);
        return patient;
    }

    @Override
    public Patient ajouterRepasPatient(List<Patient> lstPatients, List<Repas> listRepas) {
        String repas = LectureConsole.lectureChaineCaracteres(ConstantesVue.CONTINUER_SELECTION_REPAS);
        if (listRepas.contains(repas)) {
            for (Patient p : lstPatients) {
                if (p.getId().equals(p.getId())) {
                    p.getLstRepas().add(repas);
                }
            }
        }
        return X;
    }
}

