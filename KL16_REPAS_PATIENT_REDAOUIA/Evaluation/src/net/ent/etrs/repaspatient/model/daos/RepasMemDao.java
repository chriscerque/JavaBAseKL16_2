package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepasMemDao implements IRepasMemDao {

    private List<Repas> persistence = new ArrayList<>();

    protected RepasMemDao() {
    }

    @Override
    public Repas create(Repas repas) throws DaoException {
        if (repas == null ){
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (!persistence.contains(repas)){
            persistence.add(repas);
            return repas;

        }

        throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
    }

    @Override
    public Repas read(String id) throws DaoException {
        if (id == null || id.isEmpty() || id.isBlank()){
            throw new DaoException(ConstantesMetier.DAO_REPAS_INEXISTANT);
        }
        List<Repas> tempRepas = new ArrayList<>(persistence);
        for (Repas rps : tempRepas){
            if (rps.getId() == id){
                return rps;
            }
        }
       throw new DaoException(ConstantesMetier.DAO_REPAS_INEXISTANT);
    }

    @Override
    public List<Repas> readAll() {
        return Collections.unmodifiableList(persistence);
    }

    @Override
    public Repas update(Repas repas) throws DaoException {
        if (repas == null){
            throw new DaoException(ConstantesMetier.DAO_REPAS_INEXISTANT);
        }
        if (!persistence.contains(repas)){
            throw new DaoException(ConstantesMetier.DAO_REPAS_INEXISTANT);
        }
        Repas rps = persistence.set(persistence.indexOf(repas), repas);
                return rps;
    }

    @Override
    public void delete(Repas repas) throws DaoException {
        if (repas == null){
            throw new DaoException(ConstantesMetier.DAO_REPAS_INEXISTANT);
        }
        if (!persistence.contains(repas)){
            throw new DaoException(ConstantesMetier.DAO_REPAS_INEXISTANT);
        }

        persistence.remove(repas);


    }

    @Override
    public void deleteByKey(String key) throws DaoException {
        if (key == null){
            throw new DaoException(ConstantesMetier.DAO_REPAS_INEXISTANT);
        }
        List<Repas> tempRepas = new ArrayList<>(persistence);
        for (Repas rps : tempRepas){
            if (key == rps.getId()){
                persistence.remove(rps);
            }
        }
        throw new DaoException(ConstantesMetier.DAO_REPAS_INEXISTANT);

    }

    @Override
    public void init() {

    }


}
