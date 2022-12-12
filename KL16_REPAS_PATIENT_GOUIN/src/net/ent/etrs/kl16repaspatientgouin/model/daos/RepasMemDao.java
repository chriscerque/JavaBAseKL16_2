package net.ent.etrs.kl16repaspatientgouin.model.daos;

import net.ent.etrs.kl16repaspatientgouin.model.daos.exceptions.DaoException;
import net.ent.etrs.kl16repaspatientgouin.model.entities.EntitiesFactory;
import net.ent.etrs.kl16repaspatientgouin.model.entities.Repas;
import net.ent.etrs.kl16repaspatientgouin.model.entities.exceptions.EntitiesFactoryException;

import net.ent.etrs.kl16repaspatientgouin.model.entities.exceptions.RepasException;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.ConstantesMetier;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.TypeRepas;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RepasMemDao implements IRepasMemDao{
    private List<Repas> cantine;

    private RepasMemDao repasDao;

    protected RepasMemDao() throws DaoException {
        this.cantine = new ArrayList<>();
        this.repasDao = DaoFactory.fabriquerRepasDao();
        this.init();
    }

    @Override
    public Repas create(final Repas repas) throws DaoException {
        if (Objects.isNull(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        if (exist(repas)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
        }
        this.cantine.add(repas);
        return this.read(repas.getId());
    }

    @Override
    public Repas read(final String id) throws DaoException {
        if (!Objects.isNull(id)){
            for (Repas repas: this.cantine) {
                if (repas.getId().equals(id)){
                    return repas;
                }
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
    }

    @Override
    public Repas update(final Repas repas) throws DaoException {
        if(!Objects.isNull(repas) || cantine.contains(repas)){
            cantine.remove(repas);
            cantine.add(repas);
            return repas;
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_REPAS_INEXISTANT);
    }

    @Override
    public void delete(final Repas repas) throws DaoException {
        if (!Objects.isNull(repas)){
            for (Repas r: this.cantine) {
                if (r.equals(repas)){
                    cantine.remove(r);
                }
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
    }

    @Override
    public void deletebyKey(final String id) throws DaoException {
        if (!Objects.isNull(id)){
            for (Repas r: this.cantine) {
                if (r.equals(this.read(id))){
                    cantine.remove(r);
                }
            }
        }
    }

    @Override
    public List<Repas> readAll() {
        return Collections.unmodifiableList(this.cantine);
    }

    @Override
    public void init() throws DaoException {

        try {
            Repas r1 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(4), TypeRepas.PETIT_DEJEUNER);
            r1.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            r1.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            Repas r2 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(4), TypeRepas.DEJEUNER);
            r2.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGAN);
            r2.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            Repas r3 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(4), TypeRepas.DINER);
            r3.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
            Repas r4 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(5), TypeRepas.PETIT_DEJEUNER);
            r4.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            Repas r5 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(5), TypeRepas.DEJEUNER);
            r5.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            r5.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
            Repas r6 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(5), TypeRepas.DINER);
            r6.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
            r6.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            Repas r7 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(6), TypeRepas.PETIT_DEJEUNER);
            r7.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
            Repas r8 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(6), TypeRepas.DEJEUNER);
            r8.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);

            this.repasDao.create(r1);
            this.repasDao.create(r2);
            this.repasDao.create(r3);
            this.repasDao.create(r4);
            this.repasDao.create(r5);
            this.repasDao.create(r6);
            this.repasDao.create(r7);
            this.repasDao.create(r8);

        } catch (EntitiesFactoryException | RepasException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }

    @Override
    public boolean exist(final Repas repas) {
        return this.cantine.contains(repas);
    }
}
