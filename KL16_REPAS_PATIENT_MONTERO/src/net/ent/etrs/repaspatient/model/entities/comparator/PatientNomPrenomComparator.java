package net.ent.etrs.repaspatient.model.entities.comparator;

import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.Comparator;

public class PatientNomPrenomComparator implements Comparator<Patient> {

    /**
     * Trie un patient dabord par nom puis par prenom.
     * utilisation de to lowercase pour uniformiser la comparaison/trie.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return
     */
    @Override
    public int compare(final Patient o1, final Patient o2) {
        int comp = o1.getNom().toLowerCase().compareTo(o2.getNom().toLowerCase());
        if (comp == 0) {
            comp = o1.getPrenom().toLowerCase().compareTo(o2.getPrenom().toLowerCase());
        }
        return comp;
    }
}
