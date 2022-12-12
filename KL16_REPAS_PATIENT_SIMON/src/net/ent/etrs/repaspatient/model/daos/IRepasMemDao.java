package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

public interface IRepasMemDao {
    Repas create(final Repas repas) throws DaoException;
    Repas update(final Repas repas) throws DaoException;

    Repas read(final String id);
    void delete(final String id) throws DaoException;
    List<Repas> readAll();


    boolean exist(Repas repas) throws DaoException;
}
