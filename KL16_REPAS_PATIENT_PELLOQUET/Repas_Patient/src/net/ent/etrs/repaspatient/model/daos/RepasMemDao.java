package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

public interface RepasMemDao {
    /**
     * Permet de créer un Repas en Base
     *
     * @param repas Repas à créer dans la Base
     */
    public Repas create(Repas repas) throws DaoException;

    /**
     * Permet de selectionner un Repas dans la base
     *
     * @param idRepas identifiant du Repas à selectionner
     */
    public Repas read(String idRepas) throws DaoException;

    /**
     * Permet d'afficher tous les Repass
     */
    public List<Repas> readAll();

    /**
     * Permet de mettre à jour un Repas
     *
     * @param repas Repas à mettre à jour
     */
    public Repas update(Repas repas) throws DaoException;

    /**
     * Permet de supprimer un Repas de la base
     *
     * @param idRepas identifiant du Repas à supprimer
     */
    public void deleteByKey(String idRepas) throws DaoException;

    /**
     * Permet de supprimer un Repas de la base
     *
     * @param repas Repas à supprimer
     */
    public void delete(Repas repas) throws DaoException;

    /**
     * Vérifie l'existence d'un repas dans la base
     * @param repas le Repas à confirmer
     * @return true/false
     */
    public boolean exist (Repas repas);

}
