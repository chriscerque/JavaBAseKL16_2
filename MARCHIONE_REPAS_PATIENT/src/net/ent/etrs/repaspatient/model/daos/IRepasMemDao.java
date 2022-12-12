package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.entities.references.Repas;

import java.util.List;

/**
 * The interface Repas mem dao.
 */
public interface IRepasMemDao {


    /**
     * Read repas.
     *
     * @param libelle the libelle
     * @return the repas
     */
    public abstract Repas read(String libelle);

    /**
     * Delete by key.
     *
     * @param s the s
     */
    public abstract void deleteByKey(String s);

    /**
     * Delete.
     *
     * @param repas the repas
     */
    public abstract void delete(Repas repas);

    /**
     * Read all list.
     *
     * @return the list
     */
    public abstract List<Repas> readAll();

    /**
     * Update repas.
     *
     * @param repas the repas
     * @return the repas
     */
    public abstract Repas update(Repas repas);

    /**
     * Exist boolean.
     *
     * @param repas the repas
     * @return the boolean
     */
    public abstract boolean exist(Repas repas);

    /**
     * Init.
     */
    public void init();

    /**
     * Create repas.
     *
     * @param repas the repas
     * @return the repas
     */
    public abstract Repas create(Repas repas);







}

