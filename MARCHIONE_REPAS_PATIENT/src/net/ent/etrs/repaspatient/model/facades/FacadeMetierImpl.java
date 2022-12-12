package net.ent.etrs.repaspatient.model.facades;

import net.ent.etrs.repaspatient.model.daos.IPatientMemDao;
import net.ent.etrs.repaspatient.model.daos.IRepasMemDao;
import net.ent.etrs.repaspatient.model.entities.references.Patient;
import net.ent.etrs.repaspatient.model.entities.references.Repas;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Facade metier.
 */
public class FacadeMetierImpl {

    /**
     * Instantiates a new Facade metier.
     */
    protected FacadeMetierImpl() {
    }


    private IRepasMemDao repasDao;

    private IPatientMemDao patientDao;


    /**
     * Supprimer patient.
     *
     * @param patient le patient
     */
    public void supprimerPatient(Patient patient) {

    }

    /**
     * Lister repas.
     */
    public  void listerRepas () {
        List<Repas> liserRepas = new ArrayList<>();
    }

    /**
     * Mettre à jour patient.
     *
     * @param patient the patient
     */
    public void mettreAJourPatient(Patient patient) {

    }

    /**
     * Init.
     */
    public void init() {

    }

    /**
     * Recuperer patient par id.
     *
     * @param id l'id
     */
    public void recupererPatientById(String id) {

    }

    /**
     * Sauvegarder patient.
     *
     * @param patient le patient
     */
    public void sauvegarderPatient(Patient patient) {

    }


}
