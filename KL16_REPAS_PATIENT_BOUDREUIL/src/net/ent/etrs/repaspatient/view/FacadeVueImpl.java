package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.GestionException;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierImpl;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.time.LocalDate;
import java.util.*;

public class FacadeVueImpl implements FacadeVue {

    FacadeMetierImpl fm;

    public FacadeVueImpl() {
        this.fm = FacadeMetierFactory.fabriquerFacadeMetier();
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
     * @param e l'exception dont on doit afficher le message.
     */
    @Override
    public void afficherMessageErreur(final Exception e) {
        GestionException.afficherMessageErreur(e);
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
        try {
            fm.recupererPatientById(patient.getId());
        } catch (BusinessException e) {
            throw new ViewException(ConstantesVue.MSG_PATIENT_INEXISTANT, e);
        }
    }

    /**
     * Affiche un ensemble de patients.
     *
     * @param lstPatients la liste des patients à afficher.
     */
    @Override
    public void afficherPatients(final List<Patient> lstPatients) throws ViewException {
        if (Objects.isNull(lstPatients)) {
            throw new ViewException(ConstantesVue.MSG_LST_PATIENT_NULL);
        }
        if (lstPatients.isEmpty()) {
            System.out.printf(ConstantesVue.MSG_AUCUN_PATIENT);
        } else {
            for (Patient patient : lstPatients) {
                AffichageConsole.afficherMessageAvecSautLigne("numéro secu:"+ patient.getNumSecu()+" | nom : "+patient.getNom() +" | prenom : "+patient.getPrenom() +" | date d'entré : "+patient.getDateEntree());
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
        String numSecu = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_NUM_SECU);
        String nom = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_NOM);
        String prenom = LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_PRENOM);
        final LocalDate dateEntree = LectureConsole.lectureLocalDate(ConstantesVue.SAISIR_DATE_ENTREE + "|" + AffichageConsole.PATTERN_DATE_PAR_DEFAUT);
        try {
            return fm.sauvegarderPatient(EntitiesFactory.fabriquerPatient(dateEntree, nom, prenom, numSecu));
        } catch (PatientConstructionException | BusinessException e) {
            throw new ViewException(ConstantesVue.MSG_ERREUR_PATIENT_CREATION);
        }
    }

    /**
     * Affiche un ensemble de patients et demande la sélection d'un des patients.
     *
     * @param lstPatients la liste des patients à afficher.
     * @return le patient sélectionné.
     * @throws ViewException
     */
    @Override
    public Patient selectionnerPatient(final List<Patient> lstPatients) throws ViewException {
        if(lstPatients.size() == 0){
            throw new ViewException(ConstantesVue.MSG_AUCUN_PATIENT);
        }
        List<String> lstPatientAffichage = new ArrayList<>();
        AffichageConsole.afficherMessageAvecSautLigne(String.format("%s | %s | %s | %s", "numero secu", "nom", "prenom", "date entré"));
        for (Patient p : lstPatients){
            lstPatientAffichage.add(String.format("%s | %s | %s | %s", p.getNumSecu(), p.getNom(), p.getPrenom(), p.getDateEntree()));
        }
        AffichageConsole.afficherMenuSimpleAvecOptionSortie(lstPatientAffichage,"retour");
        return lstPatients.get(LectureConsole.lectureChoixInt(0, lstPatientAffichage.size())-1);
    }

    /**
     * Affiche un ensemble de repas et demande la sélection d'un des repas.
     *
     * @param lstRepas la liste des repas à afficher.
     * @return le repas sélectionné.
     * @throws ViewException
     */
    @Override
    public Repas selectionnerRepas(final List<Repas> lstRepas) throws ViewException {
        if(lstRepas.size() == 0){
            throw new ViewException(ConstantesVue.MSG_AUCUN_REPAS);
        }
        List<String> lstRepasAffichage = new ArrayList<>();
        AffichageConsole.afficherMessageAvecSautLigne(String.format("%s | %s | %s "," id", "type repas", "date repas"));
        for (Repas r : lstRepas){
            lstRepasAffichage.add(String.format("%s | %s | %s | %s", r.getId(), r.getTypeRepas(), r.getDateRepas()));
        }
        AffichageConsole.afficherMenuSimpleAvecOptionSortie(lstRepasAffichage,"retour");
        return lstRepas.get(LectureConsole.lectureChoixInt(0, lstRepasAffichage.size())-1);
    }

    /**
     * @param patient
     * @return
     * @throws ViewException
     */
    @Override
    public void modifierPatient(final Patient patient) throws ViewException {
        try {
            fm.sauvegarderPatient(patient);
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.MSG_PATIENT_MISE_A_JOUR);
        } catch (BusinessException e) {
            throw new ViewException(ConstantesVue.MSG_ERREUR_PATIENT_MODIF);
        }
    }

    /**
     * @param lstPatients
     * @param listRepas
     * @return Patient
     * @throws ViewException
     */
    @Override
    public Patient ajouterRepasPatient(final List<Patient> lstPatients, final List<Repas> listRepas) throws ViewException {
        try {
            Patient patient = selectionnerPatient(lstPatients);
            Repas repas = selectionnerRepas(listRepas);
            patient.ajouterRepas(repas);
        } catch (ViewException | PatientException | RepasException e) {
            throw new ViewException(ConstantesVue.MSG_ERREUR_PATIENT_REPAS);
        }
        return null;
    }


}
