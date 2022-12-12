package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.List;


public interface IRepasMemDao {


    Repas create(final Repas repas) throws DaoException;

    List<Repas> readAll();

    Repas update(final Repas repas) throws DaoException;

    void delete(final String id) throws DaoException;


    void delete(final LocalDate dateRepas, final TypeRepas typeRepas) throws DaoException;

    Repas read(final LocalDate dateRepas, final TypeRepas typeRepas);

}

