package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public final class EntitiesFactory {


    //constructeur(s)

    private EntitiesFactory() {
    }


    //autre(s) methode(s)

    public static Repas fabriquerRepas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasConstructionException {
        try {
            Repas repas = new Repas(dateRepas, typeRepas);
            return repas;
        } catch (RepasException e) {
            throw new RepasConstructionException(ConstantesMetier.REPAS_CONSTRUCTION_EXCEPTION, e);
        }
    }

    public static Patient fabriquerPatient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree) throws PatientConstructionException {
        try {
            Patient patient = new Patient(numSecu, nom, prenom, dateEntree);
            return patient;
        } catch (PatientException e) {
            throw new PatientConstructionException(ConstantesMetier.PATIENT_CONSTRUCTION_EXCEPTION, e);
        }
    }


}