package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;

import java.util.List;
import java.util.UUID;

public interface IRepasMemsDao {

    public abstract UUID read(final UUID id) throws RepasConstructionException, DaoException;

    public abstract void deleteByKey(final UUID id);

    public abstract void delete(final Repas r);

    public abstract List<Repas> readAll();

    public abstract Repas update(final Repas r) throws DaoException;

    public abstract boolean exists(final Repas r);

    public abstract void init();

    public abstract Repas create(final Repas r) throws DaoException;


}
