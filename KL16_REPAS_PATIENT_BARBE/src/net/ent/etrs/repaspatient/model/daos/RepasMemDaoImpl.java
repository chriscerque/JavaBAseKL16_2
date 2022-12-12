package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.ConstantesException;

import java.util.*;

public class RepasMemDaoImpl implements IRepasMemDao {

    private final Set<Repas> persistance = new LinkedHashSet<>();

    @Override
    public Repas create(final Repas repas) throws DaoException {
        if (!Objects.isNull(repas) || persistance.add(repas)) {
            return repas;
        } else throw new DaoException(ConstantesException.DAO_REPAS_IMPOSSIBLE_CREATE);

    }

    @Override
    public Repas update(final Repas repas) throws DaoException {
        if (!Objects.isNull(repas) || persistance.contains(repas)) {
            persistance.remove(repas);
            persistance.add(repas);
            return repas;
        }
        throw new DaoException(ConstantesException.DAO_REPAS_IMPOSSIBLE_UPDATE);
    }

    /**
     * Lis le repas en fonction de son id.
     *
     * @param libelle l'id
     * @return le Repas
     */
    @Override
    public Repas read(final String libelle) throws DaoException {
        Set<Repas> repasSetTemporaire = new HashSet<>(persistance);
        if (!Objects.isNull(libelle)) {
            for (Repas item : repasSetTemporaire) {
                if (item.getId().toString().equals(libelle)) {
                    return item;
                }
            }
        }
        throw new DaoException(ConstantesException.DAO_REPAS_NULL);
    }

    /**
     * Enlève le repas de la liste en fonction de l'id.
     *
     * @param libelle l'id
     */
    @Override
    public void delete(final String libelle) throws DaoException {
        Set<Repas> repasSetTemporaire = new HashSet<>(persistance);
        if (!Objects.isNull(libelle)) {
            for (Repas item : repasSetTemporaire) {
                if (item.getId().toString().equals(libelle)) {
                    persistance.remove(item);
                }
            }
        } else {
            throw new DaoException(ConstantesException.DAO_REPAS_NULL);
        }
    }

    @Override
    public Set<Repas> readAll() {
        return Collections.unmodifiableSet(persistance);
    }

    @Override
    public boolean exist(final Repas repas) throws DaoException {
        try {
            return this.persistance.contains(repas);
        } catch (Exception e) {
            throw new DaoException(ConstantesException.DAO_REPAS_NULL);
        }
    }

    // TO STRING

    @Override
    public String toString() {
        return "RepasDaoImpl{" +
                "persistance=" + persistance +
                '}';
    }
}
