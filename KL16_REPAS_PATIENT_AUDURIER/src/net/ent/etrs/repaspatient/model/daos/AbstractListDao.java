package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.daos.exceptions.references.ConstantesDaoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AbstractListDao<T extends Identifiable<K>, K> implements DaoInter<T, K> {
    private List<T> persistance = new ArrayList<>();


    //Methods
    private void paramNullChecker(final Object entity) throws DaoException {
        if (Objects.isNull(entity)) {
            throw new DaoException(ConstantesDaoException.DAO_OBJECT_NULL);
        }
    }

    @Override
    public T save(final T t) throws DaoException {
        paramNullChecker(t);
        if (this.persistance.contains(t)) {
            persistance.set(persistance.indexOf(t), t);
        } else {
            this.persistance.add(t);
        }
        return this.read(t.getId());
    }

    @Override
    public T read(final K k) throws DaoException {
        paramNullChecker(k);
        for (T t : persistance) {
            if (t.getId().equals(k)) {
                return t;
            }
        }
        throw new DaoException(ConstantesDaoException.DAO_OBJECT_NOT_EXIST);
    }

    @Override
    public List<T> readAll() {
        return Collections.unmodifiableList(persistance);
    }

    @Override
    public void delete(final T t) throws DaoException {
        paramNullChecker(t);
        List<Object> listeRafaleTemporaire = new ArrayList<>(persistance);
        for (Object tLst : listeRafaleTemporaire) {
            if (tLst.equals(t)) {
                persistance.remove(t);
            }
        }
    }

    @Override
    public void deleteByKey(final K k) throws DaoException {
        paramNullChecker(k);
        this.delete(this.read(k));
    }

    @Override
    public boolean exist(final T t) throws DaoException {
        paramNullChecker(t);
        return persistance.contains(t);
    }

    @Override
    public void init() throws DaoException {

    }
}
