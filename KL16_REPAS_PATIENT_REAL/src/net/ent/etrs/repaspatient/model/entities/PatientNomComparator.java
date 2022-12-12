package net.ent.etrs.repaspatient.model.entities;

import java.util.Comparator;

public class PatientNomComparator implements Comparator<Patient> {

    /**
     * Permet d'afficher dans l'odre des nom des patients.
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return
     */
    @Override
    public int compare(Patient o1, Patient o2) {
        return o1.getNom().compareTo(o2.getNom());
    }
}
