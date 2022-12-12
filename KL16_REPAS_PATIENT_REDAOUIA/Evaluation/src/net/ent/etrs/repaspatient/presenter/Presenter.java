package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.daos.DaoFactory;
import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.model.facades.FacadeMetierFactory;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;
import net.ent.etrs.repaspatient.view.FacadeView;
import net.ent.etrs.repaspatient.view.FacadeViewFactory;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Presenter {
    static FacadeView fv = FacadeViewFactory.fabriquerFacadeView();

    static FacadeMetier fm = FacadeMetierFactory.fabriquerFacadeMetier();

    public Presenter(FacadeView fv, FacadeMetier fm) {
        this.fv = fv;
        this.fm = fm;
    }

    public static void main(String[] args) {

        exec();

    }

    public static void exec() {
        try {
            fm.init();
        }
        catch (BusinessException e) {
            AffichageConsole.afficherErreur(e.getMessage());
        }
        menuPrincipal();
    }


    public static void menuPrincipal() {
        int result = 0;
        do {
            result = fv.afficherMenu();
            traitementChoix(result);
        } while (result != 0);

    }

    private static void traitementChoix(int choix) {
        switch (choix){
            case 1:
                //creerPatient
                creerPatient();
                break;
            case 2:
                //modifierPatient
              modifierPatient();
                break;
            case 3:
                //supprimerPatient
                supprimerPatient();
                break;
            case 4:
                //listerPatient
                listerPatient();
                break;
            case 5:
                //ajouterRepasPatient
                ajouterRepasPatient();
                break;
            default:
                break;
        }

    }

    private static void ajouterRepasPatient() {
        listerPatient();
        fv.afficherMessage("entrez le numero du patient");
        String numSecu = LectureConsole.lectureChaineCaracteres();
        List<Patient> tempLstPatient = new ArrayList<>(fm.listerPatients());
        for (Patient p: tempLstPatient){
            if (numSecu == p.getNumSecu()){
                fv.afficherMessage("Entrez une date : ");
                LocalDate dateRepas = LectureConsole.lectureLocalDate("dd/MM/yyyu");
                fv.afficherMessage("Entrez le nom d'un régime :");
                String regime = LectureConsole.lectureChaineCaracteres();
                for (TypeRepas tr: TypeRepas.values()){
                    if (tr == TypeRepas.valueOf(regime)){
                        try {
                            p.ajouterRepas(EntitiesFactory.fabriquerRepas(dateRepas, tr));
                        } catch (RepasException | RegimeAlimentaireException e) {
                           fv.afficherMessageErreur(e.getMessage());
                        }
                    }
                }

            }
        }
    }

    private static void supprimerPatient()  {
        fv.afficherMessage(String.format(ConstantesVue.SAISIR_TYPE_MSG, "numero de securite"));
        String numSecu = LectureConsole.lectureChaineCaracteres();
        List<Patient> tempLstPatient = new ArrayList<>(fm.listerPatients());
        for (Patient p : tempLstPatient){
            if (p.getNumSecu() == numSecu){
                try {
                    fm.supprimerPatient(p);
                } catch (BusinessException | DaoException e) {
                   fv.afficherMessageErreur(e.getMessage());
                }
            }
        }

    }

    private static void modifierPatient() {
        listerPatient();

    }

    private static void listerPatient() {
        //TODO a corriger
        fv.afficherPatients(fm.listerPatients());
    }


    private static void creerPatient() {
        fv.afficherMessage(String.format(ConstantesVue.SAISIR_TYPE_MSG, "numero de securite"));
        String numSecu = LectureConsole.lectureChaineCaracteres();
        fv.afficherMessage(String.format(ConstantesVue.SAISIR_TYPE_MSG, "le nom : "));
        String nom = LectureConsole.lectureChaineCaracteres();
        fv.afficherMessage(String.format(ConstantesVue.SAISIR_TYPE_MSG, "le prenom : "));
        String prenom= LectureConsole.lectureChaineCaracteres();
        fv.afficherMessage(String.format(ConstantesVue.SAISIR_TYPE_MSG, "la date d'entrée"));
        LocalDate dateEntree = LectureConsole.lectureLocalDate("dd/MM/yyyy");

        try {
            fm.sauvegarderPatient(EntitiesFactory.fabriquerPatient(numSecu, nom, prenom, dateEntree));
        } catch (BusinessException | DaoException | PatientConstructionException e) {
            fv.afficherMessage(e.getMessage());
        }


    }
}
