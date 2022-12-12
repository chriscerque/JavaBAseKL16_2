package net.ent.etrs.kl16repaspatientgouin.model.entities;

import java.util.Comparator;

public class PatientNomPrenomComparator implements Comparator<Patient> {
    @Override
    public int compare(Patient o1, Patient o2) {
        if((o1.getNom().compareTo(o2.getNom()) == 0)){
            return o1.getPrenom().compareTo(o2.getPrenom());
        }

        return (o1.getNom().compareTo(o2.getNom()));
    }
}
