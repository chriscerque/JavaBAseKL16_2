package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Implémentation de {@link net.ent.etrs.repaspatient.model.daos.IRepasMemDao}.
 */
public class RepasMemDao implements IRepasMemDao {
    //region ATTRIBUTS
    List<Repas> persistence = new ArrayList<>();

    //endregion
    //region CONSTRUCTEUR(S)

    protected RepasMemDao() {
    }

    //endregion

    /**
     * Créé un repas dans la base de données.
     *
     * @param repas le repas à créer
     * @throws DaoException si le repas existe déjà dans la base de données ou si il est null
     */
    @Override
    public void create(final Repas repas) throws DaoException {
        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (this.exist(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
        }
        this.persistence.add(repas);
    }

    /**
     * Renvoie un Repas à partir d'un identifiant associé.
     *
     * @param id l'identifiant du Repas
     * @return le Repas associé
     * @throws DaoException si le repas n'a pas été trouvé
     */
    @Override
    public Repas read(final String id) throws DaoException {
        if (Objects.isNull(id) || id.isBlank()) {
            throw new DaoException(ConstantesMetier.DAO_ID_NULL);
        }
        for (Repas r : this.persistence) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        throw new DaoException(ConstantesMetier.DAO_REPAS_INTROUVABLE);
    }

    /**
     * Met à jour un repas dans la BDD.
     *
     * @param repas le repas à mettre à jour
     * @throws DaoException si le repas existe déjà ou est null
     */
    @Override
    public Repas update(final Repas repas) throws DaoException {
        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (!this.exist(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_INEXISTANT);
        }
        this.persistence.set(persistence.indexOf(repas), repas);
        return repas;
    }

    /**
     * Supprime un repas de la BDD.
     *
     * @param repas le repas à supprimer
     * @throws DaoException si le repas est null ou si il n'existe pas
     */
    @Override
    public void delete(final Repas repas) throws DaoException {
        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (!this.exist(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_INEXISTANT);
        }
        this.persistence.remove(repas);
    }

    /**
     * Pour sélectionner la liste des repas enregistrés.
     *
     * @return la liste non-modifiable des repas
     */
    @Override
    public List<Repas> readall() {
        return Collections.unmodifiableList(this.persistence);
    }

    @Override
    public void init() {

    }

    @Override
    public void deleteByKey(final String id) throws DaoException {
        if (Objects.isNull(id) || id.isBlank()) {
            throw new DaoException(ConstantesMetier.DAO_ID_NULL);
        }
        if (!this.exist(this.read(id))) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
        }
        Repas repas = this.read(id);
        this.persistence.remove(repas);
    }

    /**
     * Vérifie la présence d'un repas dans la BDD.
     *
     * @param repas le repas à vérifier
     * @return un bolléen exprimant la présence ou non du repas
     * @throws DaoException
     */
    @Override
    public boolean exist(final Repas repas) throws DaoException {
        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        return this.persistence.contains(repas);
    }
}
