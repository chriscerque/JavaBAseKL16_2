package net.ent.etrs.repaspatient.model.comparator;

import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.Comparator;

public class RepasDateTypeComparator implements Comparator<Repas> {
    @Override
    public int compare(Repas o1, Repas o2) {
        int comp = o1.getDateRepas().compareTo(o2.getDateRepas());
        if (comp == 0) {
            comp = o1.getTypeRepas().compareTo(o2.getTypeRepas());
        }
        return comp;
    }
}
