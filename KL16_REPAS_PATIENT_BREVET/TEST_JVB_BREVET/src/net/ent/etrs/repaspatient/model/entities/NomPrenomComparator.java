package net.ent.etrs.repaspatient.model.entities;

import java.util.Comparator;

public class NomPrenomComparator implements Comparator<Patient> {
    @Override
    public int compare(final Patient o1, final Patient o2) {
        if (o1.getNom().compareTo(o2.getNom()) == 0) {
            return o1.getPrenom().compareTo(o2.getPrenom());
        } else {
            return o1.getNom().compareTo(o2.getNom());
        }
    }
}
