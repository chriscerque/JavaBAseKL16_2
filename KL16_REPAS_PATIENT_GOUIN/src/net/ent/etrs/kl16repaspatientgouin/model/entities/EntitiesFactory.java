package net.ent.etrs.kl16repaspatientgouin.model.entities;

import net.ent.etrs.kl16repaspatientgouin.model.entities.exceptions.EntitiesFactoryException;
import net.ent.etrs.kl16repaspatientgouin.model.entities.exceptions.PatientException;
import net.ent.etrs.kl16repaspatientgouin.model.entities.exceptions.RepasException;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.ConstantesMetier;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.TypeRepas;

import java.time.LocalDate;

public class EntitiesFactory {

    private EntitiesFactory() {
    }

    public static Repas fabriquerRepas(final LocalDate dateRepas,final TypeRepas typeRepas) throws EntitiesFactoryException {

        try {
            Repas repas = new Repas(dateRepas, typeRepas);
            return repas;
        } catch (RepasException e) {
            throw new EntitiesFactoryException(ConstantesMetier.MSG_ENTITIES_CREATION_REPAS, e);
        }
    }

    public static Patient fabriquerPatient(final  String numSecu,final  String nom,final  String prenom, final LocalDate dateEntree) throws EntitiesFactoryException {
        try {
            Patient patient = new Patient(numSecu, nom, prenom, dateEntree);
            return patient;
        } catch (PatientException e) {
            throw new EntitiesFactoryException(ConstantesMetier.MSG_ENTITIES_CREATION_REPAS, e);
        }
    }
}
