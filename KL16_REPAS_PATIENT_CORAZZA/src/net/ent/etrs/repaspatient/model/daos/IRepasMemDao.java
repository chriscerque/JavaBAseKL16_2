package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

public interface IRepasMemDao {

    Repas read(String id) throws DaoException;

    void deleteByKey(String id) throws DaoException;

    void delete(Repas repas) throws DaoException;

    List<Repas> readAll();

    Repas update(Repas repas) throws DaoException;

    boolean exist(Repas repas) throws DaoException;

    default void init() {
        throw new UnsupportedOperationException();
    }

    Repas create(Repas repas) throws DaoException;


}
