package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.commons.utils.AffichageConsole;
import net.ent.etrs.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FacadeVueImpl implements FacadeVue{
    /**
     * Affiche un message.
     *
     * @param msg le message à afficher.
     */
    @Override
    public void afficherMessage(String msg) {
        AffichageConsole.afficherMessageAvecSautLigne(msg);
    }

    /**
     * Affiche un message d'erreur.
     *
     * @param msg le message à afficher.
     */
    @Override
    public void afficherMessageErreur(String msg) {
        AffichageConsole.afficherErreur(msg);
    }

    /**
     * Affiche le menu principal de l'application.
     *
     * @return le choix de l'option choisie.
     */
    @Override
    public int afficherMenu() {
        List<String> initMenu = new ArrayList<>(initMenuPrincipal());
        AffichageConsole.afficherMenuSimpleAvecOptionSortie(initMenu, "Sortir");
        int choix = LectureConsole.lectureChoixInt(0,initMenu.size());
        return choix;
    }

    /**
     * Affiche un patient.
     *
     * @param patient le patient à afficher.
     */
    @Override
    public void afficherPatient(Patient patient) {
        afficherMessage(patient.getClass().toString());
        afficherMessage("- Numéro de Sécurité Sociale: " + patient.getNumSecu());
        afficherMessage("- Nom: " + patient.getNom());
        afficherMessage("- Prénom: " + patient.getPrenom());
        afficherMessage("- Date d'entrée: " + patient.getPrenom());
    }

    /**
     * Affiche un ensemble de patients.
     *
     * @param lstPatients la liste des patients à afficher.
     */
    @Override
    public void afficherPatients(List<Patient> lstPatients) {
        for (Patient patient :
                lstPatients) {
            afficherPatient(patient);
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
            AffichageConsole.afficherMessageSansSautLigne("Renseignez son numéro de Sécurité Sociale :");
            String numSecu = LectureConsole.lectureChaineCaracteres();

            AffichageConsole.afficherMessageSansSautLigne("Renseignez son nom de famille :");
            String nom = LectureConsole.lectureChaineCaracteres();

            AffichageConsole.afficherMessageSansSautLigne("Renseignez son prénom :");
            String prenom  = LectureConsole.lectureChaineCaracteres();

            AffichageConsole.afficherMessageSansSautLigne("Renseignez sa date d'entrée :");
            LocalDate dateEntree = LectureConsole.lectureLocalDate(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));

            return EntitiesFactory.fabriquerPatient(numSecu, nom, prenom, dateEntree);
        } catch (PatientConstructionException e) {
            throw new ViewException(e.getMessage());
        }
    }

    /**
     * Affiche un ensemble de patients et demande la sélection d'un des patients.
     *
     * @param lstPatients la liste des patients à afficher.
     * @return le patient sélectionné.
     */
    @Override
    public Patient selectionnerPatient(List<Patient> lstPatients) throws ViewException {
        if(Objects.isNull(lstPatients) || lstPatients.size() == 0){
            throw new ViewException(ConstantesVue.MSG_AUCUN_PATIENT);
        }
        List<String> tmpPatient = new ArrayList<>();
        for (Patient p: lstPatients) {
            tmpPatient.add(p.getNumSecu());
        }
        AffichageConsole.afficherMenuSimple(tmpPatient);
        String numSecu =LectureConsole.lectureChaineCaracteres(String.format(ConstantesVue.SAISIR_TYPE_MSG, "Numéro de Sécurité Sociale"));
        for (Patient p: lstPatients) {
            if (p.getNumSecu().equals(numSecu)) {
                return p;
            }
        }
        throw new ViewException(ConstantesVue.MSG_AUCUN_PATIENT);
    }

    /**
     * Affiche un ensemble de repas et demande la sélection d'un des repas.
     *
     * @param lstRepas la liste des repas à afficher.
     * @return le repas sélectionné.
     */
    @Override
    public List<Repas> selectionnerRepas(List<Repas> lstRepas) throws ViewException {
        if(Objects.isNull(lstRepas) || lstRepas.size() == 0){
            throw new ViewException(ConstantesVue.MSG_AUCUN_PATIENT);
        }

        List<String> tmpRepasString = new ArrayList<>();
        List<Repas> res = new ArrayList<>();
        for (Repas repas: lstRepas) {
            tmpRepasString.add(repas.toString());
        }
        int choix = 0;
        do {
            AffichageConsole.afficherMenuSimple(tmpRepasString);
            res.add(lstRepas.get(LectureConsole.lectureChoixInt(1, lstRepas.size(), String.format(ConstantesVue.SAISIR_TYPE_MSG, "repas"))));
            tmpRepasString.remove(res.get(res.size() - 1).toString());
        } while (choix != 0);

        return res;
    }

    /**
     * @param patient
     * @return
     * @throws ViewException
     */
    @Override
    public Patient modifierPatient(Patient patient) throws ViewException {
        return null;
    }

    /**
     * @param lstPatients
     * @param listRepas
     * @return
     */
    @Override
    public Patient ajouterRepasPatient(List<Patient> lstPatients, List<Repas> listRepas) throws ViewException {
        try {
            Patient patient = selectionnerPatient(lstPatients);
            List<Repas> lstRepas = selectionnerRepas(listRepas);
            for (Repas repas :
                    lstRepas) {
                patient.ajouterRepas(repas);
            }
            return patient;
        } catch (ViewException | PatientException e) {
            throw new ViewException(e.getMessage());
        }
    }

    public List<String> initMenuPrincipal() {
        List<String> menu = new ArrayList<>();
        for (String option :
                ConstantesVue.MENU) {
            menu.add(option);
        }
        return menu;
    }

//    private String formatterRegimeAlimentaireRepas(List<RegimeAlimentaire> regimeAlimentaires) {
//
//    }
//
//    private RegimeAlimentaire seletionnerRegimeAlimentaireSimple(List<RegimeAlimentaire> regimeAlimentaires) {
//
//    }
//
//    private List<RegimeAlimentaire> selectionnerRegimeAlimentaire(List<RegimeAlimentaire> regimeAlimentaires, boolean bool) {
//
//    }
//
//    private String saisirString(String string, String string2, boolean bool) {
//
//    }
//
//    private LocalDate saisirDateEntree(LocalDate localDate, boolean bool) {
//
//    }
//
//    private void afficherLstRegimeAlimentaire(List<RegimeAlimentaire> regimeAlimentaires) {
//
//    }
}
