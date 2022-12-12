package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.entities.references.Repas;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Repas mem dao.
 */
public class RepasMemDao {

    //------------------------------------------------
    //          ATTRIBUTS
    //------------------------------------------------

    private List<Repas> persistence = new ArrayList<>();


    /**
     * Instantiates a new Repas mem dao.
     */
    protected RepasMemDao() {
    }

    /**
     * Read all list.
     *
     * @return the list
     */
    public List<Repas> readAll() {

        return null;
    }

    /**
     * Update repas.
     *
     * @param repas the repas
     * @return the repas
     */
    public Repas update(Repas repas) {

        return repas;
    }

    /**
     * Delete.
     *
     * @param repas the repas
     */
    public void delete(Repas repas) {

    }

    /**
     * Delete by key.
     *
     * @param s the s
     */
    public void deleteByKey(String s) {

    }

    /**
     * Create repas.
     *
     * @param repas the repas
     * @return the repas
     */
    public Repas create (Repas repas) {

        return repas;
    }

    /**
     * Exist boolean.
     *
     * @param repas the repas
     * @return the boolean
     */
    public boolean exist(Repas repas) {

        return false;
    }

    /**
     * Repas repas.
     *
     * @param s the s
     * @return the repas
     */
    public Repas repas (String s) {

        return null;
    }


}
