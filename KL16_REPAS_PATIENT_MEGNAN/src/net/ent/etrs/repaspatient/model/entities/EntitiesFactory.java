package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public class EntitiesFactory {

    //Attributs


    //Constructeur
    public EntitiesFactory() {
    }

    //Autres methodes
    public static Repas fabriquerRepas(LocalDate dateRepas, TypeRepas typeRepas) throws RepasException {
        return new Repas(dateRepas, typeRepas);
    }

    public static Patient fabriquerPatient(String numSecu, String nom, String prenom, LocalDate dateEntre) throws PatientException {
        return new Patient(dateEntre, numSecu, nom, prenom);
    }
}
