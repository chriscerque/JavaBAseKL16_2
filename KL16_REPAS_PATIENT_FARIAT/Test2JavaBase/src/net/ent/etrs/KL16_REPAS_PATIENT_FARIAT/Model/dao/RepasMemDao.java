package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao;

import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.excetions.RepasMemDaoException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.Repas;

import java.util.List;
import java.util.UUID;

public interface RepasMemDao {

    /**
     * Permet de sauvegarder un repas.
     *
     * @param repas repas à sauvegarder
     * @return le repas sauvegarder
     * @throws RepasMemDaoException si le repas est null
     */
    public Repas create(Repas repas) throws RepasMemDaoException;

    /**
     * Selectionne tous les repas
     *
     * @return la liste des repas
     */
    public List<Repas> readAll();

    /**
     * Permet la mise à jour d'un repas.
     *
     * @param repas le repas à mettre à jour
     * @return le repas mise à jour
     * @throws RepasMemDaoException si le repas est null ou qu'il n'est pas connue
     */
    public Repas update(Repas repas) throws RepasMemDaoException;

    /**
     * Permet de supprimer un repas avec son id.
     *
     * @param id identifiant du repas à supprimer
     * @throws RepasMemDaoException si l'id est null ou non connu
     */
    public void deleteByKey(UUID id) throws RepasMemDaoException;

    /**
     * Selectionne tous les repas.
     *
     * @return la liste des repas
     */
    public Repas read(String id) throws RepasMemDaoException;

    /**
     * Permet de supprimer un repas .
     *
     * @param repas identifiant du repas à supprimer
     * @throws RepasMemDaoException
     */


    public void delete(Repas repas) throws RepasMemDaoException;


    /**
     * revoie si un repas existe ou non
     *
     * @param repas
     * @return boolean
     * @throws RepasMemDaoException
     */
    boolean exist(Repas repas) throws RepasMemDaoException;


}

