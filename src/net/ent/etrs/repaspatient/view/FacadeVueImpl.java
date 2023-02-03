package net.ent.etrs.repaspatient.view;


import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;
import net.ent.etrs.repaspatient.model.entities.RegimeAlimentaire;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FacadeVueImpl implements FacadeVue {

    protected FacadeVueImpl() {
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
    public void afficherException(final Exception exception) {
        AffichageConsole.afficherException(exception);
    }

    @Override
    public int afficherMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesVue.MENU), "Repas patient");
        return LectureConsole.lectureChoixInt(0, ConstantesVue.MENU.length);
    }

    @Override
    public void afficherPatient(final Patient patient) {
        StringBuilder sb = new StringBuilder();
        sb.append(ConstantesVue.CARACTERE_SEPARATEUR);
        sb.append(String.format("Patient %s %s (%s) %n", patient.getNumSecu(), patient.getNom(), patient.getPrenom(),
                patient.getDateEntree().format(DateTimeFormatter.ofPattern(ConstantesVue.PATTERN_DATE))));
        AffichageConsole.afficherMessageSansSautLigne(sb.toString());
        this.afficherLstRegimeAlimentaire(patient.getLstRegimeAlimentaire());
        this.afficherRepasPatient(patient.getLstRepas());

    }


    private void afficherRepasPatient(final Set<Repas> repas) {
        if (repas.size() == 0) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.MSG_AUCUN_REPAS);
        } else {
            StringBuilder sb = new StringBuilder();
            List<Repas> lstRepas = new ArrayList<>(repas);
            Collections.sort(lstRepas);
            for (Repas r : lstRepas) {
                sb.append(String.format("%20s %s", r.getDateRepas(), r.getTypeRepas()));
                sb.append(System.lineSeparator());
            }
            AffichageConsole.afficherMessageAvecSautLigne(sb.toString());
        }

    }


    @Override
    public void afficherPatients(final List<Patient> lstPatients) {
        if (lstPatients.size() == 0) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.MSG_AUCUN_PATIENT);
        } else {
            for (Patient patient : lstPatients) {
                this.afficherPatient(patient);
            }
        }

    }

    @Override
    public Patient saisirPatient() throws ViewException {

        try {
            String numSecu = saisirString(null, "numéro de sécurité", false);
            String nom = saisirString(null, "nom", false);
            String prenom = saisirString(null, "prénom", false);
            LocalDate dateEntree = saisirDateEntree(null, false);
            List<RegimeAlimentaire> lstRegimeAlimentaireSelectionnes = null;

            lstRegimeAlimentaireSelectionnes = selectionnerRegimeAlimentaire(null, false);

////TODO sout
//        System.out.println("lstRegimeAlimentaireSelectionnes : " + lstRegimeAlimentaireSelectionnes);
//        patient.tabRegimeAlimentaire = new String[lstRegimeAlimentaireSelectionnes.size()];
//        lstRegimeAlimentaireSelectionnes.toArray(patient.tabRegimeAlimentaire);
////        patient.tabRegimeAlimentaire = lstRegimeAlimentaireSelectionnes.toArray(new String[0]);
////TODO sout
//        System.out.println("patient.tabRegimeAlimentaire : " + patient.tabRegimeAlimentaire);
//        for (String s : patient.tabRegimeAlimentaire) {
//            System.out.println("s ->" + s);
//        }
//        Arrays.stream(patient.tabRegimeAlimentaire).forEach(System.out::println);
//        System.out.println("-----");
            Patient patient = EntitiesFactory.fabriquerPatient(numSecu, nom, prenom, dateEntree);
            for (RegimeAlimentaire regimeAlimentaire : lstRegimeAlimentaireSelectionnes) {
                patient.ajouterRegimeAlimentaire(regimeAlimentaire);
            }

            return patient;
        } catch (ViewException | PatientConstructionException | PatientException e) {
            throw new ViewException(e.getMessage(), e);
        }
    }

    @Override
    public Patient selectionnerPatient(final List<Patient> lstPatients) throws ViewException {
        if (lstPatients.size() == 0) {
            throw new ViewException(ConstantesVue.MSG_AUCUN_PATIENT);
        } else {
            AffichageConsole.afficherMessageAvecSautLigne("Sélectionner un contact : ");
            List<String> option = new ArrayList<>();
            for (Patient patient : lstPatients) {
                option.add(String.format("%s %s", patient.getNom(), patient.getPrenom()));
            }
            AffichageConsole.afficherMenuSimple(option);
            return lstPatients.get(LectureConsole.lectureChoixInt(1, option.size()) - 1);
        }
    }

    @Override
    public List<Repas> selectionnerRepas(final List<Repas> lstRepas) throws ViewException {
        if (lstRepas.size() == 0) {
            throw new ViewException(ConstantesVue.MSG_AUCUN_REPAS);
        } else {
            List<Repas> lstRepasSelectionnes = new ArrayList<>();
            do {
                AffichageConsole.afficherMessageAvecSautLigne("Sélectionner un repas : ");
                List<String> lstRepasAffichage = new ArrayList<>();
                for (Repas repas : lstRepas) {
                    lstRepasAffichage.add(String.format("%s %s %s", repas.getDateRepas(), repas.getTypeRepas(), this.formatterRegimeAlimentaireRepas(repas.getLstRegimeAlimentaire())));
                }
                AffichageConsole.afficherMenuSimple(lstRepasAffichage);
                lstRepasSelectionnes.add(lstRepas.get(LectureConsole.lectureChoixInt(1, lstRepasAffichage.size()) - 1));
            } while (LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REPAS));


            return lstRepasSelectionnes;
        }
    }

    private String formatterRegimeAlimentaireRepas(final List<RegimeAlimentaire> tabRegimeAlimentaire) {
        StringBuilder sb = new StringBuilder("regime alimentaire : ");
        sb.append(System.lineSeparator());
        for (RegimeAlimentaire str : tabRegimeAlimentaire) {
            sb.append("\t | ").append(String.format("%-20s", str.getLibelle()));
        }
        return sb.toString();
    }

    @Override
    public Patient modifierPatient(final Patient patient) throws ViewException {
        try {
            String numSecu = saisirString(patient.getNumSecu(), "numéro de sécurité", true);
            String nom = saisirString(patient.getNom(), "nom", true);
            String prenom = saisirString(patient.getPrenom(), "prénom", true);
            LocalDate dateEntree = saisirDateEntree(patient.getDateEntree(), true);
            List<RegimeAlimentaire> lstRegimeAlimentaireSelectionnes = null;

            lstRegimeAlimentaireSelectionnes = selectionnerRegimeAlimentaire(patient.getLstRegimeAlimentaire(), true);

            if (lstRegimeAlimentaireSelectionnes.size() != 0) {
                for (RegimeAlimentaire regimeAlimentaire : lstRegimeAlimentaireSelectionnes) {
                    patient.ajouterRegimeAlimentaire(regimeAlimentaire);
                }
            }

            patient.setNumSecu(numSecu);
            patient.setNom(nom);
            patient.setPrenom(prenom);
            patient.setDateEntree(dateEntree);
            return patient;
        } catch (ViewException | PatientException e) {
            throw new ViewException(e.getMessage(), e);
        }
    }

    @Override
    public Patient ajouterRepasPatient(final List<Patient> lstPatients, final List<Repas> listRepas) throws ViewException {
        Patient patient = this.selectionnerPatient(lstPatients);
        for (Repas repas : this.selectionnerRepas(listRepas)) {
            try {
                patient.ajouterRepas(repas);
            } catch (PatientException e) {
                this.afficherMessageErreur(e.getMessage());
            }
        }
        return patient;

    }


