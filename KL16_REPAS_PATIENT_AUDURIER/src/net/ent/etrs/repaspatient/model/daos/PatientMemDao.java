package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.comparator.PatientNomPrenomComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatientMemDao extends AbstractListDao<Patient, String> {
    protected PatientMemDao() {
    }

    @Override
    public List<Patient> readAll() {
        List<Patient> lst = new ArrayList<>(super.readAll());
        Collections.sort(lst, new PatientNomPrenomComparator());
        return lst;
    }
}
