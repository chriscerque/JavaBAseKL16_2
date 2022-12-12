package net.ent.etrs.repaspatient.model.dao;

import net.ent.etrs.repaspatient.model.dao.exception.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.ArrayList;
import java.util.List;

public class RepasMemDao implements IRepasMemDao{

    //ATTRIBUTS
    List<Repas> persistance = new ArrayList<>();

    //CONSTRUCTEUR
    protected RepasMemDao() {
    }

    //AUTRES METHODES
    @Override
    public void create(Repas repas) throws DaoException {
        for(Repas r : persistance) {
            if (repas.getDateRepas().equals(repas.getDateRepas())){
                throw new DaoException(ConstantesDao.DAO_PATIENT_EXIST_EXCEPTION);
            }
        }
        this.persistance.add(repas);
    }

    @Override
    public void delete(Repas repas) throws DaoException {
        for (Repas r:persistance) {
            if(r.getId().equals(r.getId())){
                throw new DaoException(ConstantesDao.DAO_PATIENT_EXIST_PAS_EXCEPTION);
            }
        }
        this.persistance.remove(repas);
    }

    @Override
    public void deleteByKey(String id) throws DaoException {
        for (Repas r:persistance) {
            if(r.getId().equals(id)){
                throw new DaoException(ConstantesDao.DAO_PATIENT_EXIST_PAS_EXCEPTION);
            }
        }
        this.persistance.remove(id);
    }

    @Override
    public boolean exist(final Repas repas){
        for (Repas r:persistance) {
            repas.getDateRepas().equals(repas.getDateRepas());
            return true;
        }
        return false;
    }

    @Override
    public Repas read(final Repas repas) {
        Repas repas1 = null;
        for (Repas r : persistance) {
            if (r.getDateRepas().equals(repas.getDateRepas())) {
                repas1 = r;
            }
        }
        return repas1;
    }

    @Override
    public List<Repas> readAll(){
        return this.persistance;
    }

    @Override
    public void update(final Repas repas) throws DaoException {
        if(repas == null){
            throw new DaoException(ConstantesDao.DAO_PATIENT_EXIST_NULL_EXCEPTION);
        }
        int indexClient = this.persistance.indexOf(repas);
        this.persistance.set(indexClient, repas);
    }
}
