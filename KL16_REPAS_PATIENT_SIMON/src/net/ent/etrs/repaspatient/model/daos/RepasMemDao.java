package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RepasMemDao implements IRepasMemDao{

    protected RepasMemDao() {
    }

    private List<Repas> persistence = new ArrayList<>();

    @Override
    public Repas create(final Repas repas) throws DaoException {
        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (exist(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
        }
        this.persistence.add(repas);
        return this.read(repas.getId());
    }

    @Override
    public Repas update(final Repas repas) throws DaoException {
        try {
            this.persistence.remove(repas);
            this.persistence.add(repas);
        }catch (Exception e) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_INEXISTANT);
        }
        return this.read(repas.getId());
    }

    @Override
    public Repas read(final String id) {
        Repas p = null;
        for (Repas repas : this.persistence) {
            if(repas.getId().equals(id)) {
                p = repas;
            }
        }
        return p;
    }

    @Override
    public void delete(final String id) throws DaoException {
        Repas b = this.read(id);
        if (!exist(b)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
        }
        this.persistence.remove(b);

    }

    @Override
    public List<Repas> readAll() {
        return Collections.unmodifiableList(this.persistence);
    }

    @Override
    public boolean exist(final Repas repas) throws DaoException {
        try {
            return this.persistence.contains(repas);
        } catch (Exception e) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT, e);
        }
    }
}
