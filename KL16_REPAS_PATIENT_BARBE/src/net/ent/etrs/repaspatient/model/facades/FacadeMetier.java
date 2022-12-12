/**
 *
 */
package net.ent.etrs.repaspatient.model.facades;


import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;

import java.util.List;

/**
 * Façade métier proposant les opérations utiles
 * à l'application.
 *
 * @author christophe.cerqueira
 */
public interface FacadeMetier {


    List<Patient> listerPatients();


    List<Repas> listerRepas();


    void sauvegarderPatient(Patient patient) throws BusinessException;


    void supprimerPatient(Patient patient) throws BusinessException;


    void mettreAJourPatient(Patient patient) throws BusinessException;

    void init() throws BusinessException;

    Patient recupererPatientById(String patientId) throws BusinessException;
}
