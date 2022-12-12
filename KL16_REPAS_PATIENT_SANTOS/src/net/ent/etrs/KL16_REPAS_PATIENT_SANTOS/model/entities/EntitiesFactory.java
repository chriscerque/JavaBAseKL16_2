package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.RepasException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references.ConstantesMetier;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references.TypeRepas;

import java.time.LocalDate;
import java.util.UUID;

public class EntitiesFactory {
    private EntitiesFactory() {
    }
    public static Repas fabriquerRepas(LocalDate dateRepas, TypeRepas typeRepas) throws RepasException {


        try {
            return new Repas(dateRepas,typeRepas);
        } catch ( RepasConstructionException e) {
            throw new RepasException(ConstantesMetier.REPAS_CONSTRUCTION_EXCEPTION);
        }
    }






    public static Patient fabriquerPatient(String nom , String prenom ,String numSecu,LocalDate dateEntre )   {
        Patient retour = null;


        Patient patient = null;
        try {
            patient = new Patient(dateEntre,numSecu,nom,prenom);
        } catch (PatientConstructionException e) {
            throw new RuntimeException(e);
        }

        retour = patient;



        return retour;

    }
}


