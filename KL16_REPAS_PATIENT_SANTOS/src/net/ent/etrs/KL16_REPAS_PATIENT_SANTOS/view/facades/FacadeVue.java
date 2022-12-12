package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.view.facades;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.Patient;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.Repas;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.view.facades.exception.ViewException;

import java.util.List;

public interface FacadeVue {

    /**
     * Permet d'afficher le Menu principal
     */
    public int afficherMenu();
    /**
     * Permet d'initialiser le menu principal
     * @return la liste pour le Menu principal
     */
    public List<String> initMenuPrincipal();


    /**
     *
     *
     * @return le patient modifier
     */
    public Patient selectionnerPatient (List<Patient> patients) throws ViewException;


    public Repas selectionnerRepas (List<Repas> repas) throws ViewException;


    public Patient afficherPatient (List<Patient> patients) throws ViewException;



    /**
     * Permet d'afficher les message d'exception
     * @param message le message qui vient de l'exception
     */
    public void afficherErreur(String message);


    public void afficherMessage(String msg);
}



















