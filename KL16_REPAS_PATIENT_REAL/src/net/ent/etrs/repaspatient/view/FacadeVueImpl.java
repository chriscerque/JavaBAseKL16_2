package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.view.exceptions.ViewException;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class FacadeVueImpl implements FacadeVue{

    /**
     * Permet d'afficher le msg
     * @param msg le message à afficher.
     */
    @Override
    public void afficherMessage(String msg) {
        AffichageConsole.afficherMessageAvecSautLigne(msg);
    }

    /**
     * Permet d'afficher le msg d'erreur
     * @param msg le message à afficher.
     */
    @Override
    public void afficherMessageErreur(String msg) {
        AffichageConsole.afficherErreur(msg);
    }

    /**
     * Permet d'afficher le menu
     * @return
     */
    @Override
    public int afficherMenu() {
        List<String> initMenu = new ArrayList<>(initMenuPrincipal());
        AffichageConsole.afficherMenuSimple(initMenu);
        int choix = LectureConsole.lectureChoixInt(0,initMenu.size());
        return choix;
    }

    /**
     * Permet d'initialiser le menu
     * @return
     */
    public List<String> initMenuPrincipal() {
        List<String> retour = new ArrayList<>();
        retour.add("Lister les patients");
        retour.add("Créer un nouveau patient");
        retour.add("Modifier un patient");
        retour.add("Supprimer un patient");
        retour.add("Ajouter un repas à un patient");
        return retour;
    }


    /**
     * Permet d'afficher la liste des patients
     * @param lstPatients la liste des patients à afficher.
     * @return
     * @throws ViewException
     */
    @Override
    public List<Patient> afficherPatients(List<Patient> lstPatients) throws ViewException {
//        return Collections.unmodifiableList(lstPatients);
            if (lstPatients.size() == 0) {
                throw new ViewException(ConstantesVue.MSG_AUCUN_PATIENT);
            }
            for (Patient patient : lstPatients) {
                AffichageConsole.afficherMessageAvecSautLigne(String.format("Patient %s %s (%s)",patient.getNumSecu(),patient.getNom(),patient.getPrenom()));
                AffichageConsole.afficherMessageAvecSautLigne("     Liste des régimes alimentaires : ");
//                AffichageConsole.afficherMessageAvecSautLigne("     %s",patient.getLstRegimeAlimentaire());
//                AffichageConsole.afficherMessageAvecSautLigne("     %s",patient.getLstRepas());
            }
        return Collections.unmodifiableList(lstPatients);

    }

    /**
     * Affiche | controle | création d'un patient
     * @return
     * @throws ViewException
     * @throws RegimeAlimentaireException
     */
    @Override
    public Patient saisirPatient() throws ViewException, RegimeAlimentaireException {
        Patient pat = null;
            String id = LectureConsole.lectureChaineCaracteres("Quel est l'identifiant du patient ?");
            String numSecu = LectureConsole.lectureChaineCaracteres("Quel est le numéro de securité social du patient ?");
            String nom = LectureConsole.lectureChaineCaracteres("Quel est le nom du patient ?");
            String prenom = LectureConsole.lectureChaineCaracteres("Quel est le prenom du patient ?");
            LocalDate dateEntree = LectureConsole.lectureLocalDate("Quel est la date d'entrée ?", "dd/MM/yyyy");
            Boolean ajoutRegimeAlimentaire = LectureConsole.lectureBoolean("Voulez-vous ajouter un régime alimentaire ?");

            if (id.equals(null) || numSecu.equals(null) || nom.equals(null)) {
                throw new ViewException(ConstantesVue.MSG_CHAMP_VIDE);
            }
            if (!ajoutRegimeAlimentaire) {
                if (numSecu.equals(5) && nom.length() > 3 && nom.length() < 51 && !dateEntree.isAfter(LocalDate.now())) {
                    return EntitiesFactory.fabriquerPatient(id, numSecu, nom, dateEntree);
                }
            } else {
                RegimeAlimentaire r = demanderChoixRegimeAlimentaire();
                Patient patientWithoutCheck = EntitiesFactory.fabriquerPatient(numSecu, nom, prenom, dateEntree);
                if (patientWithoutCheck.getLstRegimeAlimentaire().contains(r)) {
                    afficherMessage(ConstantesVue.MSG_REGIME_DEJA_PRESENT);
                }
                Patient p = EntitiesFactory.fabriquerPatient(numSecu, nom, prenom, dateEntree);
                p.ajouterRegimeAlimentaire(r);
                afficherMessage("Vous avez créer le patient " + p.getNom() + " avec un regime " + r.getLibelle());

                return p;
            }
            pat = EntitiesFactory.fabriquerPatient(numSecu, nom, prenom, dateEntree);
            return pat;

    }

    /**
     * Selection d'un patient
     * @param lstPatients la liste des patients à afficher.
     * @return
     * @throws BusinessException
     */
    @Override
    public Patient selectionnerPatient(List<Patient> lstPatients) throws BusinessException {
        if(lstPatients.size() == 0){
            throw new BusinessException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        List<String> lstPatientTemp = new ArrayList<>();
        for (Patient pat : lstPatients) {
            lstPatientTemp.add(pat.getNom());
        }
        AffichageConsole.afficherMenuSimple(lstPatientTemp);
        int choix = LectureConsole.lectureChoixInt(1,lstPatientTemp.size());
        Patient p = lstPatients.get(choix-1);

        return p;
    }

    /**
     * Permet de selectionner parmis l'énumération des régimes
     * @return
     * @throws RegimeAlimentaireException
     */
    public static RegimeAlimentaire demanderChoixRegimeAlimentaire() throws RegimeAlimentaireException {
        List<String> lstLibelleRegimeAlimentaire = RegimeAlimentaire.getLstLibelleRegimeAlimentaire();
        AffichageConsole.afficherMenuSimple(lstLibelleRegimeAlimentaire);
        int choixRegime = LectureConsole.lectureChoixInt(1, lstLibelleRegimeAlimentaire.size());
        //Je récupère le libellé dans la liste en fonction du choix de l'utilisateur
        String libelleRegime = lstLibelleRegimeAlimentaire.get(choixRegime - 1);
        return RegimeAlimentaire.getRegimeByLibelle(libelleRegime);
    }

    public void selectionnerRegimeAlimentaire(){

    }

    /**
     * Permet d'afficher un patient
     * @param patients le patient à afficher.
     * @return
     * @throws BusinessException
     */
    @Override
    public Patient afficherPatient(List<Patient> patients) throws BusinessException {
        if(patients.size() == 0){
            throw new BusinessException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        List<String> lstPatientTemp = new ArrayList<>();
        for (Patient pat : patients) {
            lstPatientTemp.add(pat.getNom());
        }
        AffichageConsole.afficherMenuSimple(lstPatientTemp);
        int choix = LectureConsole.lectureChoixInt(1,lstPatientTemp.size());
        Patient p = patients.get(choix-1);

        return p;
    }

    /**
     * Permet de selectionner un repas
     * @param lstRepas la liste des repas à afficher.
     * @return
     * @throws BusinessException
     */

    @Override
    public List<Repas> selectionnerRepas(List<Repas> lstRepas) throws BusinessException {
        if(lstRepas.size() == 0){
            throw new BusinessException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        List<String> lstRepasTemp = new ArrayList<>();
        for (Repas repas : lstRepas) {
            lstRepasTemp.add(String.valueOf(repas.getTypeRepas()));
        }
        AffichageConsole.afficherMenuSimple(lstRepasTemp);
        int choix = LectureConsole.lectureChoixInt(1,lstRepasTemp.size());
        Repas rp = lstRepas.get(choix-1);

        return (List<Repas>) rp;
    }

    /**
     * Permet de modifier un patient
     * @param patient
     * @return
     * @throws ViewException
     */

    @Override
    public Patient modifierPatient(Patient patient) throws ViewException {
        return patient;
    }

    @Override
    public Patient ajouterRepasPatient(List<Patient> lstPatients, List<Repas> listRepas) throws ViewException {
            if(lstPatients.size() == 0){
                throw new ViewException(ConstantesMetier.MSG_PATIENT_CREATION_EXCEPTION);
            }
            List<String> vtmp = new ArrayList<>();
            for (Repas r : listRepas) {
                listRepas.add(r);
            }
            AffichageConsole.afficherMenuSimple(vtmp);
            int choix = LectureConsole.lectureChoixInt(1,vtmp.size());
            Patient v = lstPatients.get(choix-1);
            return v;
        }

}
