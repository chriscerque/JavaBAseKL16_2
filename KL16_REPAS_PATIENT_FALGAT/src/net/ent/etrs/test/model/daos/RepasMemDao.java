package net.ent.etrs.test.model.daos;

import net.ent.etrs.test.model.daos.exception.DaoException;
import net.ent.etrs.test.model.entities.Patient;
import net.ent.etrs.test.model.entities.Repas;
import net.ent.etrs.test.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RepasMemDao implements IRepasMemDao{

    protected RepasMemDao() {}

    private List<Repas> persistence = new ArrayList<>();

    @Override
    public List<Repas> readAll() throws DaoException {
        return Collections.unmodifiableList(this.persistence);
    }

    @Override
    public Repas create(Repas repas) throws DaoException {
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
    public void delete(Repas repas) throws DaoException {
        if (!exist(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
        }
        this.persistence.remove(repas);
    }

    @Override
    public void deleteByKey(String id) throws DaoException {
        Repas r = this.read(id);
        if (!exist(r)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
        }
        this.persistence.remove(r);
    }

    @Override
    public boolean exist(Repas repas) throws DaoException {
        try {
            return this.persistence.contains(repas);
        } catch (Exception e) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT, e);
        }
    }

    @Override
    public Repas read(String id) throws DaoException {
        Repas r = null;
        for (Repas repas : this.persistence) {
            if(repas.getId().equals(id)) {
                r = repas;
            }
        }
        return r;
    }

    @Override
    public void init() {

    }

    @Override
    public Repas update(Repas repas) throws DaoException {
        try {
            this.persistence.remove(repas);
            this.persistence.add(repas);
        }catch (Exception e) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT);
        }
        return this.read(repas.getId());
    }
}
