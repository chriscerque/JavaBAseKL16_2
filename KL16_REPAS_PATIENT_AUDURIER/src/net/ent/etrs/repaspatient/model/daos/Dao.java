package net.ent.etrs.repaspatient.model.daos;


import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;

import java.util.List;

public interface Dao<T, K> {
    T save(T t) throws DaoException;

    T read(K k) throws DaoException;

    List<T> readAll();

    void delete(T t) throws DaoException;

    void deleteByKey(K k) throws DaoException;

    boolean exist(T t) throws DaoException;

    void init() throws DaoException;
}
