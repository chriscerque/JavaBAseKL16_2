package net.ent.etrs.repaspatient.view;

import net.ent.etrs.repaspatient.model.entities.Patient;

public interface FacadeView {
    int AffichageMenu();

    void AffichageEnteteListePatient();

    void AffichageListePatient(Patient patient);

    void AffichageConstante(String s);

    void SaisirInfoPatient();


    void AfficherErreur(String s);

}
