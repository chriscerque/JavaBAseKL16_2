package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.*;

public class RepasMemDao implements IRepasMemDao{
    private List<Repas> persistance = new ArrayList<>();
    /**
     * Read repas.
     *
     * @param id the id
     * @return the repas
     */
    @Override
    public Repas read(String id) throws DaoException {
        Set<Repas> repasSetTemporaire = new HashSet<>(persistance);
        if (!Objects.isNull(id)){
            for (Repas r: repasSetTemporaire) {
                if (r.getId().equals(id)){
                    return r;
                }
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);

    }

    /**
     * Delete by key.
     *
     * @param id the id
     */
    @Override
    public void deleteByKey(String id) throws DaoException {
        if(!Objects.isNull(id))
        {
            boolean supp = false;
            for (Repas r : persistance){
                if (r.getId().equals(id)){
                    persistance.remove(r);
                    supp = true;
                }
            }
            if (!supp) {
                throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
            }
        }else {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
        }
    }

    /**
     * Delete.
     *
     * @param repas the repas
     */
    @Override
    public void delete(Repas repas) throws DaoException {
        if(!Objects.isNull(repas))
        {
            deleteByKey(repas.getId());
        }else {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
        }
    }

    /**
     * Read all list.
     *
     * @return the list
     */
    @Override
    public List<Repas> readAll() {
        return Collections.unmodifiableList(persistance);
    }

    /**
     * Update repas.
     *
     * @param repas the repas
     * @return the repas
     */
    @Override
    public Repas update(Repas repas) throws DaoException {
        if(Objects.isNull(repas)){
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (!persistance.contains(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        persistance.remove(repas);
        persistance.add(repas);
        return repas;
    }

    /**
     * Exist boolean.
     *
     * @param repas the repas
     * @return the boolean
     */
    @Override
    public boolean exist(Repas repas) {
        if (repas == null) {
            return false;
        }
        if (persistance.contains(repas)) {
            return true;
        }
        return false;
    }

    /**
     * Init.
     */
    @Override
    public void init() {

    }

    /**
     * Create repas.
     *
     * @param repas the repas
     * @return the repas
     */
    @Override
    public Repas create(Repas repas) throws DaoException {
        if (!Objects.isNull(repas)){
            if (persistance.contains(repas)) {
                throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
            }
            persistance.add(repas);
            return persistance.get(persistance.indexOf(repas));
        }
        else {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
    }
}
