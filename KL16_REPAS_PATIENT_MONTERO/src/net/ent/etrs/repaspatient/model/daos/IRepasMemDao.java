package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;

import java.util.List;

public interface IRepasMemDao {

    /**
     * Creer le Repas en parametre dans la base de donnée et le renvoie.
     *
     * @param pRepas l'objet a creer
     * @return l'objet creer
     * @throws DaoException si l'objet est null ou existe deja
     */
    Repas create(Repas pRepas) throws DaoException;

    /**
     * Retourne le Repas correspondant aux parametres (critères de recherche).
     *
     * @param id les critères de recherche
     * @return l'objet souhaité
     * @throws DaoException si l'objet n'est pas trouvé
     */
    Repas read(String id) throws DaoException;

    /**
     * Supprime le Repas dans la base de donnée correspondant aux id parametres (critères de recherche).
     *
     * @param id les critères de recherche / id
     * @throws DaoException si l'objet est null ou n'existe pas
     */
    void deleteByKey(String id) throws DaoException;

    /**
     * Supprime le Repas dans la base de donnée correspondant aux Repas parametres (critères de recherche).
     *
     * @param pRepas les critères de recherche / Repas
     * @throws DaoException si l'objet est null ou n'existe pas
     */
    void delete(Repas pRepas) throws DaoException;

    /**
     * Modifie le Repas en parametre dans la base de donnée.
     *
     * @param pRepas
     * @return l'objet modifié
     * @throws DaoException si la modification est echec
     */
    Repas update(Repas pRepas) throws DaoException;

    /**
     * Renvoie tous les éléments de type Repas de la base de donnée.
     *
     * @return une liste non modifiable de IRepasMem
     */
    List<Repas> readAll();

    /**
     * Renvoie si le Repas en paramètre est présent dans la base de donnée.
     *
     * @param pRepas
     * @return true (présent) ou false (non présent)
     * @throws DaoException si l'objet n'existe pas
     */
    boolean exist(Repas pRepas) throws DaoException;

    void init();
}