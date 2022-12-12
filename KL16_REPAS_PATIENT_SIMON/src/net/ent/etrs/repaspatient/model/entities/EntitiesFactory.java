package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public class EntitiesFactory {

    private EntitiesFactory() {
    }

    public static Repas fabriquerRepas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasConstructionException {
        try {
            return new Repas(dateRepas,typeRepas);
        }catch (RepasException e ){
            throw new RepasConstructionException(ConstantesMetier.MSG_CONSTRUCTION_REPAS);
        }

    }

    public static Patient fabriquerPatient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree) throws PatientConstructionException {
        try {
            return new Patient(dateEntree,numSecu,nom,prenom);
        }catch (PatientException e){
            throw new PatientConstructionException(ConstantesMetier.MSG_CONSTRUCTION_PATIENT);
        }
    }
}
