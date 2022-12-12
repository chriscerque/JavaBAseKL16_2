package net.ent.etrs.repaspatient.model.entities.comparator;

import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.Comparator;

/**
 * Compare patient by name first the first name.
 */
public class PatientNomPrenomComparator implements Comparator<Patient> {
    @Override
    public int compare(final Patient o1, final Patient o2) {
        int result = o1.getNom().compareTo(o2.getNom());
        return result == 0 ? o1.getPrenom().compareTo(o2.getPrenom()) : result;
    }
}
