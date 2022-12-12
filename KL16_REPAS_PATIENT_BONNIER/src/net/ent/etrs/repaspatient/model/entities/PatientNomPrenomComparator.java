package net.ent.etrs.repaspatient.model.entities;

import java.util.Comparator;

public class PatientNomPrenomComparator implements Comparator<Patient> {
    /**
     * Permet de trier d'abord par rapport au nom puis s'ils sont identiques compare par rapport au prénom.
     */
    @Override
    public int compare(Patient o1, Patient o2) {
        int comp = o1.getNom().compareTo(o2.getNom());
        if (comp == 0) {
            comp = o1.getPrenom().compareTo(o2.getPrenom());
        }
        return comp;
    }
}
