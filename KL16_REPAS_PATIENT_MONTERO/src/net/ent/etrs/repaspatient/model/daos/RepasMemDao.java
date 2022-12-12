package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.daos.references.ConstantesDao;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RepasMemDao implements IRepasMemDao {

    //attribut(s)

    private List<Repas> persistence = new ArrayList<>();

    //constructeur(s)

    protected RepasMemDao() {
    }


    //super(interface) methode(s)


    @Override
    public Repas create(final Repas pRepas) throws DaoException {
        if (Objects.isNull(pRepas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (exist(pRepas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
        }
        this.persistence.add(pRepas);
        return pRepas;
    }

    @Override
    public Repas read(final String id) throws DaoException {
        if (Objects.isNull(id)) {
            throw new DaoException(ConstantesDao.MSG_DAO_ID_NULL);
        }
        for (Repas repas : this.persistence) {
            if (repas.getId().equals(id)) {
                return repas;
            }
        }
        throw new DaoException(ConstantesDao.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
    }

    @Override
    public void deleteByKey(final String id) throws DaoException {
        if (Objects.isNull(id)) {
            throw new DaoException(ConstantesDao.MSG_DAO_ID_NULL);
        }
        Repas repas = this.read(id);
        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (!exist(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
        }
        this.persistence.remove(repas);

    }

    @Override
    public void delete(final Repas pRepas) throws DaoException {
        if (Objects.isNull(pRepas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (!exist(pRepas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
        }
        this.persistence.remove(pRepas);

    }

    @Override
    public Repas update(final Repas pRepas) throws DaoException {
        if (Objects.isNull(pRepas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        try {
            this.persistence.remove(pRepas);
            this.persistence.add(pRepas);
        } catch (Exception e) {
            throw new DaoException(ConstantesDao.MSG_DAO_PERSITANCE_REPAS_MODIFICATION);
        }
        return this.read(pRepas.getId());
    }

    @Override
    public List<Repas> readAll() {
        return Collections.unmodifiableList(this.persistence);
    }

    @Override
    public boolean exist(final Repas pRepas) throws DaoException {
        if (Objects.isNull(pRepas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        return this.persistence.contains(pRepas);
    }

    @Override
    public void init() {

    }
}
