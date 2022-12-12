package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.commons.utils.LectureConsole;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.view.references.ConstantesVue;

import java.util.Arrays;

public class FacadeViewImpl implements FacadeView {

    @Override
    public int AffichageMenu() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(Arrays.asList(ConstantesVue.MENU),"Repas patient");
        return LectureConsole.lectureChoixInt(0, ConstantesVue.MENU.length);
    }

    @Override
    public void AffichageEnteteListePatient() {
        AffichageConsole.afficherMessageAvecSautLigne(String.format("%-20s | %-20s | %-15s | %-5s", "Date entrée", "numéro de sécurité", "nom", "prénom"));
    }

    @Override
    public void AffichageListePatient(final Patient p) {
        AffichageConsole.afficherMessageAvecSautLigne(String.format("%-20s | %-20s | %-15s | %s", p.getDateEntree(), p.getNumSecu(), p.getNom() ,p.getPrenom()));
    }

    @Override
    public void AffichageConstante(final String s) {
        AffichageConsole.afficherMessageAvecSautLigne(s);

    }

    @Override
    public void SaisirInfoPatient() {
        LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_DATE_ENTREE);
        LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_NUMERO_DE_SECURITE);
        LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_NOM);
        LectureConsole.lectureChaineCaracteres(ConstantesVue.SAISIR_PRENOM);
    }

    @Override
    public void AfficherErreur(final String s) {
        AffichageConsole.afficherErreur(s);
    }


}
