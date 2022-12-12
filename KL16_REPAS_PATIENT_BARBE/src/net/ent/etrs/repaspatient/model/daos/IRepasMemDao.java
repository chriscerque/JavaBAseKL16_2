package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.Set;


public interface IRepasMemDao {

    Repas create(final Repas repas) throws DaoException;


    Repas update(final Repas repas) throws DaoException;


    Repas read(final String libelle) throws DaoException;


    void delete(final String libelle) throws DaoException;


    Set<Repas> readAll();


    boolean exist(final Repas repas) throws DaoException;
}