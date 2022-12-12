package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DaoRepasImpl implements DaoRepas{

    private List<Repas> persistance = new ArrayList<>();

    protected DaoRepasImpl() {
    }

    @Override
    public Repas save(final Repas repas) throws DaoException {
        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (exist(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
        }
        this.persistance.add(repas);
        return this.read(repas.getId());
    }

    @Override
    public Repas read(final String id) throws DaoException {
        Repas r = null;
        for (Repas repas : this.persistance) {
            if(repas.getId().equals(id)) {
                r = repas;
            }
        }
        return r;
    }

    @Override
    public List<Repas> readAll() {
        return Collections.unmodifiableList(this.persistance);
    }

    @Override
    public Repas update(final Repas repas) throws DaoException {
        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (!exist(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MODIF_REPAS_INEXISTANT);
        }
        this.persistance.remove(repas);
        this.persistance.add(repas);
        return this.read(repas.getId());
    }

    @Override
    public void delete(final Repas repas) throws DaoException {
        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_NULL);
        }
        if (!exist(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
        }
        this.persistance.remove(repas);
    }

    @Override
    public void deleteByKey(final String id) throws DaoException {
        this.delete(read(id));
    }

    @Override
    public boolean exist(final Repas repas) throws DaoException {
        try {
            return this.persistance.contains(repas);
        } catch (Exception e) {
            throw new DaoException(ConstantesMetier.MSG_DAO_REPAS_INEXISTANT, e);
        }
    }

    @Override
    public void init() throws DaoException {

    }
}
