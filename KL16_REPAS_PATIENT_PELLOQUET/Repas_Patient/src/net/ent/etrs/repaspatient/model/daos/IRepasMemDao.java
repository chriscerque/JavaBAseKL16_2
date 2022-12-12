package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

//Repas = nom classe
//repas = nom variable
//REPAS = nom classe majuscules
//idRepas = identifiant classe
//idRepas = UUID classe

public class IRepasMemDao implements RepasMemDao {

    List<Repas> persitanceRepas = new ArrayList<>();

    protected IRepasMemDao() {
    }

    /**
     * Permet de créer un Repas en Base
     *
     * @param repas Repas à créer dans la Base
     */
    @Override
    public Repas create(Repas repas) throws DaoException {
        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (persitanceRepas.contains(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
        }
        persitanceRepas.add(repas);
        return repas;
    }

    /**
     * Permet de selectionner un Repas dans la base
     *
     * @param idRepas identifiant du Repas à selectionner
     */
    @Override
    public Repas read(String idRepas) throws DaoException {
        if (Objects.isNull(idRepas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_LECTURE_REPAS_NULL);
        }
        List<Repas> listeRepasTemporaire = new ArrayList<>(persitanceRepas);

        for (Repas repas : listeRepasTemporaire) {
            if (idRepas.equals(repas.getId())) {
                return repas;
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_LECTURE_REPAS_INEXISTANT);
    }

    /**
     * Permet d'afficher tous les Repas
     */
    @Override
    public List<Repas> readAll() {
        return Collections.unmodifiableList(persitanceRepas);
    }

    /**
     * Permet de mettre à jour un Repas
     *
     * @param repas Repas à mettre à jour
     */
    @Override
    public Repas update(Repas repas) throws DaoException {
        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_REPAS_NULL);
        }
        if (!persitanceRepas.contains(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_REPAS_INEXISTANT);
        }

        Repas r = persitanceRepas.set(persitanceRepas.indexOf(repas), repas);

        return r;
    }

    /**
     * Permet de supprimer un Repas de la base
     *
     * @param idRepas identifiant du Repas à supprimer
     */
    @Override
    public void deleteByKey(String idRepas) throws DaoException {

        if (Objects.isNull(idRepas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_NULL);
        }
        int temp = 0;
        List<Repas> listeRepasTemporaire = new ArrayList<>(persitanceRepas);
        for (Repas t : listeRepasTemporaire) {
            if (t.getId().equals(idRepas)) {
                persitanceRepas.remove(t);
            } else {
                temp++;
            }
        }
        if (temp == persitanceRepas.size()) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
        }

    }

    /**
     * Permet de supprimer un Repas de la base
     *
     * @param repas Repas à supprimer
     */
    @Override
    public void delete(Repas repas) throws DaoException {

        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_NULL);
        }
        if (persitanceRepas.contains(repas)) {
            AffichageConsole.afficherMessageAvecSautLigne(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
        } else {
            persitanceRepas.remove(repas);

        }
    }

    /**
     * vérifie l'existence d'un repas dans la base
     * @param repas le Repas à confirmer
     * @return true/false
     */
        @Override
    public boolean exist(Repas repas) {
        if (this.persitanceRepas.contains(repas)) {
            return true;
        }
        return false;
    }


}