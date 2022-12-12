package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;

public final class EntitiesFactory {
    private EntitiesFactory() { }

    public static Repas fabriquerRepas(LocalDate date, TypeRepas type) throws RepasException {
        try {
            Repas r = new Repas(date, type);
            return r;
        } catch (Exception e) {
            throw new RepasException(e);
        }
    }

    public static Patient fabriquerPatient(LocalDate date, String numSecu, String nom, String prenom) throws PatientException {
        try {
            Patient p = new Patient(date,numSecu,nom,prenom);
            return p;
        } catch (Exception e) {
            throw new PatientException(e);
        }
    }


}
