package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities;

import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.excetions.EntitiesFactoryException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.references.TypeRepas;

import java.time.LocalDate;

public class EntitiesFactory {
    public EntitiesFactory() {
    }

    public static Repas fabriquerRepas(final LocalDate dateRepas, final TypeRepas typeRepas) throws EntitiesFactoryException {

        return fabriquerRepas(dateRepas, typeRepas);
    }

    public static Patient fabriquerPatient(final LocalDate dateEntree, final String numSecu, final String nom, final String prenom) throws EntitiesFactoryException {
        return fabriquerPatient(dateEntree, numSecu, nom, prenom);

    }


}
