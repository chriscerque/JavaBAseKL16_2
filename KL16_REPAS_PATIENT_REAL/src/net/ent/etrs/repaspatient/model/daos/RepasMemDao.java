package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.*;

public class RepasMemDao implements IRepasMemsDao{

    private List<Repas> persistance = new ArrayList<>();

    protected RepasMemDao() {
    }

    @Override
    public UUID read(UUID id) throws RepasConstructionException, DaoException {
        if (Objects.isNull(id)) {
            throw new RepasConstructionException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
        }
        List<Repas> listeRepasTemporaire = new ArrayList<>(persistance);

        for (Repas repas : listeRepasTemporaire) {
            if (id.equals(repas.getTypeRepas())) {
                return id;
            }
        }
        throw new DaoException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
    }

    @Override
    public void deleteByKey(UUID id) {

    }

    @Override
    public void delete(Repas r) {
        if (!Objects.isNull(r)) {
            for (Repas repas : persistance) {
                if (repas.getTypeRepas().equals(r)) {
                    persistance.remove(repas);
                }
            }
        }
    }

    @Override
    public List<Repas> readAll() {
        return Collections.unmodifiableList(persistance);
    }

    @Override
    public Repas update(Repas r) throws DaoException {
        if(!Objects.isNull(r) && persistance.contains(r)){
            persistance.set(persistance.indexOf(r), r);
            return persistance.get(persistance.indexOf(r));
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT);
    }

    @Override
    public boolean exists(Repas r) {
        if(!Objects.isNull(r) && persistance.contains(r)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void init() {

    }

    @Override
    public Repas create(Repas r) throws DaoException {
        if (!Objects.isNull(r)){
            persistance.add(r);
            return persistance.get(persistance.indexOf(r));
        }
        else {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
    }
}
