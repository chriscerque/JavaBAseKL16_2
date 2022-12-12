package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

public interface DaoRepas {

    Repas save(Repas repas) throws DaoException;

    Repas read(String id) throws DaoException;

    List<Repas> readAll();

    Repas update(Repas repas) throws DaoException;

    void delete(Repas repas) throws DaoException;

    void deleteByKey(String id) throws DaoException;

    boolean exist(Repas repas) throws DaoException;

    void init() throws DaoException;
}
