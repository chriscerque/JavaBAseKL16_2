package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.List;

public interface IPatientMemDao {

    /**
     * Creer le Patient en parametre dans la base de donnée et le renvoie.
     *
     * @param pPatient l'objet a creer
     * @return l'objet creer
     * @throws DaoException si l'objet est null ou existe deja
     */
    Patient create(Patient pPatient) throws DaoException;

    /**
     * Retourne le Patient correspondant aux parametres (critères de recherche).
     *
     * @param numSecu les critères de recherche
     * @return l'objet souhaité
     * @throws DaoException si l'objet n'est pas trouvé
     */
    Patient read(String numSecu) throws DaoException;

    /**
     * Supprime le Repas dans la base de donnée correspondant aux id parametres (critères de recherche).
     *
     * @param numSecu les critères de recherche / id
     * @throws DaoException si l'objet est null ou n'existe pas
     */
    void deleteByKey(String numSecu) throws DaoException;

    /**
     * Supprime le Repas dans la base de donnée correspondant aux Repas parametres (critères de recherche).
     *
     * @param pPatient les critères de recherche / Repas
     * @throws DaoException si l'objet est null ou n'existe pas
     */
    void delete(Patient pPatient) throws DaoException;

    /**
     * Modifie le Patient en parametre dans la base de donnée.
     *
     * @param pPatient
     * @return l'objet modifié
     * @throws DaoException si la modification est echec
     */
    Patient update(Patient pPatient) throws DaoException;

    /**
     * Renvoie tous les éléments de type Patient de la base de donnée.
     *
     * @return une liste non modifiable de IRepasMem
     */
    List<Patient> readAll();

    /**
     * Renvoie si le Patient en paramètre est présent dans la base de donnée.
     *
     * @param pPatient
     * @return true (présent) ou false (non présent)
     * @throws DaoException si l'objet n'existe pas
     */
    boolean exist(Patient pPatient) throws DaoException;

    void init();
}