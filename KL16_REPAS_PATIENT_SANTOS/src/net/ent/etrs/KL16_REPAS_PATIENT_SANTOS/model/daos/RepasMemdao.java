package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.daos;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.daos.exceptions.DaoException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.Repas;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references.ConstantesMetier;

import java.util.*;

public class RepasMemdao  implements IRepasMemDao {
    private List<Repas> persistance = new ArrayList<>();
    @Override
    public Repas create(Repas repas) throws DaoException {
        if (!Objects.isNull(repas)){
            persistance.add(repas);
            return persistance.get(persistance.indexOf(repas));
        }
        else {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
        }
    }

    @Override
    public List<Repas> readAll() {
        return Collections.unmodifiableList(persistance);
    }

    @Override
    public Repas update(Repas repas) throws DaoException {
        if(!Objects.isNull(repas) && persistance.contains(repas)){
            persistance.set(persistance.indexOf(repas), repas);
            return persistance.get(persistance.indexOf(repas));
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
    }


    @Override
    public void delete(Repas repas) throws DaoException {
        if(!Objects.isNull(repas))
        {
            persistance.removeIf(repas1 -> repas1.getId() == (repas1.getId()));
        }else {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
        }
    }

    @Override
    public void deleteByKey(UUID idRepas) throws DaoException {
        if (!Objects.isNull(idRepas)) {
            for (Repas repas : persistance) {
                if (repas.getId().equals(idRepas)) {
                    persistance.remove(repas);

                } else {
                    throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_NULL);
                }
            }
        }
    }

    @Override
    public boolean exist(Repas repas) throws DaoException {
        for (Repas repas1 : persistance) {
            return persistance.contains(repas);
        }

        return false;
    }


    @Override
    public void read(String patient) throws DaoException {

    }

    @Override
    public void init() throws DaoException {

    }
}