//    private void afficherRepas(Patient c) {
//        if (!c.getLstRepas().isEmpty()) {
//            for (Coordonnee coord : c.classerCoordonnee()) {
//                AffichageConsole.afficherMessageSansSautLigne("\t\t");
//                if (coord instanceof Mail) {
//                    mailVue.afficherCoordonnee((Mail) coord);
//                } else {
//                    telephoneVue.afficherCoordonnee((Telephone) coord);
//                }
//            }
//        } else {
//            AffichageConsole.afficherMessageAvecSautLigne("\t\t Aucune Coordonnées.");
//        }

//    }


    private LocalDate saisirDateEntree(final LocalDate dateNaissanceOld, final boolean modif) {
        if (modif) {
            AffichageConsole.afficherMessageAvecSautLigne(String.format(ConstantesVue.MSG_ACTUEL, dateNaissanceOld));
            boolean choix = LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION);
            if (choix) {
                return LectureConsole.lectureLocalDate(ConstantesVue.PATTERN_DATE);
            }
            return dateNaissanceOld;
        }
        return LectureConsole.lectureLocalDate(ConstantesVue.SAISIR_DATE_ENTREE, ConstantesVue.PATTERN_DATE);

    }


    private String saisirString(final String strOld, final String typeMsg, final boolean modif) {
        if (modif) {
            AffichageConsole.afficherMessageAvecSautLigne(String.format(ConstantesVue.MSG_ACTUEL, strOld));
            boolean choix = LectureConsole.lectureBoolean(ConstantesVue.MSG_MODIF_QUESTION);
            if (choix) {
                return LectureConsole.lectureChaineCaracteres("nouveau :");
            }
            return strOld;
        }
        return LectureConsole.lectureChaineCaracteres(String.format(ConstantesVue.SAISIR_TYPE_MSG, typeMsg));

    }

    private List<RegimeAlimentaire> selectionnerRegimeAlimentaire(final List<RegimeAlimentaire> regimeAlimentaireOld, final boolean modif) throws ViewException {
        List<RegimeAlimentaire> lst = new ArrayList<>();
        List<RegimeAlimentaire> lstRegimeAlimentaireDispo = new ArrayList<>(Arrays.asList(RegimeAlimentaire.values()));
        if (regimeAlimentaireOld != null && regimeAlimentaireOld.size() != 0) {
            lstRegimeAlimentaireDispo.removeAll(regimeAlimentaireOld);
        }

        if (modif) {
            this.afficherLstRegimeAlimentaire(regimeAlimentaireOld);
        }

        if (lstRegimeAlimentaireDispo.size() != 0) {
            while (LectureConsole.lectureBoolean(ConstantesVue.CONTINUER_SELECTION_REGIME_ALIMENTAIRE)) {
                RegimeAlimentaire regimeAlimentaire = selectionnerRegimeAlimentaireSimple(lstRegimeAlimentaireDispo);
                lst.add(regimeAlimentaire);
                lstRegimeAlimentaireDispo.remove(regimeAlimentaire);
            }

        } else {
            this.afficherMessage(ConstantesVue.AUCUN_REGIME_ALIMENTAIRE);
        }

        return lst;

    }

    private void afficherLstRegimeAlimentaire(final List<RegimeAlimentaire> regimeAlimentaireOld) {
        StringBuilder sb = new StringBuilder(ConstantesVue.ENTETE_REGIME_ALIMENTAIRE_EXISTANT);
        if (Objects.isNull(regimeAlimentaireOld) || regimeAlimentaireOld.size() == 0) {
            sb.append(ConstantesVue.AUCUN_REGIME_ALIMENTAIRE);
        } else {
            for (RegimeAlimentaire regimeAlimenaire : regimeAlimentaireOld) {
                sb.append(System.lineSeparator());
                sb.append("\t\t");
                sb.append(regimeAlimenaire.getLibelle());
            }
        }

        AffichageConsole.afficherMessageAvecSautLigne(sb.toString());
    }

    private RegimeAlimentaire selectionnerRegimeAlimentaireSimple(final List<RegimeAlimentaire> lstRegimeAlimentaire) throws ViewException {
        try {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesVue.MSG_SELECTIONNER_REGIME_ALIMENTAIRE);
            List<String> lstRegimeAlimentaireAff = new ArrayList<>();
            for (RegimeAlimentaire r : lstRegimeAlimentaire) {
                lstRegimeAlimentaireAff.add(r.getLibelle());
            }
            AffichageConsole.afficherMenuSimple(lstRegimeAlimentaireAff);
            int choix = LectureConsole.lectureChoixInt(1, lstRegimeAlimentaireAff.size());

            return RegimeAlimentaire.getByLibelle(lstRegimeAlimentaireAff.get(choix - 1));
        } catch (RegimeAlimentaireException e) {
            this.afficherMessageErreur(e.getMessage());
            throw new ViewException(e.getMessage(), e);
        }
    }

}
