package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao;

import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.excetions.RepasMemDaoException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.references.ConstantesDao;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.Repas;

import java.util.*;

public class RepasMemDaoImpl implements RepasMemDao {
    List<Repas> persistence = new ArrayList<>();

    @Override
    public Repas create(final Repas repas) throws RepasMemDaoException {
        if (Objects.isNull(repas)) {
            throw new RepasMemDaoException(ConstantesDao.DAO_REPAS_CREATION_EXCEPTION);
        }
        if (exist(repas)) {
            throw new RepasMemDaoException(ConstantesDao.DAO_REPAS_EXIST_EXCEPTION);
        }
        this.persistence.add(repas);
        return repas;
    }

    @Override
    public List<Repas> readAll() {
        return Collections.unmodifiableList(persistence);

    }

    @Override
    public Repas update(final Repas repas) throws RepasMemDaoException {
        if (!Objects.isNull(repas) || persistence.contains(repas)) {
            persistence.remove(repas);
            persistence.add(repas);
            return repas;
        }
        throw new RepasMemDaoException(ConstantesDao.DAO_PATIENT_IMPOSSIBLE_UPDATE);
    }

    @Override
    public void deleteByKey(final UUID id) throws RepasMemDaoException {
        if (Objects.isNull(id)) {
            throw new RepasMemDaoException(ConstantesDao.REPAS_DAO_IDENTIFICATION_NULL);
        }

        List<Repas> listerRepasTemporaire = new ArrayList<>(persistence);

        for (Repas r : listerRepasTemporaire) {
            if (r.getId().equals(id)) {
                persistence.remove(r);
            }
        }

    }

    @Override
    public Repas read(String id) throws RepasMemDaoException {
        if (Objects.isNull(id)) {
            throw new RepasMemDaoException(ConstantesDao.REPAS_DAO_IDENTIFICATION_NULL);
        }
        List<Repas> listePatientTemporaire = new ArrayList<>(persistence);

        for (Repas repas : listePatientTemporaire) {
            if (id.equals(repas.getId())) {
                return repas;
            }
        }
        throw new RepasMemDaoException(ConstantesDao.DAO_REPAS_EXIST_PAS_EXCEPTION);

    }

    @Override
    public void delete(final Repas repas) throws RepasMemDaoException {
        if (Objects.isNull(repas)) {
            throw new RepasMemDaoException(ConstantesDao.REPAS_DAO_IDENTIFICATION_NULL);
        }

        List<Repas> listePatientTemporaire = new ArrayList<>(persistence);

        for (Repas p : listePatientTemporaire) {
            if (p.equals(repas)) {
                persistence.remove(p);
            }
        }

    }

    @Override
    public boolean exist(final Repas repas) throws RepasMemDaoException {
        return false;
    }
}
