package net.ent.etrs.kl16repaspatientgouin.model.daos;

import net.ent.etrs.kl16repaspatientgouin.model.daos.exceptions.DaoException;
import net.ent.etrs.kl16repaspatientgouin.model.entities.Repas;
import net.ent.etrs.kl16repaspatientgouin.model.facades.exceptions.BusinessException;

import java.util.List;

public interface IRepasMemDao {

    Repas create (final Repas repas) throws DaoException;

    Repas read(final String id) throws DaoException;

    Repas update (final Repas repas) throws DaoException;

    void delete(final Repas repas) throws DaoException;

    void deletebyKey(final String id) throws DaoException;

    List<Repas> readAll();

    void init() throws  DaoException;

    boolean exist(final Repas repas);
}
